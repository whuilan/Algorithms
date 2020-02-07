package sword_to_offer;

import java.util.Stack;

/**
 * P68面试题9：用两个栈实现队列
 */
public class Problem9 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() throws Exception{
        if (stack2.empty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }

        if(stack2.empty()){
            throw new Exception("Queue is empty");
        }

        return stack2.pop();
    }
}
