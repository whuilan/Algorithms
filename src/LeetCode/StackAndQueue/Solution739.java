package LeetCode.StackAndQueue;

import java.util.Stack;

/**
 * 每日温度：根据每日气温列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度
 * 才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 * 显然暴力法的时间复杂度为O(n^2)，主要是因为对每个元素都要把它和后面的元素一一
 * 比较来找出第一个比它大的。所以要想办法减少遍历的次数，核心：递减栈！
 *
 */
public class Solution739 {
    // 该方法只需要对数组进行一次遍历，每个元素最多被压入和弹出堆栈一次，
    // 算法复杂度是 O(n)。
    public static int[] dailyTemperatures(int[] T) {
        if (T == null){
            return new int[0];
        }
        int[] ret = new int[T.length]; // 注意数组是有默认值的，每个元素默认值为0
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++){
            // 当前温度比递减栈的栈顶温度高，不符合递减规则，则应该把较小的栈顶元素弹出，
            // 且此时索引差即为间隔的天数，重复此过程直到当前温度小于栈顶元素或递减栈为空
            while (!stack.isEmpty() && T[i] > T[stack.peek()]){
                int smallerIdx = stack.pop();
                ret[smallerIdx] = i - smallerIdx;
            }
            // 栈为空or当前温度小于栈顶温度，则直接将索引压入栈中
            stack.push(i);
        }
        return ret;
    }

    public static void main(String[] args){
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] gaps = dailyTemperatures(T);
        for (int i : gaps){
            System.out.print(i + " ");
        }
    }
}
