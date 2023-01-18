package com.shujie.thread.scene;

/**
 * @author linshujie
 */
public class TaskEntity {
    private volatile boolean isGotTransCompleteReq = false;

    public boolean getIsGotTransCompleteReq() {
        return isGotTransCompleteReq;
    }

    public void setIsGotTransCompleteReq(boolean isGotTransCompleteReq) {
        this.isGotTransCompleteReq = isGotTransCompleteReq;
    }
}
