package com.shujie.anenum;

enum Shrubbery{GROUND,CRAWLING,HANGING}

public class MyClass {
    public static void main(String[] args) {
        for (Shrubbery s :
                Shrubbery.values()) {
            System.out.println("s.ordinal() = " + s.ordinal());
            System.out.println(s.compareTo(Shrubbery.CRAWLING));
            System.out.println(s.equals(Shrubbery.CRAWLING));
            System.out.println(s == Shrubbery.CRAWLING);
            System.out.println(s.getDeclaringClass());
            System.out.println(s.name());
            System.out.println("---------------------------");
        }

        for (String s :
                "HANGING CRAWLING GROUND".split(" ")) {
            Shrubbery shrubbery = Enum.valueOf(Shrubbery.class,s);
            System.out.println(shrubbery);
        }
    }
}