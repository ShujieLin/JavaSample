package com.shujie.concurrent.base;

/**
 * @author linshujie
 */
public class Yield {
    public static void main(String[] args) {
        LitfOff litfOff = new LitfOff();
        litfOff.run();//直接由分配给main()的线程执行
    }
}

//发射
class LitfOff implements Runnable {
    protected int cownDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LitfOff() {

    }

    public LitfOff(int cownDown) {
        this.cownDown = cownDown;
    }

    //run()并没有线程能力，不会产生内在的线程能力，要实现线程行为，必须显式地将一个任务附着到线程上。
    @Override
    public void run() {
        while (cownDown-- > 0) {
            System.out.println(status());
            Thread.yield();//将cpu从一个线程转移给另外一个线程的一种建议，表明：”我已经执行完生命周期最重要的部分了，此刻是切换给其它任务执行的好时机“
        }
    }

    protected String status() {
        return "#" + id + "(" + (cownDown > 0 ? cownDown : "Liftoff!") + "),";
    }
}
