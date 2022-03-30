package com.lsj.proxy.makecomputer;

/**
 * @description:
 * @date: 2022/2/12
 * @author: linshujie
 */
public class ComputerCustomizedFactory implements IComputerFactory{
    @Override
    public void makeComputerDesign(String design) {
        System.out.println("给电脑定制完毕，主题为：" + design);
    }
}
