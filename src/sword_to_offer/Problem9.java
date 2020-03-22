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

    //另一个版本，队列为空时返回-1
    public int deleteHead() {
        if (!stack2.isEmpty()){    // stack2不为空时直接弹出stack2的栈顶元素
            return stack2.pop();
        }
        else {                     // stack2为空时将stack1中的逐个弹出再压入stack2，然后再弹出stack2的栈顶元素
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.isEmpty() ? -1 : stack2.pop();
        }
    }
}
