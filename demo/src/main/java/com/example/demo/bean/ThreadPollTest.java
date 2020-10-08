package com.example.demo.bean;

import java.util.concurrent.*;

/**
 * Created by 24345 on 2020/7/29.
 */
public class ThreadPollTest {
    public static void main(String[] args) {
        int poolSize = Runtime.getRuntime().availableProcessors() * 2;
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(512);
        RejectedExecutionHandler policy = new ThreadPoolExecutor.DiscardPolicy();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(poolSize, poolSize, 0, TimeUnit.SECONDS, queue, policy);
        Future<Object> future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("lizhihua");
                return "wuxiaojun";
            }
        });
        try {
            String result = (String)future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
