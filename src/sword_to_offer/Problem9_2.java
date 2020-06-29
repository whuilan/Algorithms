package sword_to_offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * P71 用两个队列实现栈（只有压入操作、弹出操作时的实现）
 *
 */
public class Problem9_2 {

    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    // 每次都压入queue1
    public void push(int val){
        queue1.offer(val);
    }

    // 弹出时先判断queue1，有一个就直接弹出，有多个就先压到queue2再弹出最后一个，为空就弹出queue2的
    // 最后一个
    public int pop(){
        if (!queue1.isEmpty()){
            while ( queue1.size() > 1){
                queue2.offer(queue1.poll());
            }
            return queue1.poll();
        }
        else{ // 题目假设不会对空的栈调用pop，如果queue1为空那么queue2一定不为空
            while (queue2.size() > 1){
                queue1.offer(queue2.poll());
            }
            return queue2.poll();
        }
    }

    public static void main(String[] args){
        Problem9_2 stack = new Problem9_2();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        try{
            int first = stack.pop();
            System.out.println(first);
            int second = stack.pop();
            System.out.println(second);
        }catch (Exception e){
            e.printStackTrace();
        }
        stack.push(4);
        try{
            int third = stack.pop();
            System.out.println(third);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
