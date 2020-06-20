package LeetCode.StackAndQueue;

import java.util.Stack;

/**
 * 柱状图中的最大矩形：给定n个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，
 * 且宽度为 1 。求在该柱状图中，能够勾勒出来的矩形的最大面积。（How dare I! leetcode热题
 * 才刷了30题左右就选了这道hard题来做，看了好久！）
 */
public class Solution84 {
    /*
    暴力法：暴力法就是枚举，求出每种可能的矩形面积，然后找出最大的那个。但是也有两种枚举方法！
    1. 枚举“宽”(我的第一反应)，即所有可能的矩形的宽度，用两重循环枚举矩形的左右边界以固定宽度w，
    此时矩形的高度 h，就是所有包含在内的柱子的「最小高度」，对应的面积为 w * h
    2. 枚举“高”，最大矩形的高度一定是数组中的某个高度啊！因此可以使用一重循环枚举每一根柱子，
    将其固定为矩形的高度 h。随后从这跟柱子开始向两侧延伸，直到遇到高度小于h的柱子，就确定了
    矩形的左右边界。如果左右边界之间的宽度为 w，那么对应的面积为 w * h。
    这两种暴力法的时间复杂度均为O(n^2)，空间复杂度为O(1)。
    今天有点累，时间也每把控好，就先不写暴力法的代码啦~可以看leetcode官方写的，很棒！
     */


    /*
    暴力法优化：维护一个递增栈来找到i左右两侧最近的高度小于heights[i]的柱子，栈中的元素代表
    [可能会作为答案]的高度。具体分析见官方题解，太巧妙啦！
    时间复杂度为O(n)，空间复杂度为O(1)
     */
    public int largestRectangleArea(int[] heights) {
        if (heights ==  null || heights.length == 0){
            return 0;
        }
        int N = heights.length;
        // 每根柱子（高度为h）左右两边最近的比h小的元素的索引
        int[] left = new int[N];
        int[] right = new int[N];
        // 可能成为所求高度的高度（的索引位置）
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++){
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]){
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = N - 1; i >= 0; i--){
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]){
                stack.pop();
            }
            right[i] = stack.isEmpty() ? N : stack.peek();
            stack.push(i);
        }
        int curRect = 0, maxRect = 0;
        for (int i = 0; i < N; i++){
            curRect = heights[i] * (right[i] - left[i] - 1);
            maxRect = Math.max(maxRect, curRect);
        }
        return maxRect;
    }

    public static void main(String[] args){
        int[] heights = {2,1,5,6,2,3};
        Solution84 solution84 = new Solution84();
        int maxArea = solution84.largestRectangleArea(heights);
        System.out.println(maxArea);
    }
}
