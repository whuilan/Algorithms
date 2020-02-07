package sword_to_offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * P71 用两个队列实现栈
 */
public class Problem9_2 {
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    public void push(int val){
        queue1.offer(val);
    }

    public int pop() throws Exception{
        if(queue1.isEmpty()){
            throw new Exception("Stack is empty");
        }
        while(queue1.size() > 1){
            queue2.offer(queue1.poll());
        }
        int i = queue1.poll();
        while (!queue2.isEmpty()){
            queue1.offer(queue2.poll());
        }
        return i;
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
