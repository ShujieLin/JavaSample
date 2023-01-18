package com.shujie.thread.base.interrupt_thread;

/**
 * Created by linshujie on 2022/3/25.
 */
public class EndRunnable {
    private static class UseRunnable implements Runnable{
        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName()
                        + " I am implements Runnable.");
            }
            System.out.println(Thread.currentThread().getName()
                    +" interrupt flag is "+Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseRunnable useRunnable = new UseRunnable();
        Thread endThread = new Thread(useRunnable,"endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }
}
