package com.shujie.thread.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author linshujie
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        //FixedThreadPool可以一次性预先执行代价高昂贵的线程分配，因为使用的Thread对象的数量是有限的，因此可以限制线程的数量。这可以节约时间，因为不必为每个任务固定地付出创建线程的开销。
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LitfOff());
        }
        executorService.shutdown();
    }
}