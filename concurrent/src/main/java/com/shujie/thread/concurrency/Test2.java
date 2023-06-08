package com.shujie.thread.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author linshujie
 */
public class Test2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted());//false
                try {
                    TimeUnit.SECONDS.sleep(6);
                } catch (InterruptedException e) {
                    System.out.println("e = " + e);
                }
                System.out.println("prepare to exit");
                System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted());
            }
        });

        try {
            TimeUnit.SECONDS.sleep(3);
            executorService.shutdownNow();
            if (!executorService.awaitTermination(1,TimeUnit.SECONDS)){
                System.out.println("awaitTermination timeout");
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}