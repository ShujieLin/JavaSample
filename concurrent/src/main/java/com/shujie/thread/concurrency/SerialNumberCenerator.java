package com.shujie.thread.concurrency;

/**
 * @author linshujie
 */
public class SerialNumberCenerator {
    private static volatile int serialNumber = 0;

    public static /*synchronized*/ int nexSerialNumber() {
        return serialNumber++;//非线程安全
    }
}
