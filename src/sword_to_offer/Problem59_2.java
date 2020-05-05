package sword_to_offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列的最大值：请定义一个队列并实现函数 max_value 得到队列里的最大值，
 * 要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * 思路/关键：通过举具体的例子一步步分析，发现需要借助一个数据结构来保存每一步
 * 的最大值，而且从其头部/尾部就能获得最大值（时间复杂度为O(1)）,经过举例分析，
 * 发现应该选用双端队列（两端开口队列作为辅助的数据结构）
 */
public class Problem59_2 {
    
    Queue<Integer> dataQueue;
    Deque<Integer> maxDeque;
    
    public Problem59_2() {
        dataQueue = new LinkedList<>();
        maxDeque = new LinkedList<>();
    }

    public int max_value() {
        // 最大值即双端队列maxDeque的头部
        if (!maxDeque.isEmpty()){
            return maxDeque.peekFirst();
        }
        return -1;
    }

    public void push_back(int value) {
        dataQueue.offer(value);
        // 如果新加入的值比当前存储最大值的双端队列的尾部元素大，那么删除队列尾部的数
        // 因为它们不可能是最大值
        while (!maxDeque.isEmpty() && value > maxDeque.peekLast()){
            maxDeque.pollLast();
        }
        // 若maxDeque为空，或者新加入的数值小于maxDeque的尾部数值，则该数值有可能成为最大值，
        // 就将其放到队列尾部
        maxDeque.offerLast(value);
    }

    public int pop_front() {
        if (!dataQueue.isEmpty()){
            int peek = dataQueue.remove();
            // 如果队列头部元素等于当前最大值，说明最大值被弹出，需要将maxDeque栈顶的最大值也弹出
            if(peek == maxDeque.peekFirst()){
                maxDeque.pollFirst();
            }
            return peek;
        }
        return -1;
    }

    public static void main(String[] args){
        Problem59_2 problem59_2 = new Problem59_2();
        problem59_2.push_back(3);
        problem59_2.push_back(4);
        int max1 = problem59_2.max_value();
        problem59_2.push_back(2);
        int max2 = problem59_2.max_value();
        problem59_2.push_back(1);
        int max3 = problem59_2.max_value();
        int peek1 = problem59_2.pop_front();
        int peek2 = problem59_2.pop_front();
        int max4 = problem59_2.max_value();
        int peek3 = problem59_2.pop_front();
        int max5 = problem59_2.max_value();
        problem59_2.push_back(5);
        int max6 = problem59_2.max_value();
    }
}
