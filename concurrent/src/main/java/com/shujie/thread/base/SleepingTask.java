package com.shujie.thread.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author linshujie
 */
public class SleepingTask extends LitfOff{
    @Override
    public void run() {
        while (cownDown -- > 0){
            System.out.println("status() = " + status());
            //旧的调用方式
            /*Thread.sleep(100);*/
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("e = " + e);
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            cachedThreadPool.execute(new SleepingTask());
        }
        cachedThreadPool.shutdown();
    }
}
