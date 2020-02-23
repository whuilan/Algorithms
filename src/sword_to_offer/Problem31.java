package sword_to_offer;

import java.util.Stack;

/**
 * P168栈的压入、弹出序列，使用一个栈来模拟压入、弹出操作
 * 编程时
 */
public class Problem31 {

    public boolean IsPopOrder(int[] pushA, int[] popA){
        if (pushA == null || popA == null || pushA.length == 0
            || popA.length == 0 || pushA.length != popA.length){  // 两个序列要等长
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int len = pushA.length;
        for (int pushIndex = 0, popIndex = 0; pushIndex < len; pushIndex++){
            stack.push(pushA[pushIndex]);
            while (popIndex < len && !stack.isEmpty() && popA[popIndex] == stack.peek()){
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 我自己最开始写的，从popA开始比较，代码比较复杂，而且有个问题：不知道怎么处理在栈顶元素为空时，
     * 怎么和弹出序列的元素进行比较，因为栈为空时，stack.peek()会抛出异常而不是返回一个int值，因此
     * 无法进行比较。
     */
    public boolean IsPopOrder2(int [] pushA,int [] popA) {
        if (pushA == null || popA == null || pushA.length < popA.length){
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(pushA[0]);
        int i;
        for (i = 0; i < popA.length; i++){
            // 与栈顶元素相等，直接弹出
            int curPeek = stack.peek();
            if (popA[i] == curPeek){
                stack.pop();
            }
            else {  // 与栈顶元素不相等，则去压入序列中找到该元素，并把以前的元素都压入栈
                int pushStart = -1, pushEnd = -1;
                for (int j = 0;j < pushA.length;j++){
                    if (pushA[j] == curPeek){
                        pushStart = j;
                    }
                    if (pushA[j] == popA[i]){
                        pushEnd = j;
                    }
                }
                if (pushEnd == -1){
                    return false;   // 说明这个元素已经被压入栈但不在栈顶
                }
                for (int k = pushStart; k <= pushEnd; k++){
                    stack.push(pushA[k]);
                }
                stack.pop();
            }
        }
        return i == popA.length;
    }

    public static void main(String[] args){
        int[] pushA = {1,2,3,4,5}, popA = {4,3,5,1,2};
        Problem31 problem31 = new Problem31();
        boolean result = problem31.IsPopOrder(pushA, popA);
        if (result){
            System.out.println("success");
        }
        System.out.println("fail");
    }
}
