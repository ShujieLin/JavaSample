package com.shujie.generic.tuple;

/**
 * 仅 一次 方法 调用 就能 返回 多个 对象， 你 应该 经常 需要 这样 的 功能 吧。 可是 return 语句 只 允许 返回 单个 对象， 因此， 解决 办法 就是 创建 一个 对象，
 * 用 它来 持有 想要 返回 的 多个 对象。 当然， 可以 在 每次 需要 的 时候， 专门 创建 一个 类 来 完成 这样 的 工作。 可是 有了 泛 型， 我们 就 能够 一次 性地
 * 解决 该 问题， 以后 再也不 用在 这个 问题 上 浪费时间 了。 同时， 我们 在 编译 期 就能 确保 类型 安全。 这个 概念 称为 元 组（ tuple）， 它是 将 一组 对象
 * 直接 打包 存储 于 其中 的 一个 单一 对象。 这个 容器 对象 允许 读取 其中 元素， 但是 不允许 向 其中 存放 新的 对象。（ 这个 概念 也称 为数 据传 送 对象， 或 信使。）
 *
 * Bruce Eckel. Java编程思想（第4版） (计算机科学丛书，Java学习必读经典,殿堂级著作！赢得了全球程序员的广泛赞誉！) (Kindle 位置 7290-7296). Kindle 版本.
 * @author linshujie
 */
public class TwoTuple<A,B>{
    /**
     * 第一次 阅读 上面 的 代码 时， 你也 许 会想， 这不 是 违反 了 Java 编程 的 安全性 原则 吗？ first 和 second 应该 声明 为 private， 然后 提供 getFirst()
     * 和 getSecond() 之类 的 访问 方法 才 对呀？ 让我 们 仔细 看看 这个 例子 中的 安全性： 客户 端 程序 可以 读取 first 和 second 对象， 然后 可以 随心所欲 地
     * 使用 这 两个 对象。 但是， 它们 却 无法 将 其他 值 赋予 first 或 second。 因为 final 声明 为你 买了 相同 的 安全 保险， 而且 这种 格式 更 简洁 明了。
     */
    public final A first;
    public final B second;

    public TwoTuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "TwoTuple{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}