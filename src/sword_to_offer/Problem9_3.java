package sword_to_offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode上的用队列实现栈(包含返回栈顶元素的top方法)
 * 再使用9_2的解法就会有问题啦，因为top会影响pop!所以不能每次都以queue优先
 * 核心：轮流使用两个操作，始终保持一个队列为空！
 * push: 压入不为空的队列（如果两个都是空就随便压入一个）
 * pop: 将不为空的队列的元素弹到空队列里，直到只剩最后一个就弹出返回
 * top: 和pop类似，但是最后一个元素弹出后要添加到另一个队列里
 * 拓展：其实也可以用一个队列来实现，每次在push的时候就改变顺序，变成后进先出的顺序！
 * public void push(int x) {
 *      queue.add(x);
 *      for(int i = 1; i < queue.size(); i++)
 *          queue.add(queue.remove());
 * }
 */
public class Problem9_3 {
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    /** Initialize your data structure here. */
    public Problem9_3() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        if (queue1.isEmpty()){
            queue2.offer(x);
        }else {
            queue1.offer(x);
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (queue1.isEmpty()){
            while (queue2.size() > 1){
                queue1.offer(queue2.poll());
            }
            return queue2.poll();
        }
        else{ // 题目假设不会对空的栈调用pop，如果queue1为空那么queue2一定不为空
            while ( queue1.size() > 1){
                queue2.offer(queue1.poll());
            }
            return queue1.poll();
        }
    }

    /** Get the top element. */
    public int top() { // 相当于把一个队列的元素都搬到另一个队列
        int top = 0;
        if (queue1.isEmpty()){
            while (queue2.size() > 1){
                queue1.offer(queue2.poll());
            }
            top = queue2.poll();
            queue1.offer(top);
        }
        else{ // 题目假设不会对空的栈调用pop，如果queue1为空那么queue2一定不为空
            while ( queue1.size() > 1){
                queue2.offer(queue1.poll());
            }
            top = queue1.poll();
            queue2.offer(top);
        }
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

    public static void main(String[] args){
        Problem9_3 myStack = new Problem9_3();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        int top1 = myStack.top();
        System.out.println(top1);
        int pop1 = myStack.pop();
        System.out.println(pop1);
        int top2 = myStack.top();
        System.out.println(top2);
        int pop2 = myStack.pop();
        System.out.println(pop2);
        int top3 = myStack.top();
        System.out.println(top3);
        boolean b1 = myStack.empty();
        System.out.println(b1);
        int pop3 = myStack.pop();
        System.out.println(pop3);
        boolean b2 = myStack.empty();
        System.out.println(b2);
    }
}
