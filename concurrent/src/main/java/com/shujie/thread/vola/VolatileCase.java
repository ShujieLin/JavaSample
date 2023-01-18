package com.shujie.thread.vola;

import com.shujie.thread.utils.SleepTools;

/**
 * 演示Volatile的提供的可见性
 * Created by linshujie on 2022/3/26.
 */
public class VolatileCase {
    private volatile static boolean ready;
    private static int number;

    private static class PrintThread extends Thread{
        @Override
        public void run() {
            System.out.println("PrintThread is running.......");
            while(!ready);//无限循环
            System.out.println("number = "+number);
        }
    }

    public static void main(String[] args) {
        new PrintThread().start();
        SleepTools.second(1);
        number = 51;
        ready = true;
        SleepTools.second(5);
        System.out.println("main is ended!");
    }
}
