package com.shujie.concurrent.base;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author linshujie
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        //CachedThreadPool在程序执行过程中通常会创建与所需数量相同的线程，然后在回收线程时候停止创建新线程。因此它是合理Executor的首选。
        ExecutorService executorService = Executors.newCachedThreadPool();//ExecutorService式具有生命周期的Executor，例如可以关闭
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LitfOff());
        }

        executorService.shutdown();//shutdown可以防止新任务被提交给这个Executor，当前线程会继续允许所有shutdown之前的任务。
    }
}
