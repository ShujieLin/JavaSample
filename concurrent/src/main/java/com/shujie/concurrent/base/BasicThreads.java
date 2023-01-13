package com.shujie.concurrent.base;

/**
 * @author linshujie
 */
public class BasicThreads {
    public static void main(String[] args) {
        Thread t = new Thread(new LitfOff());
        t.start();
        System.out.println("waiting for liftoff");
    }
}
