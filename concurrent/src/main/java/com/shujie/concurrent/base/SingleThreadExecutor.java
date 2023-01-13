package com.shujie.concurrent.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author linshujie
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        //SingleThreadExecutor是线程数量为1的FixThreadPool，这对于在另外一个线程需要长期存活的任务来说都是很有用的。如果向它提交多个任务，任务将排队。
        //每个任务都会在下一个任务开始之前结束，所有任务将使用相同的线程。
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LitfOff());
        }
        executorService.shutdown();
    }
}
