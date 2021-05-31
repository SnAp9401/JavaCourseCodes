package com.esiz.week4.mission3;

public class Method5 {

    private static int result = 0;
    private static volatile boolean hasComputeEnd = false;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread computeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                result = sum();
                hasComputeEnd = true;
            }
        });
        computeThread.start();
        //main thread wait hasComputeEnd become true
        while (!hasComputeEnd) {
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
