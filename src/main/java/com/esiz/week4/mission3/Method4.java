package com.esiz.week4.mission3;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

public class Method4 {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        ThreadFactory threadFactory = new CustomizableThreadFactory("springThread-pool-thread");
        ExecutorService exec = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(1), threadFactory);
        Future<Integer> future = exec.submit(Method4::sum);
        System.out.println("异步计算结果为：" + future.get());
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}
