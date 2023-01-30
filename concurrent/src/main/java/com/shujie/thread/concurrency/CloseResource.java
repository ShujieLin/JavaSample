package com.shujie.thread.concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author linshujie
 */
public class CloseResource {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8080);
        InputStream socketInput = new Socket("localhost", 8080).getInputStream();
        exec.execute(new IOBlocked(socketInput));
        exec.execute(new IOBlocked(System.in));

        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("shutting down all thread");
        exec.shutdownNow();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("closing " + socketInput.getClass().getSimpleName());
        socketInput.close();//释放被阻塞的线程

        TimeUnit.SECONDS.sleep(1);
        System.out.println("closing " + System.in.getClass().getSimpleName());
        System.in.close();//释放被阻塞的线程
    }
}
