package com.shujie.concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author linshujie
 */
public class TestInterrupt {
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(2,5,60, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));
    static int i = 0;

    private static volatile boolean isInterrupt = false;

    public static void main(String[] args) {

        executor.execute(myRunable);
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("e = " + e);
        }
        isInterrupt = true;
    }

    static Runnable myRunable = new Runnable() {
        @Override
        public void run() {
            while (!isInterrupt){
                try {
                    Thread.sleep(1000);
                    System.out.println("i ++ = " + i++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}