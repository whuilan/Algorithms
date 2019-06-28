package exercises.chapter1_1;

import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int i1 = s.nextInt();
        int i2 = s.nextInt();
        int i3 = s.nextInt();
        if(i1 == i2 && i1 == i3){
            System.out.println("equal");
        }else {
            System.out.println("not equal");
        }
    }
}
