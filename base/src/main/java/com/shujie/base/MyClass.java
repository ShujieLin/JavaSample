package com.shujie.base;

import static jdk.nashorn.internal.objects.Global.print;

public class MyClass {
    public static void main(String[] args) {
        /*byte a = (byte)234;
        System.out.println(a &0xff);*/

       /* int i = 0;
        System.out.println("i = " + i);
        i ++;
        System.out.println("i = " + i);
        ++ i;
        System.out.println("i = " + i);

        System.out.println("i++ = " + i++);
        System.out.println("++i = " + ++i);*/

        urShift();
    }

    /**
     * 移位 操作 符 只可 用来 处理 整数 类型（ 基本 类型 的 一种）。 左移 位 操作 符（<<） 能按 照 操作 符 右侧 指定 的 位数 将 操作 符 左边 的 操作 数 向左
     * 移动（ 在 低 位 补 0）。
     * “ 有 符号” 右移 位 操作 符（>>） 则 按照 操作 符 右侧 指定 的 位数 将 操作 符 左边 的 操作 数 向右 移动。
     * “ 有 符号” 右移 位 操作 符 使用“ 符号 扩展”： 若 符号为 正， 则在 高位 插入 0； 若 符号为 负， 则在 高位 插入 1。 Java 中 增加 了 一种“ 无符号” 右移 位 操作 符（>>>），
     * 它 使用“ 零 扩展”： 无论 正负， 都在 高位 插入 0。 这一 操作 符 是 C 或 C++ 中 所 没有 的。

     */
    private static void urShift() {
        int i = 10000;
        System.out.println("s = " + Integer.toBinaryString(i));
        i = i << 2;
        System.out.println("s = " + Integer.toBinaryString(i));

        int i2 = 100;
        System.out.println("s = " + Integer.toBinaryString(i2));
        i2 = i2 >> 1;
        System.out.println("s = " + Integer.toBinaryString(i2));

        i2 >>>= 1;
        System.out.println("s = " + Integer.toBinaryString(i2));
    }
}