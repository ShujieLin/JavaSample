package com.shujie.thread.concurrency;

/**
 * @author linshujie
 */
// The producer-consumer approach to task cooperation.

import java.util.concurrent.*;

class Meal {
    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    public String toString() {
        return "Meal " + orderNum;
    }
}

class WaitPerson implements Runnable {
    private final Restaurant restaurant;

    public WaitPerson(Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null)
                        wait(); // ... for the chef to produce a meal
                }
                System.out.println("Waitperson got " + restaurant.meal);
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll(); // Ready for another
                }
            }
        } catch (InterruptedException e) {
            System.out.println("WaitPerson interrupted");
        }
    }
}

class Chef implements Runnable {
    private final Restaurant restaurant;
    private int count = 0;

    public Chef(Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {//整个run方法都被放到try语句中，使得可以有序的关闭。如果收到中断异常，可以捕获异常之后结束
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null)
                        wait(); // ... for the meal to be taken
                }
                if (++count == 10) {
                    System.out.println("Out of food, closing");
                    //任务没有在获取interrupt()之后立刻关闭，因为当任务试图进入下一个可中断阻塞操作时候，这个中断只能抛出中断异常。因此可以打印"Order up! "。在试图调用sleep的时候
                    //抛出异常。如果移除sleep方法，则会回到循环，在Thread.interrupted()出退出，同时不抛出异常。
                    restaurant.exec.shutdownNow();
                }
                System.out.println("Order up! ");
                synchronized (restaurant.waitPerson) {//要执行做饭并notifyAll，就得先获取waitPerson对象的锁。waitPerson对象在执行run的时候，wait释放了waitPerson对象锁，因此得以继续执行。
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}

public class Restaurant {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    final Chef chef = new Chef(this);
    final WaitPerson waitPerson = new WaitPerson(this);

    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}