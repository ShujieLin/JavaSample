package com.shujie.thread.wait_notify2;

/**
 * @author linshujie
 */
public class Task {
    //用于阻塞Task执行线程的锁
    public static final Object transCompleteLock = new Object();

    Transaction transaction;

    public Task(Transaction transaction) {
        this.transaction = transaction;
    }

    public void start(){
        boolean isGotCompleteRequest = waitForTransCompleteReq(15 * 1000, transaction.isGotTransCompleteReq());

        if (isGotCompleteRequest){
            System.out.println("isGotCompleteRequest = " + isGotCompleteRequest);
        }else {
            System.out.println("isGotCompleteRequest = " + isGotCompleteRequest);
        }
    }

    /**
     * 等待上位机发送交易完成请求
     *
     * @param timeoutMs 超时时间
     * @param condition 条件
     * @return 是否成功获取上位机的交易完成请求
     */
    // FIXME: 2022/12/16 boolean类型是值引用类型，相当于深拷贝，方法体中值的修改不会影响原本的值，考虑需要修改
    private boolean waitForTransCompleteReq(int timeoutMs, boolean[] condition) {
        if (timeoutMs > 0) {
            synchronized (transCompleteLock) {
                /*long now = SystemClock.elapsedRealtime();*/
                long now = System.currentTimeMillis();
                long end = now + timeoutMs;
                while (!condition[0] && now < end) {
                    try {
                        transCompleteLock.wait(end - now);
                    } catch (InterruptedException e) {
                        System.out.println("e = " + e);
                        Thread.currentThread().interrupt();
                    }
                    /*now = SystemClock.elapsedRealtime();*/
                    now = System.currentTimeMillis();
                }
                return condition[0];   // FIXME: 2022/12/16 这里返回的依然是传递进来时的值
            }
        } else {
            throw new UnsupportedOperationException("Waitting's timeoutMs can not smaller than 0 "
                    + "or equal 0");
        }
    }
}
