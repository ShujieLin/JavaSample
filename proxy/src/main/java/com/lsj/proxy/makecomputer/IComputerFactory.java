package com.lsj.proxy.makecomputer;

/**
 * @description:
 * 加工笔记本的工厂
 * @date: 2022/2/12
 * @author: linshujie
 */
public interface IComputerFactory {
    /**
     * 进行笔记本的定制设计
     * @param design
     */
    void makeComputerDesign(String design);
}
