package com.shujie.thread.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author linshujie
 */
public class AtomicIntegerTest3 implements Callable<Integer> {
    private int i = 0;

    @Override
    public Integer call() throws Exception {
        synchronized (this) {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        }
        return i;
    }


    public int getI() {
        return i;
    }

    public static void main(String[] args) {
        List<Future<Integer>> futureList = new ArrayList<>();
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest3 ait = new AtomicIntegerTest3();
        for (int i = 0; i < 5; i++) {
            futureList.add(exec.submit(ait));
        }

        try {
            for (int i = 0; i < futureList.size(); i++) {
                System.out.println("future[" + i + "]" + +futureList.get(i).get());
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("e = " + e);
        } finally {
            exec.shutdown();
        }

    }
} ///:~
