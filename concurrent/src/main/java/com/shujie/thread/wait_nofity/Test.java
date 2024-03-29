package com.shujie.thread.wait_nofity;

/**
 * Created by linshujie on 2022/3/26.
 */
public class Test {
    private static Express express = new Express(0,Express.CITY);

    /**
     * 检查里程数变化的线程,不满足条件，线程一直等待
     */
    private static class CheckKm extends Thread{
        @Override
        public void run() {
            express.waitKm();
        }
    }

    /**
     * 检查地点变化的线程,不满足条件，线程一直等待
     */
    private static class CheckSite extends Thread{
        @Override
        public void run() {
            express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<3;i++){
            new CheckSite().start();
        }
        for(int i=0;i<3;i++){
            new CheckKm().start();
        }

        Thread.sleep(1000);
//        express.changeKm();//快递地点变化
        express.changeSite();
    }
}
