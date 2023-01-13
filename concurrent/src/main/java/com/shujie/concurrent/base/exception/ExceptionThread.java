package com.shujie.concurrent.base.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author linshujie
 */
public class ExceptionThread implements Runnable{
    public static void main(String[] args) {

        //即使通过try catch也不能
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public void run() {
        //由于线程的本质，使得不能捕捉从线程逃逸的异常，一旦异常逃出任务的run方法，它就会向外传播到控制台，但是可以通过Executor来解决这个问题。
        throw new RuntimeException();
    }
}