package com.lsj.proxy;


import com.lsj.proxy.makecomputer.ComputerCustomizedFactory;
import com.lsj.proxy.makecomputer.IComputerFactory;
import com.lsj.proxy.makephone.IPhoneFactory;
import com.lsj.proxy.makephone.PhoneCustomizedFactory;
import com.lsj.proxy.proxy.dynamic.CustomizedCompany;
import com.lsj.proxy.utils.ProxyUtils;

/**
 * @description:
 * 用户自己
 *
 * @date: 2022/2/12
 * @author: linshujie
 */
public class Me {
    public static void main(String[] args) {
//        staticProxy();
        dynamicProxy();
    }

    /**
     * 一：静态代理模式
     */
//    private static void staticProxy() {
//        //正真执行制造工艺的工厂
//        IPhoneFactory phoneFactory = new PhoneCustomizedFactory();
//        //淘宝手机定制代理商张三
//        ZhangSan zhangSan = new ZhangSan(phoneFactory);
//        zhangSan.makePhoneDesign("蜘蛛侠主题");
//
//        IComputerFactory computerCustomizedFactory = new ComputerCustomizedFactory();
//        //淘宝电脑代理商老王
//        LaoWang laoWang = new LaoWang(computerCustomizedFactory);
//        laoWang.makeComputerDesign("钢铁侠主题");
//    }

    /**
     * 二：动态代理模式
     */
    private static void dynamicProxy() {
        //创建自定义设计公司，负责各种代理定制服务，包括手机、电脑、手表、平板等电子设备
        CustomizedCompany company = new CustomizedCompany();

        //手机定制工厂
        IPhoneFactory phoneCustomizedFactory = new PhoneCustomizedFactory();
        company.setFactory(phoneCustomizedFactory);
        //动态生成负责手机业务的员工
        IPhoneFactory employeePhone = (IPhoneFactory) company.getProxyInstance();
        employeePhone.makePhoneDesign("超人主题");

        //电脑定制工厂
        IComputerFactory computerFactory = new ComputerCustomizedFactory();
        company.setFactory(computerFactory);
        //动态生成负责笔记本业务的员工
        IComputerFactory employeeComputer = (IComputerFactory) company.getProxyInstance();
        employeeComputer.makeComputerDesign("美国队长主题");

        //生成代理class
//        ProxyUtils.generateClassFile(phoneCustomizedFactory.getClass(),
//                employeePhone.getClass().getSimpleName());
//        ProxyUtils.generateClassFile(computerFactory.getClass(),
//                employeeComputer.getClass().getSimpleName());
    }


}
