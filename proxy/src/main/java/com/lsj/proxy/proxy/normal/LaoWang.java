package com.lsj.proxy.proxy.normal;


import com.lsj.proxy.makecomputer.IComputerFactory;

/**
 * @description:
 * 静态代理类
 *
 * @date: 2022/2/12
 * @author: linshujie
 */
public class LaoWang implements IComputerFactory {
    public IComputerFactory computerFactory;

    public LaoWang(IComputerFactory computerFactory) {
        this.computerFactory = computerFactory;
    }

    private void preSalesService(){
        System.out.println("免费咨询！！！");
    }

    private void afterSalesService(){
        System.out.println("三年保修服务！！！");
    }

    @Override
    public void makeComputerDesign(String design) {
        preSalesService();
        computerFactory.makeComputerDesign(design);
        afterSalesService();
    }
}
