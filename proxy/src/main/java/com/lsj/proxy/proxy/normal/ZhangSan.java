package com.lsj.proxy.proxy.normal;


import com.lsj.proxy.makephone.IPhoneFactory;

/**
 * @description:
 * 静态代理类
 *
 * @date: 2022/2/12
 * @author: linshujie
 */
public class ZhangSan implements IPhoneFactory {
    public IPhoneFactory phoneFactory;

    public ZhangSan(IPhoneFactory phoneFactory) {
        this.phoneFactory = phoneFactory;
    }

    private void preSalesService(){
        System.out.println("免费咨询！！！");
    }

    private void afterSalesService(){
        System.out.println("三年保修服务！！！");
    }

    @Override
    public void makePhoneDesign(String design) {
        preSalesService();
        phoneFactory.makePhoneDesign(design);
        afterSalesService();
    }
}
