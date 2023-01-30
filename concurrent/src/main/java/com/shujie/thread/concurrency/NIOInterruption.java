package com.shujie.thread.concurrency;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author linshujie
 */
public class NIOInterruption {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8080);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8080);
        SocketChannel socketChannel1 = SocketChannel.open(inetSocketAddress);
        SocketChannel socketChannel2 = SocketChannel.open(inetSocketAddress);

        //运行
        Future<?> future = exec.submit(new NIOBlocked(socketChannel1));
        //运行
        exec.execute(new NIOBlocked(socketChannel2));
        exec.shutdown();

        TimeUnit.SECONDS.sleep(1);
        //通过cancle提供中断
        future.cancel(true);

        TimeUnit.SECONDS.sleep(1);
        //通过关闭通道解除阻塞
        socketChannel2.close();
    }
}

class NIOBlocked implements Runnable{
    private final SocketChannel socketChannel;

    public NIOBlocked(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        System.out.println("waiting for read() in " + this);
        try {
            socketChannel.read(ByteBuffer.allocate(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("exiting NIOBlocked.run() " + this);
    }
}