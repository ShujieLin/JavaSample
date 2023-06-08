package com.shujie.thread.vola;

/**
 * @author linshujie
 */
public class VolatileOrderingDemo {
    private int dataA = 0;
    private long dataB = 0L;
    private String dataC = null;
    private volatile boolean ready = false;

    public static void main(String[] args) {

    }

    public void writer() {
        dataA = 1;
        dataB = 10000L;
        dataC = "Content...";
        ready = true;
    }

    /**
     * 上述 第 2 行情 形的 出现 次数 总是 为 0。 这 说明 当 reader 方法 的 执行 线程 读取 到 ready 的 值 为 true 时， 该 线程 所 读取 到 的 其他
     * 共享 变量 的 值 必然 是 writer 方法 的 执行 线程 更新 之 后的 值。 这里， volatile 变量 ready 使得 reader 方法 的 执行 线程 对 writer
     * 方法 的 执行 线程 所 执行 操作 的 感知 顺序 与 源 代码 顺序 一致。
     *
     * 黄文海. Java多线程编程实战指南（核心篇） (Java多线程编程实战系列) (Kindle 位置 2131-2134). 电子工业出版社. Kindle 版本.
     * @return
     */
    public int reader() {
        int result = 0;
        boolean allISOK;

        if (ready) {
            allISOK = (1 == dataA) && (10000L == dataB) && "Content...".equals(dataC);
            result = allISOK ? 1 : 2;
        } else {
            result = 3;
        }
        return result;
    }

}
