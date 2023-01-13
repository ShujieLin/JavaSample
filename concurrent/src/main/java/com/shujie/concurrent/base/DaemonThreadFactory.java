package com.shujie.concurrent.base;

import java.util.concurrent.ThreadFactory;

/**
 * 通过定制的ThreadFactory可以定制由Excutor创建的线程的属性（后台，优先级，名称）
 *
 * @author linshujie
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable runnable) {
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        return t;
    }
}