package LeetCode.Array;

public class Solution42 {
    // 解法一：暴力直接解法，求每一列能装的水，因此对数组进行遍历，同时记录下当前列的高度，
    // 当前列左边的最大值和右边的最大值。时间复杂度O(n^2)，空间复杂度O(1)。
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

    // 解法二：（动态规划，将中间结果保存起来，避免重复计算）法一中每次遍历都要用O(n)的时间
    // 复杂度去找当前列左边的最大值和右边的最大值其实可以先将其用数组存起来，再遍历的时候只需
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
        for (int i = 1; i < N - 1; i++){
            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
        }
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
    // 时间复杂度为O(n)，空间复杂度为O(1)
    public int trap3(int[] height) {
        if (height == null || height.length < 3){
            return 0;
        }
        int N = height.length;
        int leftMax = height[0], rightMax = height[N - 1]; // 左边和右边的最大值，不包括当前元素
        int left = 1, right = N - 2; // 遍历每一列的左右指针
        int amount = 0;
        while (left <= right){
            if (leftMax < rightMax){  // 此时可以确定left处的装水量
                amount += Math.max(0, leftMax - height[left]);
                leftMax = Math.max(leftMax, height[left]);
                left++;
            }
            else{
                amount += Math.max(0, rightMax - height[right]);
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }
        return amount;
    }


    public static void main(String[] args){
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        Solution42 solution42 = new Solution42();
        int rainCount = solution42.trap3(height);
        System.out.println(rainCount);
    }
}
