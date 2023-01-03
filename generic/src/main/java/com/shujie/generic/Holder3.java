package com.shujie.generic;

/**
 * @author linshujie
 */
public class Holder3 <T>{
    private T a;

    public Holder3(T a) {
        this.a = a;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Holder3<Automobile> holder3 = new Holder3<>(new Automobile());
        Automobile automobile = holder3.getA();
    }
}
