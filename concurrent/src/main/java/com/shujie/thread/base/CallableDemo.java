package com.shujie.thread.base;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author linshujie
 */
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //submit会产生Future对象，可以用isDone()来查询Future是否完成。也可以不用，直接调用get()，这种情况下，get()将会阻塞，直至结果准备就绪。
            results.add(pool.submit(new TaskWithResult(i)));
        }

        for (Future<String> fs :
                results) {
            try {
                System.out.println("fs = " + fs.get());
            } catch (InterruptedException e) {
                System.out.println("e = " + e);
                return;
            } catch (ExecutionException e) {
                System.out.println("e = " + e);
            } finally {
                pool.shutdown();
            }
        }
    }

}

class TaskWithResult implements Callable<String> {
    private final int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of TaskWithResult " + id;
    }
}


