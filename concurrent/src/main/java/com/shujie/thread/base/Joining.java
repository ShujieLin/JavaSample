package com.shujie.thread.base;

import java.util.concurrent.TimeUnit;

/**
 * @author linshujie
 */
public class Joining {
    public static void main(String[] args) {
        Sleeper sleeper1 = new Sleeper("sleeper1", 1500),
                sleeper2 = new Sleeper("sleeper2", 1500);
        Joiner joiner1 = new Joiner("joiner1",sleeper1),
                joiner2 = new Joiner("joiner2",sleeper2);
        //当sleeper2调用中断的时候，将给线程设定一个标志，表面该线程已经被中断。但是，异常被捕捉的时候会清理这个标志，所以异常捕捉里面这个标志总为false
        sleeper2.interrupt();//sleeper2调用中断方法的话，join方法就会被中断。joiner2跳过等待，直接执行
    }
}

class Sleeper extends Thread {
    private int duration;

    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }

    @Override
    public void run() {
        super.run();
        try {
            TimeUnit.MILLISECONDS.sleep(duration * 10);
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted." + "isInterrupted(): " + isInterrupted());//
            return;
        }
        System.out.println(getName() + " has awakened");
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            //当前线程被挂起，直到sleeper执行完毕
            sleeper.join();//Joiner等待Sleeper执行完毕之后再执行自己的任务
            /*sleeper.join(5000);//带上参数*/
        } catch (InterruptedException e) {
            System.out.println("e = " + e);
        }
        System.out.println(getName() + " join completed");
    }
}
