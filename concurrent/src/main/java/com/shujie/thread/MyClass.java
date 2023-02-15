package com.shujie.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyClass {
    static Object channelPreHandler = new Object();
    private static boolean isConnected;

    public static void main(String[] args) {
        isConnected = true;
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.interrupted()) {
                        if (!isConnected) Thread.currentThread().interrupt();
                        //channelPreHandler必须要实现，因为执行了读取原始数据的功能
                        System.out.println("lsj" + "执行非阻塞接收");
                        TimeUnit.MILLISECONDS.sleep(500);
                    }
                    System.out.println("lsj" + "线程退出循环并结束");
                } catch (Exception e) {
                    System.out.println("lsj" + "线程异常退出:" + e);
                }
            }
        });

        try {
            TimeUnit.SECONDS.sleep(5);
            isConnected = false;
        } catch (InterruptedException e) {
            System.out.println("e = " + e);
        }
    }


}
