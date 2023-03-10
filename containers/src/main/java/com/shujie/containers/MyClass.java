package com.shujie.containers;

import java.lang.ref.WeakReference;

public class MyClass {

    public static void main(String[] args) {
        VeryBig a = new VeryBig("1");


        WeakReference<VeryBig> wa = new WeakReference<>(new VeryBig("2"));
       /* for (int i = 0; i < 10000; i++) {
            new WeakReference<>(new VeryBig(String.valueOf(i)));
        }*/
        System.gc();
        /*System.out.println("a = " + a);*/
        /*System.out.println("b = " + b);*/

    }
}