package com.shujie.thread.base.dead_lock;

/**
 * File description
 *
 * @author linshujie
 * @date 2022/3/28
 */
public class NormalDeadLock {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }

    private static class Thread1 extends Thread{
        @Override
        public void run() {
            try {
                doSomeThing1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doSomeThing1() throws InterruptedException {
            synchronized (lock1){
                System.out.println(Thread.currentThread().getName() + " get lock1 ");
                Thread.sleep(100);
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + " get lock2");
                }
            }
        }
    }

    private static class Thread2 extends Thread{
        @Override
        public void run() {
            try {
                doSomeThing2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doSomeThing2() throws InterruptedException {
            synchronized (lock1){
                System.out.println(Thread.currentThread().getName() + " get lock1 ");
                Thread.sleep(100);
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + " get lock2");
                }
            }
        }
    }
}
