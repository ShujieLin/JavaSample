package com.lsj.reflect.parametertype;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @description:
 *
 * @date: 2022/2/23
 * @author: linshujie
 */

/**
 * ParameterizedType
 * 具体的泛型类型, 如Map<String, String>
 * 有如下方法:
 * <p>
 * Type getRawType(): 返回承载该泛型信息的对象, 如上面那个Map<String, String>承载范型信息的对象是Map
 * Type[] getActualTypeArguments(): 返回实际泛型类型列表, 如上面那个Map<String, String>实际范型列表中有两个元素, 都是String
 */
public class TestClass {
    Map<String,String> map;

    public static void main(String[] args) throws Exception {
        Field field = TestClass.class.getDeclaredField("map");
        System.out.println(field.getGenericType());

        ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
        System.out.println(parameterizedType.getRawType());

        for (Type type :
                parameterizedType.getActualTypeArguments()) {
            System.out.println(type);
        }
    }
}
