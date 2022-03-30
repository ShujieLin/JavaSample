package com.lsj.proxy.makephone;

/**
 * @description:
 * 加工手机的工厂
 * @date: 2022/2/12
 * @author: linshujie
 */
public interface IPhoneFactory {
    /**
     * 进行手机的定制设计
     * @param design
     */
    void makePhoneDesign(String design);
}
