package com.shujie.thread.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author linshujie
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted() + " before enter while");
                while (!Thread.currentThread().isInterrupted()){
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println("e = " + e);
                        System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted());
                        /*Thread.interrupted();*/
                        Thread.currentThread().interrupt();
                        System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted());
//                        System.out.println("interrupted = " + Thread.interrupted());
                    }
                }

            }
        });

        try {
            TimeUnit.SECONDS.sleep(5);
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
