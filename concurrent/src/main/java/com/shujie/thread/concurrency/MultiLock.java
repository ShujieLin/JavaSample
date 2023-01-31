package com.shujie.thread.concurrency;

/**
 *
 * @author linshujie
 */
public class MultiLock {
    public static void main(String[] args) {
        final MultiLock multiLock = new MultiLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                multiLock.f1(10);
            }
        }).start();
    }

    /**
     * 一个任务能够调用同一对象中的其他synchronize方法，而这个任务已经持有锁了
     * @param count
     */
    private synchronized void f1(int count) {
        if (count -- > 0){
            f2(count);
        }
        System.out.println("f1() " + count);
    }

    private synchronized void f2(int count) {
        if (count-- >0){
            f1(count);
        }
        System.out.println("f2() " + count);
    }
}
