package com.shujie.thread.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author linshujie
 */
public class Interrupting2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("issuing t.interrupt");
        t.interrupt();
    }
}

class BlockMutex{
    private Lock lock = new ReentrantLock();

    public BlockMutex() {
        lock.lock();
    }

    public void f(){
        try {
            //与synchronize不同，这将变得可以被中断，但是锁不会再被第二个任务获取了
            lock.lockInterruptibly();
            System.out.println("lock aquired in f()");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class Blocked2 implements Runnable{
    BlockMutex blockMutex = new BlockMutex();

    @Override
    public void run() {
        System.out.println("waiting for f() in BlockMutex");
        blockMutex.f();
        System.out.println("broken out of blocked call");
    }
}
