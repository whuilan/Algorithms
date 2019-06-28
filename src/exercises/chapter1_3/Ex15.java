package exercises.chapter1_3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
/**
 * 1.3.15 编写一个Queue的用例，接受一个命令行参数k并打印出标准输入中的倒数第k个字符串
 * （假设标准输入中至少有k个字符串）。
 */
public class Ex15 {
    public static void main(String[] args){
        int k = Integer.parseInt(args[0]);
       String[] arr = {"apple","banana","orange","pear","watermelon"};
       Queue<String> queue = new Queue<>();
       for(int i = 0;i<arr.length;i++){
           queue.enqueue(arr[i]);
       }
       for(int i=0;i<arr.length;i++){
           if(!queue.isEmpty()){
               String str = queue.dequeue();// 注意队列是先进先出
               if(k==arr.length-i){     //重点，i和k以及arr.length之间满足的数量关系
                   StdOut.println(str);
               }
           }
       }

    }
}
