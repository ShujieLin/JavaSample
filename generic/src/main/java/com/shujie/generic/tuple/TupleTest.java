package com.shujie.generic.tuple;

/**
 * @author linshujie
 */
public class TupleTest {

    static TwoTuple<String,Integer> f(){
        return new TwoTuple<>("shujie",1);
    }

    static ThreeTuple<Amphbian,String,Integer> g(){
        return new ThreeTuple<>(new Amphbian(),"shujie",1);
    }

    static FourTuple<Vehicle,Amphbian,String,Integer> h(){
        return new FourTuple<>(new Vehicle(),new Amphbian(),"shujie",1);
    }

    public static void main(String[] args) {
        TwoTuple<String,Integer> twoTuple = f();
        System.out.println("twoTuple = " + twoTuple);

        System.out.println(g());
        System.out.println(h());
    }
}

class Amphbian{}
class Vehicle{}
