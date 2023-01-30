package com.shujie.thread.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author linshujie
 */
public class ThreadLocalVariableHolder {
    private static final ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        private Random random = new Random(47);

        @Override
        protected Integer initialValue() {
            return random.nextInt();
        }
    };

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Accessor(i));
        }
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
    }

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static Object get() {
        return value.get();
    }
}

class Accessor implements Runnable {
    private final int id;

    public Accessor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "Accessor{" +
                "id=" + id +
                ": " + ThreadLocalVariableHolder.get() +
                '}';
    }
}