package com.shujie.thread.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 互斥锁
 *
 * @author linshujie
 */
public class MutexEvenGenerator extends IntGenerator{
    private int currentEvenValue = 0;
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }

    //如果方法在同一个对象中嵌套调用，该任务可以多次获得对象锁，jvm负责跟踪对象被加锁的次数，如果一个对象的锁被完全释放，其计数会变为0。
    @Override
    public int next() {
        lock.lock();
        try {
            ++currentEvenValue;//不做同步，导致多线程资源抢占
            Thread.yield();//增加资源抢占的概率
            ++currentEvenValue;
            return currentEvenValue;
        } finally {//如果使用synchronized，假如某些地方出问题了，会抛出异常，但是没有任何机会进行清理工作，使得程序处于良好状态。
            lock.unlock();//有了显式的锁，可以进行维护和清理工作。
        }
    }
}