package com.lsj.reflect.genericarraytype;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * @description:
 * @date: 2022/2/23
 * @author: linshujie
 */
public class TestClass {
    private List<? extends Number> listA;
    private List<? super String> listB;

    public static void main(String[] args) throws Exception {
        Field fieldA = TestClass.class.getDeclaredField("listA");
        Field fieldB = TestClass.class.getDeclaredField("listB");

        ParameterizedType parameterizedTypeA = (ParameterizedType) fieldA.getGenericType();
        ParameterizedType parameterizedTypeB = (ParameterizedType) fieldB.getGenericType();

        WildcardType wildcardTypeA = (WildcardType) parameterizedTypeA.getActualTypeArguments()[0];
        WildcardType wildcardTypeB = (WildcardType) parameterizedTypeB.getActualTypeArguments()[0];

        System.out.println(wildcardTypeA.getUpperBounds()[0]);
        System.out.println(wildcardTypeB.getLowerBounds()[0]);

        System.out.println(wildcardTypeA);
        System.out.println(wildcardTypeB);
    }
}
