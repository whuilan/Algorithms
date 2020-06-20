package LeetCode.Array;

import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

import java.util.Stack;

/**
 * 接雨水(hard):给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，
 * 下雨之后能接多少雨水。)
 */
public class Solution42 {
    // 解法一：暴力解法，求每一列能装的水，因此对数组进行遍历，同时记录下当前列的高度及
    // 左边的最大值和右边的最大值。当前列能装的水，等于两边最大高度的较小值减去当前高度的值
    // 时间复杂度O(n^2)，空间复杂度O(1)。
    public int trap1(int[] height) {
        if(height == null || height.length < 3){
            return 0;
        }
        int N = height.length;
        int amount = 0;
        int leftMax, rightMax, less;
        for (int i = 1; i < N - 1; i++){
            leftMax = max(height, 0, i - 1);
            rightMax = max(height, i + 1, N - 1);
            less = Math.min(leftMax, rightMax);
            if (less > height[i]){
                amount += less - height[i];
            }
        }
        return amount;
    }

    // 求数组a中start到end范围的最大值
    private int max(int[] a, int start, int end){
        int max = 0;
        for (int i = start; i <= end; i++){
            if (a[i] > max){
                max = a[i];
            }
        }
        return max;
    }

    // 解法二：（动态编程，将中间结果保存起来，避免重复计算）法一中每次遍历都要用O(n)的时间
    // 复杂度去找当前列左边的最大值和右边的最大值。其实可以提前将其用数组存起来，遍历的时候只需
    // 要去对应位置读取就好了。时间复杂度为O(n)，空间复杂度也为O(n)。相当于空间换时间。
    public int trap2(int[] height) {
        if (height == null || height.length < 3){
            return 0;
        }
        int N = height.length;
        int amount = 0;
        // leftMax[i]表示第i列左边的最大值，即0~i-1列的最大值，注意不包含自身
        int[] leftMax = new int[N];
        int[] rightMax = new int[N];
        // 从左到右遍历求每一列的leftMax
        for (int i = 1; i < N - 1; i++){
            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
        }
        // 从右往左遍历求每一列的rightMax
        for (int i = N - 2; i > 0; i--){
            rightMax[i] = Math.max(rightMax[i+1], height[i+1]);
        }
        for (int i = 1; i < N - 1; i++){
            int less = Math.min(leftMax[i], rightMax[i]);
            if (less > height[i]){
                amount += less - height[i];
            }
        }
        return amount;
    }

    // 解法三：双指针优化解法二的空间复杂度（我在解法一之后就想到了，但不知道rightMax该
    // 怎么用一个变量来保存）核心：用left和right两个指针分别从最左边和最右边同时开始遍历！
    // 时间复杂度为O(n)，空间复杂度为O(1)（所以说，一个指针从左开始遍历不够嘛，不能处理
    // rightMax，那就试试两个指针呀，要处理rightMax，就让新指针从右往左遍历）
    public int trap3(int[] height) {
        if (height == null || height.length < 3){
            return 0;
        }
        int N = height.length;
        int leftMax = height[0], rightMax = height[N - 1]; // 左边和右边的最大值，不包括当前元素
        int i = 1, j = N - 2; // 遍历每一列的左右指针
        int amount = 0;
        while (i <= j){ // 这一列也是要算上的！
            if (leftMax < rightMax){  // 此时可以确定left处的装水量
                amount += Math.max(0, leftMax - height[i]);
                leftMax = Math.max(leftMax, height[i]);
                i++;
            }
            else{
                amount += Math.max(0, rightMax - height[j]);
                rightMax = Math.max(rightMax, height[j]);
                j--;
            }
        }
        return amount;
    }

    /*
    解法四：单调栈（递减栈），当两堵墙之间形成凹形时，它们之间就有了个坑，可以接水。因此可以在遍历
    高度的时候判断，是否有凹形墙形成。用递减栈来模拟这个过程！用栈保存每堵墙的高度，遍历时：
    如果当前高度小于栈顶的墙的高度，说明当前墙可能会有积水（是个坑！），将当前墙直接入栈。
    如果当前墙的高度大于栈顶墙的高度，说明栈顶墙（坑）积水到这里停下，可以将栈顶墙弹出，计算由当前
    墙和此时栈顶墙（高度都大于刚刚弹出的栈顶墙）给刚刚弹出的墙积的水。并且要继续比较当前墙和新的栈顶
    墙的高度，直到当前墙不大于栈顶墙或栈为空，则将当前墙压入栈作为新的坑。
     */
    public int trap4(int[] height) {
        if(height == null || height.length == 0){
            return 0;
        }
        int amount = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++){
            while (!stack.isEmpty() && height[i] > height[stack.peek()]){
                int base = stack.pop(); // 此次积水的墙（坑）的高度
                if (stack.isEmpty()){
                    break;
                }
                int leftIdx = stack.peek(); // 左边
                int h = Math.min(height[leftIdx], height[i]) - height[base]; // 积水高度
                amount += h * (i - leftIdx - 1); // 积水总量：高x宽
            }
            // 栈为空或当前高度小于栈顶高度（可能形成坑）时直接将当前墙高度的索引压入栈中
            stack.push(i);
        }
        return amount;
    }


    public static void main(String[] args){
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        Solution42 solution42 = new Solution42();
        int rainCount = solution42.trap4(height);
        System.out.println(rainCount);
    }
}
