# GC垃圾回收器

因为在使用JAVA创建一个类或者对象后，难免会存在以后不使用的情况，为了减少其继续再占用内存，必须建立一套清理垃圾的机制，但是怎么判断什么样的才算是不使用的垃圾呢，这里面进行了判断并标记分类，然后根据不同的标记再进行不同的处理。不过世事无完美之说，其也是存在弊端的（开销通常很大，而且它的运行具有不确定性），为了避免，我们还是在正常工作中，养成一个好的编程习惯。

**串行垃圾收集器**：一般在Java web应用程序中是不会采用串行垃圾收集器的。一旦进行垃圾回收，应用就会被暂停。

**并行垃圾收集器**：在串行垃圾收集器上做了改进，将单线程改成多线程进行垃圾回收，缩短回收时间。

**CMS垃圾收集器**：并发的，使用标记-清除算法的垃圾回收器，针对老年代垃圾回收的。

**G1垃圾收集器**：G1 GC有计划地避免在整个Java堆中进行全区域的垃圾收集。G1跟踪各个Region里面的垃圾堆积的价值大小（回收所获得的空间大小以及回收所需时间的经验值），在后台维护一个优先列表，**每次根据允许的收集时间，优先回收价值最大的Region**。内存的回收是以region作为基本单位的。**Region之间是复制算法**，但整体上实际可看作是**标记-压缩（Mark-Compact）算法**。G1（Garbage-First）是一款面向服务端应用的垃圾收集器，主要针对配备多核CPU及大容量内存的机器，以极高概率满足GC停顿时间的同时，还兼具高吞吐量的性能特征。

# 堆内存

**堆内存划分**

Java 7及之前堆内存逻辑上分为三部分：新生代+老年代+**永久区**

| 新生代                                    | 老年代                  | 永久区          |
| ----------------------------------------- | ----------------------- | --------------- |
| Young Generation Space                    | Tenure generation space | Permanent Space |
| Young/New（又被划分为Eden区和Survivor区） | Old/Tenure              | Perm            |

Java 8及之后堆内存逻辑上分为三部分：新生代+老年代+**元空间**

| 新生代                                    | 老年代                  | 元空间     |
| ----------------------------------------- | ----------------------- | ---------- |
| Young Generation Space                    | Tenure generation space | Meta Space |
| Young/New（又被划分为Eden区和Survivor区） | Old/Tenure              | Meta       |

其中年轻代又可以划分为Eden空间、Survivor0空间和Survivor1空间（有时也叫做from区、to区）。

 <div align="center"> <img src="..\mission4\新生代与老年代.png" width="800px"></div>

**堆对象分配过程**

* new的对象先放Eden区。
* 当Eden区的空间填满时，程序还需创建对象，JVM的垃圾回收器将对Eden区进行垃圾回收（**MinorGC**，又称Young GC），将Eden区中的不再被其他对象所引用的对象进行销毁，再加载新的对象放到Eden区。
* 然后将Eden区中的幸存的对象移动到From区（Survivor From区）。

- 如果再次触发垃圾回收，此时Eden区和From区幸存下来的对象就会放到To区（Survivor To区）。
  - 此过程后From区对象都放到To区，故From区变To区，原To区变From区。

- 如果再次经历垃圾回收，此时Eden区对象会重新放回From区，接着再去To区。
- 啥时候能去养老区呢？当Survivor中的对象的年龄达到15的时候，将会触发一次 Promotion晋升的操作，对象晋升至养老区。可以设置次数：`-Xx:MaxTenuringThreshold= N`，**默认是15次**。
- 当养老区内存不足时，再次触发垃圾回收（**Major GC**），进行养老区的内存清理。
- 若养老区执行了Major GC之后，发现依然无法进行对象的保存，就会产生OOM异常。

 <div align="center"> <img src="..\mission4\堆对象分配过程.png" width="600px"></div>

**Minor GC、Major GC、Full GC**

- Minor GC：年轻代的GC。
- Major GC：老年代的GC。
- Full GC：整堆收集，收集整个Java堆和方法区的垃圾收集。

> Major GC 和 Full GC出现STW的时间，是Minor GC的10倍以上。

