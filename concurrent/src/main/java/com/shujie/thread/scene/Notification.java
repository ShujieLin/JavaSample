package com.shujie.thread.scene;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author linshujie
 */
public class Notification {


    public static void main(String[] args) {
        System.out.println("main thread start");
        final TaskEntity taskEntity = new TaskEntity();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread start");
                Task task = new Task();
                task.process(taskEntity);
            }
        });
        exec.shutdown();

        System.out.println("main thread begin to sleep");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("e = " + e);
        }

        System.out.println("main thread wake up and notofy all");
        synchronized (Task.transCompleteLock){
            taskEntity.setIsGotTransCompleteReq(true);
            Task.transCompleteLock.notifyAll();
        }


    }
}
