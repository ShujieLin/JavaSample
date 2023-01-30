package com.shujie.thread.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author linshujie
 */
public class OrnamentalGarden {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Entrance(i));
        }

        TimeUnit.MILLISECONDS.sleep(3000);
        Entrance.cancel();
        exec.shutdown();

        if (!exec.awaitTermination(250,TimeUnit.MICROSECONDS)){
            System.out.println("还有任务未终止");
        }


        System.out.println("total：" + Entrance.getTotal());
        System.out.println("sum of entrances ： " + Entrance.sumEntrance());

    }
}

class Entrance implements Runnable {
    private static final Count count = new Count();
    private static final List<Entrance> entranceList = new ArrayList<>();
    private static volatile boolean canceled = false;
    private int number = 0;
    private final int id;

    public Entrance(int id) {
        this.id = id;
        entranceList.add(this);
    }

    public static void cancel() {
        canceled = true;
    }

    public static Object getTotal() {
        return count.value();
    }

    public static int sumEntrance() {
        int sum = 0;
        for (Entrance entrance :
                entranceList) {
            sum += entrance.getValue();
        }
        return sum;
    }

    @Override
    public void run() {
        while (!canceled){
            //每个门口进来人数++
            synchronized (this){
                number ++;
            }
            //共享变量人数++
            System.out.println(this + " total :" + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("睡眠中断");
            }
        }
        System.out.println("停止" + this);
    }

    @Override
    public String toString() {
        return "Entrance " + id + " : " + getValue();
    }

    private synchronized int getValue() {
        return number;
    }
}

/**
 * 共享访问对象
 */
class Count{
    //花园参观者计数器
    private static int count;

    public synchronized  int value(){
        return count;
    }

    public synchronized Object increment() {
        int temp = count;
        Random random = new Random(47);
        if (random.nextBoolean()) Thread.yield();//让一半的时间都在给其他线程让步
        return (count = ++ temp);
    }
}