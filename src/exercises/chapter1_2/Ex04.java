package exercises.chapter1_2;

import edu.princeton.cs.algs4.StdOut;

public class Ex04 {
    public static void main(String[] args){
        String string1 = "hello";
        String string2 = string1;
        System.out.println("string1的hashcode：" + string1.hashCode());
        System.out.println("string2的hashcode：" + string2.hashCode());
        string1 = "world";
        StdOut.println(string1);
        StdOut.println(string2);
        System.out.println("string1重新赋值后的hashcode：" + string1.hashCode());
        System.out.println("string1重新赋值后string2的hashcode：" + string2.hashCode());
    }
}
