package exercises.chapter1_1;

import java.util.Arrays;

public class Ex28 {
    public static int[] removeDuplicates(int[] a){
        if (a.length == 0) return null;
        int j = 0;
        for(int i=0;i<a.length;i++){
            if(a[j]!=a[i]){
                a[++j] = a[i];
            }
        }
        int[] temp = new int[j+1];
        for(int i = 0;i<j+1;i++){
            temp[i] = a[i];
        }
        a = temp;
        System.out.println(a.length);
        for(int i = 0;i<temp.length;i++){
            System.out.print(temp[i]+" ");
        }
        System.out.println();

        return a;
    }

    public static void main(String[] args){
//        int[] blacklist = StdIn.readAllInts();
//        System.out.println("original array:"+ Arrays.toString(blacklist));
//        Arrays.sort(blacklist);
//        System.out.println("sorted array:"+ Arrays.toString(blacklist));
//        int newArrLength = removeDuplicates(blacklist);
//        System.out.println("last array:"+ Arrays.toString(blacklist));
//        System.out.print("去重之后的数组的长度为："+newArrLength);
        int[] a = {1,2,2,3,4,4};
        int[] newArr = removeDuplicates(a);
        System.out.println("last array:"+ Arrays.toString(newArr));
//        for(int i=0;i<index;i++){
//            System.out.print(a[i]+" ");
//        }
    }
}
