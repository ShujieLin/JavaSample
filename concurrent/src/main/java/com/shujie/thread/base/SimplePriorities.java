package com.shujie.thread.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author linshujie
 */
public class SimplePriorities implements Runnable {
    private int countDown = 5;
    private volatile double d = 0;//这里的volatile用于确保不进行任务编译器优化
    private final int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread() + " : "  + countDown ;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);//注意优先级是在run()的开头部分设定的，在构造器中设置没有作用，因为Excutor还没有开始执行任务。
        while (true) {
            //一个昂贵的，可以中断的操作
            for (int i = 0; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0) {
                    Thread.yield();
                }
            }

            System.out.println(this);
            if (--countDown == 0) return;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }
}
