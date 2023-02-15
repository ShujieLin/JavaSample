package com.shujie.thread.concurrency;

import static com.shujie.thread.concurrency.ThreadLocalVariableHolder.increment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author linshujie
 */
public class AtomicIntegerTest4 implements Callable<Integer> {
    private AtomicInteger i = new AtomicInteger(0);

    @Override
    public Integer call() throws Exception {
        for (int j = 0; j < 10000; j++) {
            /**
             * Atomically adds the given value to the current value.
             * Params:
             * delta – the value to add
             * Returns:
             * the updated value
             */
            /*i.addAndGet(1);*/
            /**
             * Atomically adds the given value to the current value.
             * Params:
             * delta – the value to add
             * Returns:
             * the previous value
             */
            /*i.getAndAdd(1);*/

            /*i.set(myIncrementAndSet(i));*/

            i.incrementAndGet();

        }
        return i.get();
    }

    private int myIncrementAndSet(AtomicInteger atomicInteger) {
        while (true){
            int before = atomicInteger.get();
            int after = calcNext(before);
            if (atomicInteger.compareAndSet(before,after)){
                return after;
            }
        }
    }

    private int calcNext(int before) {
        return ++ before;
    }


    public static void main(String[] args) {
        List<Future<Integer>> futureList = new ArrayList<>();
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest4 ait = new AtomicIntegerTest4();
        for (int i = 0; i < 5; i++) {
            futureList.add(exec.submit(ait));
        }

        try {
            for (int i = 0; i < futureList.size(); i++) {
                System.out.println("future[" + i + "]" + +futureList.get(i).get());
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("e = " + e);
        } finally {
            exec.shutdown();
        }

    }
} ///:~
