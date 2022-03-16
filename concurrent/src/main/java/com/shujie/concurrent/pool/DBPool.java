package com.shujie.concurrent.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by linshujie on 2022/3/16 22:37.
 * 数据库连接池的实现：
 * 生产者-消费者模式，从数据库连接池中拿连接，用完之后返回池中
 * 等待时间内，假如拿到连接则返回
 * 若超过规定时长，则抛出超时异常（这里为了简约直接返回null）。
 */
public class DBPool {
    //容器，存放正在使用的连接
    private static LinkedList<Connection> pool = new LinkedList<>();

    public DBPool(int initialSize){
        if (initialSize > 0){
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    /**
     * 释放连接,通知其他的等待连接的线程
     * @param connection
     */
    public void releaseConnection(Connection connection){
        if (connection != null){
            synchronized (pool){
                //用完，放回到LinkedList的尾巴中
                pool.addLast(connection);
                //通知其他等待连接的线程
                pool.notifyAll();
            }
        }
    }

    /**
     * 获取连接
     * @param mills 超时时间
     * @return
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool){
            //永不超时
            if (mills <= 0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                //返回LinkedList的第一个连接
                return pool.removeFirst();
            }else {
                //超时时刻
                long future = System.currentTimeMillis() + mills;
                //等待时长
                long remaining = mills;
                while (pool.isEmpty()
                        && remaining > 0){
                    //wait的时候会把锁释放掉，notifyAll的时候唤醒，所有等待的线程去抢锁，抢到的线程进入方法
                    //在wait时间以内，假如被唤醒的话
                    pool.wait(remaining);
                    //唤醒一次，重新计算等待时长
                    remaining = future - System.currentTimeMillis();
                }

                //假如pool不为空，直接返回连接
                //假如remaining < 0 ,直接返回Null
                Connection connection = null;
                if (!pool.isEmpty()){
                    //返回LinkedList的第一个连接
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }

    }

}
