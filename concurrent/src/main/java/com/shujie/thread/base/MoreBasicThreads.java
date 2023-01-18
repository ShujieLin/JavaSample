package com.shujie.thread.base;

/**
 * @author linshujie
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LitfOff()).start();
        }
    }
}
