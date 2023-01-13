package com.shujie.concurrent.base;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程创建的任何线程都是后台线程。
 * 后台线程在不执行finally的情况下就会终止run方法
 * @author linshujie
 */
public class Daemons {
    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.println("d.isDaemon() = " + d.isDaemon() + ", ");

        //让守护线程有时间完成启动
        TimeUnit.MILLISECONDS.sleep(1000);
    }
}