package com.lsj.proxy.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * 定制服务公司类，作为代理的入口。根据传入的接口，动态生成代理对象，无需手动在代理里面new出具体的代理对象
 * @date: 2022/2/12
 * @author: linshujie
 */
public class CustomizedCompany {
    /**
     * 待传入的工厂，真正负责执行定制的工作
     */
    private Object factory;

    public Object getFactory() {
        return factory;
    }

    public void setFactory(Object factory) {
        this.factory = factory;
    }

    /**
     * 通过动态代理对象方法进行增强
     *
     * @return
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(factory.getClass().getClassLoader(),
                factory.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        preSalesService();
                        Object result = method.invoke(factory, args);
                        afterSalesService();
                        return result;
                    }
                });
    }

    private void preSalesService(){
        System.out.println("免费咨询！！！");
    }

    private void afterSalesService(){
        System.out.println("三年保修服务！！！");
    }
}
