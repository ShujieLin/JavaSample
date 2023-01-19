package com.shujie.thread.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 系统的可视性问题比原子性问题多得多，一个任务做出的修改即使在不中断的意义来讲是原子性的，对其它任务来说可能是不可视的，修改只是暂时性的存储在
 * 本地处理器的缓存中。因此不同任务对应用的状态有不同的视图。
 * volatile关键字可以确保应用的可视性，如果一个域声明为volatile，那么只要对这个域进行写操作，那么所有的读操作就都可以看到这个修改。volatile域会
 * 立刻被写进主存中，而读取操作就发生在主内存。如果多个任务在同时访问某个域，那么这个域应该声明为volatile，否则这个域只能由同步来访问，因为同步也会
 * 向主内存刷新。
 * 如果一个域完全由snychronized防护，那么没有必要设置为volatile的。
 * <p>
 * <p>
 * 如果一个域同时被多个任务同时访问，或者在被多个任务访问的同时，只有一个任务进行了写入操作。那么这个域应该被设置为volatile，它会高速编译器不要执行任何移除
 * 读取和写入操作的优化。
 *
 * @author linshujie
 */
public class AtomicityTest implements Runnable {
    private int i = 0;

    public static void main(String[] args) throws InterruptedException {
        //子线程不断执行累加
        AtomicityTest at = new AtomicityTest();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(at);

        /*TimeUnit.SECONDS.sleep(1);*/
        //主线程循环检测,
        while (true) {
            int val = at.getValue();
            System.out.println("val = " + val);
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }

    /**
     * 假如不使用synchronized，尽管return i是原子操作，但是缺少同步机制，导致数值处于不稳定的中间状态被读取
     * 同时由于变量i缺少volatile，缺少可视性问题。
     *
     * @return
     */
    private /*synchronized*/ int getValue() {
        return i;
    }

    @Override
    public void run() {
        while (true) eventIncrement();
    }

    private synchronized void eventIncrement() {
        i++;
        i++;
        /*i+=2;*/
    }
}