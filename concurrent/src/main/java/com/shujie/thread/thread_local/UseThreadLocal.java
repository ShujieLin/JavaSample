package com.shujie.thread.thread_local;

/**
 * ThreadLocal的使用
 * Created by linshujie on 2022/3/25.
 */
public class UseThreadLocal {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    /**
     * 运行3个线程
     */
    public void startThreadArray() {
        Thread[] runs = new Thread[3];
        for (int i = 0; i < runs.length; i++) {
            runs[i] = new Thread(new TestThread(i));
        }
        for (int i = 0; i < runs.length; i++) {
            runs[i].start();
        }
    }

    /**
     * 测试线程，线程的工作是将ThreadLocal变量的值变化，并写回，看看线程之间是否会互相影响
     */
    public static class TestThread implements Runnable {
        int id;

        public TestThread(int id) {
            this.id = id;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + ":start");
            Integer initialValue = threadLocal.get();
            initialValue = initialValue + id;
            threadLocal.set(initialValue);
            System.out.println(Thread.currentThread().getName()
                    + ":" + threadLocal.get());
            threadLocal.remove();
        }
    }

    public static void main(String[] args) {
        UseThreadLocal test = new UseThreadLocal();
        test.startThreadArray();
    }
}
