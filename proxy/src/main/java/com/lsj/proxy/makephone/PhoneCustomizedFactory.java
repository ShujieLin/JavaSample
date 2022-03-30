package com.lsj.proxy.makephone;

/**
 * @description:
 * @date: 2022/2/12
 * @author: linshujie
 */
public class PhoneCustomizedFactory implements IPhoneFactory{
    @Override
    public void makePhoneDesign(String design) {
        System.out.println("给手机定制完毕，主题为：" + design);
    }
}
