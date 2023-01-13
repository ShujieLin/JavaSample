package com.shujie.concurrent.base.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author linshujie
 */
public class SettingDefaultHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
    }
}
