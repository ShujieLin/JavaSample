package com.shujie.thread.concurrency;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * @author linshujie
 */
public class CircularSet {
    private int[] array;
    private int len;
    private int index;

    public CircularSet(int size) {
        array = new int[size];
        len = size;
        for (int i = 0; i < size; i++) {
            array[i] = -1;
        }
    }

    public synchronized void add(int i) {
        array[index] = i;
        index = ++index % len;
        System.out.println("array["+index + "] = " + array[index]);
    }

    public synchronized boolean contains(int val) {
        for (int i = 0; i < len; i++)
            if (array[i] == val) return true;
        return false;
    }

}

class SerialNumberChecker{
    private static final int SIZE = 10;
    private static CircularSet serials = new CircularSet(1000);
    private static ExecutorService exec = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < SIZE; i++) {
            System.out.println("args = " + Arrays.toString(args));
            exec.execute(new SerialChecker());
            if (args.length > 0){
                TimeUnit.SECONDS.sleep(new Integer(args[0]));
                System.exit(0);
            }
        }
    }

    static class SerialChecker implements Runnable{
        @Override
        public void run() {
            while (true){
                int serial = SerialNumberCenerator.nexSerialNumber();
                if (serials.contains(serial)){
                    System.out.println("重复:" + serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }
}
