package com.shujie.thread.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author linshujie
 */
public class AtomicIntegerTest implements Runnable {

    private final AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) {
        //添加timer，让他五秒后自动退出
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("终止");
                System.exit(0);
            }
        }, 5000);

        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest atomicityTest = new AtomicIntegerTest();
        exec.execute(atomicityTest);

        while (true) {
            int val = atomicityTest.getValue();
            System.out.println("val = " + val);
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }

    private int getValue() {
        return i.get();
    }

    private void envenIncrement(){
        i.addAndGet(2);
    }

    @Override
    public void run() {
        while (true){
            envenIncrement();
        }
    }
}
