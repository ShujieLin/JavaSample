package com.shujie.thread.concurrency.waxomatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author linshujie
 */
class Car {
    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }

    public synchronized void wartingForBuffing() throws InterruptedException {
        /**
         * 必须用一个感兴趣的条件的while来包围wait()，因为：
         * 1.假如需要有多个任务在等待同一个锁。而第一个唤醒任务可能会改变这情况，这种情况下这个任务应该要再次被挂起。
         * 2.任务唤醒之后，有可能其它任务已经完成了同样的任务，使得这个任务不能执行，或者执行不执行都无关紧要。这种时候应该再次被挂起。
         * 3.某些任务由于不同的原因等待这个对象的锁，调用了notifyAll(),这种情况需要检查是否是由正确的原因唤醒，如果不是，再次调用wait()。
         *
         * 总结来说，本质就是检查感兴趣的条件，在条件不满足的条件下返回到wait中，管用方法就是使用while来写这种代码
         */
        while (waxOn) wait();
    }

    public synchronized void waitingForWaxing() throws InterruptedException {
        while (!waxOn) wait();
    }
}

class WaxOn implements Runnable {
    private final Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("wax on");
                TimeUnit.MILLISECONDS.sleep(200);

                car.waxed();
                car.wartingForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("WaxOn:exit via interrupt");
        }
        System.out.println("WaxOn:ending wax on task");
    }
}


class WaxOff implements Runnable {
    private final Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitingForWaxing();
                System.out.println("wax off");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("WaxOff:exit via interrupt");
        }
        System.out.println("WaxOff:ending wax off task");
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));//挂起
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();//这会调用所有由他控制的线程的interrupt()
    }
}