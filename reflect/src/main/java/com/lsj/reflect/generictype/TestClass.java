package com.lsj.reflect.generictype;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.util.List;

/**
 * @description:
 * @date: 2022/2/23
 * @author: linshujie
 */

/**
 * GenericArrayType
 * 泛型数组,组成数组的元素中有范型则实现了该接口; 它的组成元素是ParameterizedType或TypeVariable类型,它只有一个方法:
 * Type getGenericComponentType(): 返回数组的组成对象
 */
public class TestClass{
    List<String>[] lists;
    public static void main(String[] args) throws Exception {
        Field field = TestClass.class.getDeclaredField("lists");
        GenericArrayType genericType = (GenericArrayType) field.getGenericType();
        System.out.println(genericType.getGenericComponentType());
    }
}
