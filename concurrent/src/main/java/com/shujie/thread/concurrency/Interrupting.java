package com.shujie.thread.concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author linshujie
 */
public class Interrupting {
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable runnable) throws InterruptedException {
        Future<?> future = exec.submit(runnable);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("interrupting " + runnable.getClass().getSimpleName());
        future.cancel(true);
        System.out.println("interrupt sent to " + runnable.getClass().getSimpleName());
    }

    public static void main(String[] args) throws InterruptedException {
        test(new SleepBlocked());
        /*test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());*/
        TimeUnit.SECONDS.sleep(3);
        System.out.println("aborting with System.exit(0)");
        System.exit(0);
    }
}

class SleepBlocked implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
        System.out.println("exiting SleepBlocked.run()");
    }
}

class IOBlocked implements Runnable {
    private InputStream in;

    public IOBlocked(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            System.out.println("wait for read");
            in.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("interrupted from blocked I/O");
            } else {
                throw new RuntimeException(e);
            }
        }
        System.out.println("exiting IOBlocked.run()");
    }


}

class SynchronizedBlocked implements Runnable {

    public synchronized void f() {
        while (true) Thread.yield();//一直持有锁
    }

    public SynchronizedBlocked() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                f();
            }
        }).start();
    }

    @Override
    public void run() {
        System.out.println("trying to call f()");
        f();
        System.out.println("exiting SynchronizedBlocked.run()");
    }
}