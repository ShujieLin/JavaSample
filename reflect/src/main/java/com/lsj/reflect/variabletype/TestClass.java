package com.lsj.reflect.variabletype;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * @description:
 * 当我们对一个泛型类进行反射时，需要得到泛型中的真实数据类型，来完成如json反序列化的操作。此时需要通
 * 过 Type 体系来完成。
 * TypeVariable
 * 泛型类型变量。可以泛型上下限等信息；
 * @date: 2022/2/23
 * @author: linshujie
 */

/**
 *
 * @param <K> key
 * @param <V> value
 */
public class TestClass<K extends Comparable & Serializable,V> {
    K key;
    V value;

    public static void main(String[] args) throws NoSuchFieldException {
        Field fieldKey = TestClass.class.getDeclaredField("key");
        Field fieldVaule = TestClass.class.getDeclaredField("value");

        TypeVariable keyType = (TypeVariable) fieldKey.getGenericType();
        TypeVariable valueType = (TypeVariable) fieldVaule.getGenericType();

        System.out.println("getName方法：");
        System.out.println(keyType.getName());
        System.out.println(valueType.getName());

        System.out.println("getGenericDeclaration方法：");
        System.out.println(keyType.getGenericDeclaration());
        System.out.println(valueType.getGenericDeclaration());

        System.out.println("K 的上界：");
        for (Type type :
                keyType.getBounds()) {
            System.out.println(type);
        }
        System.out.println("V 的上界：");
        for (Type type :
                valueType.getBounds()) {
            System.out.println(type);
        }
    }
}
