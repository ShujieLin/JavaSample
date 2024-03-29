package com.shujie.thread.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author linshujie
 */
public class AtomicEvenGenerator extends IntGenerator{
    private AtomicInteger currentEventValue = new AtomicInteger(0);

    public static void main(String[] args) {
        EvenChecker.test(new AtomicEvenGenerator());
    }

    @Override
    public int next() {
        return currentEventValue.addAndGet(2);
    }
}
