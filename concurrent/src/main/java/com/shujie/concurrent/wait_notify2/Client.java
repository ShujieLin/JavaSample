package com.shujie.concurrent.wait_notify2;

/**
 * @author linshujie
 */
public class Client {


    public static void main(String[] args) {
        Transaction transaction = new Transaction();
        Task task = new Task(transaction);
        task.start();
        //唤醒正在阻塞等待上位机回复数据的线程
        synchronized (Task.transCompleteLock) {
            transaction.setGotTransCompleteReq(true);
            Task.transCompleteLock.notifyAll();
        }
    }
}