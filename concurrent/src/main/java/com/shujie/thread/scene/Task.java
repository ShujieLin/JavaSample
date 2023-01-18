package com.shujie.thread.scene;

/**
 * @author linshujie
 */
public class Task {
    public static final Object transCompleteLock = new Object();

    public void process(TaskEntity taskEntity) {
        System.out.println("child thread begin to wait for notification");
        boolean isGotCompleteRequest = waitForTransCompleteReq(30 * 1000, taskEntity);
        System.out.println("child thread : isGotCompleteRequest = " + isGotCompleteRequest);
        System.out.println("child thread end");
    }

    private boolean waitForTransCompleteReq(int timeoutMs, TaskEntity taskEntity) {
        if (timeoutMs > 0) {
            synchronized (transCompleteLock) {
                long now = System.currentTimeMillis();

                long end = now + timeoutMs;
                while (!taskEntity.getIsGotTransCompleteReq() && now < end) {
                    try {
                        transCompleteLock.wait(end - now);
                    } catch (InterruptedException e) {
                        System.out.println("e = " + e);
                        Thread.currentThread().interrupt();
                    }
                    now = System.currentTimeMillis();
                }
                return taskEntity.getIsGotTransCompleteReq();
            }
        } else {
            throw new UnsupportedOperationException("Waitting's timeoutMs can not smaller than 0 "
                    + "or equal 0");
        }
    }
}
