package sword_to_offer;

import java.util.Stack;

/**
 * P165包含min函数的栈，在该栈中，调用min、push及pop的时间复杂度都是O(1)
 * 关键：使用一个辅助栈来保存每次压入元素时的当前栈中最小元素。
 */
public class Problem30 {
    // 定义数据栈和辅助栈
    Stack<Integer> dataStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        dataStack.push(node); // 不管是否为空，数据栈都正常压入进栈
        // 栈不为空时，应该将node和当前栈中最小元素（即minStack栈顶元素）进行比较，较小者压入minStack
        minStack.push(minStack.isEmpty() ? node : Math.min(node, minStack.peek()));
    }

    public void pop() {
        // 不用进行栈是否为空的检查，因为java内置栈的pop方法中已经包括了检查，top也是
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        // 只是返回最小元素，不删除
        return minStack.peek();
    }
}
