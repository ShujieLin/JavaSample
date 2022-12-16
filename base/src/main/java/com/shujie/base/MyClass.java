package com.shujie.base;

public class MyClass {
    public static void main(String[] args) {
//        byte b = Byte.parseByte("F");
//        System.out.println(b);

        byte a = (byte)234;
        int i = a;
        i = a&0xff;
        System.out.println(i);
    }
}