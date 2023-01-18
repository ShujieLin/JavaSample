package com.shujie.thread.wait_notify2;

/**
 * @author linshujie
 */
public class Transaction {
    //是否接收到来自上位机TransComplete请求
    private volatile boolean[] isGotTransCompleteReq = new boolean[]{false};

    /**
     * 是否接收到上位机发送的交易完成请求
     */
    public boolean[] isGotTransCompleteReq() {
        return isGotTransCompleteReq;
    }

    /**
     * 是否接收到上位机发送的交易完成请求
     */
    public void setGotTransCompleteReq(boolean gotTransCompleteReq) {
        isGotTransCompleteReq[0] = gotTransCompleteReq;
    }
}
