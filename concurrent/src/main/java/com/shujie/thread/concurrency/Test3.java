package com.shujie.thread.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author linshujie
 */
public class Test3 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted() + " before enter while");//false
                try {
                    while (!Thread.interrupted()) {
                        TimeUnit.SECONDS.sleep(6);
                    }
                } catch (InterruptedException e) {
                    System.out.println("e = " + e);
                    System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted());

                    System.out.println("1st interrupted = " + Thread.interrupted());
                    System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted());

                    System.out.println("2nd interrupted = " + Thread.interrupted());
                    System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted());
                }
                System.out.println("exit");
            }
        });

        try {
            TimeUnit.SECONDS.sleep(3);
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}