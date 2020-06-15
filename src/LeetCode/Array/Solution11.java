package LeetCode.Array;

/**
 * 盛最多水的容器（第一反应，这题和42题接雨水很像！）
 * 刚开始看到“最多”想用动态规划，但是不知道怎么定义状态，怎么进行状态转移。又想到“双指针”，用两个
 * 指针分别从开头和结尾向中间遍历（接雨水也用到了双指针！），没想清楚过程就放弃了。其实是对的呀！
 * 放弃的原因主要是误以为每次遍历要求左右指针范围内的最小值，此外也不知道左右指针怎么移动？同时移？
 * 移较小的那个？其实仔细分析一下是能做的！
 * 误区：1.指定左右指针后，就能确定以这两个索引为边界时能盛的水量，由它们之间较小的那个值决定！与
 *      区间的最小值无关！
 *      2.移动指针应该移动较小的那个，因为移动本来就会使区间长度变短，如果移动较大的那个，那移动后
 *      盛水量一定变小！直接排除掉这种使盛水量减少的情况！
 * 复杂度分析：时间复杂度为O(N)，空间复杂度为O(1)
 */
public class Solution11 {
    public static int maxArea(int[] height) {
        if(height == null || height.length == 0){
            return 0;
        }
        int i = 0, j = height.length - 1;
        int curSum = 0, maxSum = 0;
        while (i < j){
            curSum = (j - i) * Math.min(height[i], height[j]);
            if (curSum > maxSum){
                maxSum = curSum;
            }
            if (height[i] < height[j]){
                i++;
            }else {
                j--;
            }
        }
        return maxSum;
    }

    public static void main(String[] args){
        int[] height = {1,8,6,2,5,4,8,3,7};
        int maxV = maxArea(height);
        System.out.println(maxV);
    }
}
