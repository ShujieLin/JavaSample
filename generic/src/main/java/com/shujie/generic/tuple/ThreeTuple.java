package com.shujie.generic.tuple;

/**
 * 即 你 确实 希望 允许 客户 端 程序员 改变 first 或 second 所 引用 的 对象。 然而， 采用 以上 的 形式 无疑 是 更 安全 的 做法，
 * 这样 的 话， 如果 程序员 想要 使用 具有 不同 元素 的 元 组， 就 强制 要求 他们 另外 创建 一个 新的 TwoTuple 对象。
 * 我们 可以 利用 继承 机制 实现 长度 更 长的 元 组。 从下 面的 例子 中 可以 看到， 增加 类型 参数 是 件 很 简单 的 事情：
 * <p>
 * Bruce Eckel. Java编程思想（第4版） (计算机科学丛书，Java学习必读经典,殿堂级著作！赢得了全球程序员的广泛赞誉！) (Kindle 位置 7306-7308). Kindle 版本.
 *
 * @author linshujie
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
    public final C third;

    public ThreeTuple(A first, B second, C third) {
        super(first, second);
        this.third = third;
    }

    @Override
    public String toString() {
        return "ThreeTuple{" +
                "third=" + third +
                ", first=" + first +
                ", second=" + second +
                '}';
    }
}
