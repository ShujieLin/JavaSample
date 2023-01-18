package com.shujie.thread.concurrency;

/**
 * @author linshujie
 */
public class EvenGenerator extends IntGenerator{
    private int currentEvenValue = 0;

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }

    /*@Override
    public int next() {
        ++currentEvenValue;//不做同步，导致多线程资源抢占
        ++currentEvenValue;
        return currentEvenValue;
    }*/

    //如果方法在同一个对象中嵌套调用，该任务可以多次获得对象锁，jvm负责跟踪对象被加锁的次数，如果一个对象的锁被完全释放，其计数会变为0。
    @Override
    public synchronized int next() {
        ++currentEvenValue;//不做同步，导致多线程资源抢占
        Thread.yield();//增加资源抢占的概率
        ++currentEvenValue;
        return currentEvenValue;
    }
}