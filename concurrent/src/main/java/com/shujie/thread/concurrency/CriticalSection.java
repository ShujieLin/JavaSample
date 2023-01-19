//package com.shujie.thread.concurrency;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * @author linshujie
// */
//
//
//class Pair {
//    private int x, y;
//
//    public Pair(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public Pair() {
//        this(0, 0);
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public void incrementX() {
//        x++;
//    }
//
//    public void incrementY() {
//        y++;
//    }
//
//    @Override
//    public String toString() {
//        return "Pair{" +
//                "x=" + x +
//                ", y=" + y +
//                '}';
//    }
//
//
//    public class PairValueNotEqualException extends RuntimeException {
//        public PairValueNotEqualException() {
//            super("pair values not equal :" + Pair.this);
//        }
//    }
//
//    public void checkState() {
//        if (x != y) {
//            throw new PairValueNotEqualException();
//        }
//    }
//
//    abstract class PairManager {
//        AtomicInteger checkCounter = new AtomicInteger(0);
//        protected Pair pair = new Pair();
//        private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());
//
//        public synchronized Pair getPair() {
//            return new Pair(pair.getX(), pair.getY());
//        }
//
//        protected void store(Pair pair) {
//            storage.add(pair);
//            try {
//                TimeUnit.MILLISECONDS.sleep(50);
//            } catch (InterruptedException e) {
//                System.out.println("e = " + e);
//            }
//        }
//
//        public abstract void increment();
//    }
//
//    class PairManager1 extends PairManager {
//
//        @Override
//        public synchronized void increment() {
//            pair.incrementX();
//            pair.incrementY();
//            store(getPair());
//        }
//    }
//
//    class PairManager2 extends PairManager {
//
//        @Override
//        public void increment() {
//            Pair temp;
//            synchronized (this) {
//                pair.incrementX();
//                pair.incrementY();
//                temp = getPair();
//            }
//            store(getPair());
//        }
//    }
//
//    //操纵器
//    class PairManipulator implements Runnable {
//        private PairManager pairManager;
//
//        public PairManipulator(PairManager pairManager) {
//            this.pairManager = pairManager;
//        }
//
//        @Override
//        public void run() {
//            while (true) {
//                pairManager.increment();
//            }
//        }
//
//        @Override
//        public String toString() {
//            return "Pair : + " + pairManager.getPair() + " checkCounter = " + pairManager.checkCounter.get();
//        }
//    }
//
//
//    class PairChecker implements Runnable {
//        PairManager pairManager;
//
//        public PairChecker(PairManager pairManager) {
//            this.pairManager = pairManager;
//        }
//
//        @Override
//        public void run() {
//            while (true) {
//                pairManager.checkCounter.incrementAndGet();
//                pairManager.getPair().checkState();
//            }
//        }
//    }
//
//
//}
//
//
//public class CriticalSection {
//     void testApproaches(Pair.PairManager pairManager1, Pair.PairManager pairManager2) {
//        ExecutorService exec = Executors.newCachedThreadPool();
//        Pair.PairManipulator
//                pairManipulator1 = new Pair.PairManipulator(pairManager1),
//                pairManipulator2 = new Pair.PairManipulator(pairManager2);
//
//        Pair.PairChecker
//                pairChecker1 = new Pair.PairChecker(pairManager1),
//                pairChecker2 = new Pair.PairChecker(pairManager2);
//        exec.execute(pairManipulator1);
//        exec.execute(pairManipulator2);
//        exec.execute(pairChecker1);
//        exec.execute(pairChecker2);
//
//        try {
//            TimeUnit.MILLISECONDS.sleep(500);
//        } catch (InterruptedException e) {
//            System.out.println("e = " + e);
//        }
//
//        System.out.println("pairManipulator1 " + pairManipulator1 + "\n  pairManipulator2" + pairManipulator2);
//        System.exit(0);
//    }
//
//    public static void main(String[] args) {
//        Pair.PairManager
//                pairManager1 = new Pair.PairManager1(),
//                pairManager2 = new Pair.PairManager2();
//
//        testApproaches(pairManager1, pairManager2);
//    }
//}