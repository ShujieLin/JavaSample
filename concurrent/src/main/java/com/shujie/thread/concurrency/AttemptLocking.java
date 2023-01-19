package com.shujie.thread.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author linshujie
 */
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        final AttemptLocking attemptLocking = new AttemptLocking();
        attemptLocking.untimed();//获取锁
        attemptLocking.timed();//获取锁

        //新建一个任务来获取锁
        new Thread() {
            {
                setDaemon(true);
            }

            @Override
            public void run() {
                super.run();
                attemptLocking.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        Thread.yield();
        TimeUnit.SECONDS.sleep(1);
        //假如子线程拿到锁的话，主线程再也拿不到锁
        attemptLocking.untimed();
        attemptLocking.timed();
    }

    public void untimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock(): " + captured);
        } finally {
            if (captured) lock.unlock();
        }
    }

    public void timed() {
        boolean captured;//捕获
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS):" + captured);
        } finally {
            if (captured) lock.unlock();
        }
    }
}
