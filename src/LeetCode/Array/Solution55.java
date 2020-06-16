package LeetCode.Array;

/**
 * 跳跃游戏（百度三面面试题！）
 */
public class Solution55 {
    // 法一：回溯，根据题目描述，从第一个元素开始，每次跳的步数为0~nums[i]，跳到下一个位置
    // 之后又重复进行这个步骤，直到跳到数组末尾返回true。否则返回false，尝试下一种跳法。
    // 逻辑是对的，但运行超时！
    public boolean canJump1(int[] nums) {
        return backtracking(nums, 0);
    }

    private boolean backtracking(int[] nums, int index){
        if (index >= nums.length - 1){
            return true;
        }
        for (int i = 1; i <= nums[index]; i++){
            if (backtracking(nums, index+i)){
                return true;
            }
        }
        return false;
    }

    /*
    法二：法一的回溯使用递归超时，那可能就要用循环。这又是一个数组，那就是遍历呗！注意到，对于每一个可达的位置i，按照题意，其后面的
    i+1,i+2,...,i+num[i]这些位置都可以到达。因此，我们可以遍历数组，并实时更新遍历到该位置时最远可以
    到达的位置！对于当前遍历到的位置x，如果它在当前最远可达位置范围内，说明可以从起点通过若干次跳跃跳到
    该位置，并可以继续往前跳，并且如果x+nums[x]大于当前最远可达位置，应该更新最远可达位置。
    时间复杂度为O(N)!空间复杂度为O(1)
     */
    public boolean canJump(int[] nums) {
        // 初始化最远可达位置/索引
        int remotest = 0;
        for (int i = 0; i < nums.length; i++){
            if (i <= remotest){ // i可达
                remotest = Math.max(remotest, i + nums[i]);
                if (remotest >= nums.length - 1){
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args){
        int[] nums = {3,2,1,0,4};
        Solution55 solution55 = new Solution55();
        boolean canArrive = solution55.canJump(nums);
        System.out.println(canArrive);
    }
}
