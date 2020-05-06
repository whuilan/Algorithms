package sword_to_offer;

import java.util.Arrays;

/**
 * P298 扑克牌中的顺子
 * 首先用数组表示扑克牌，然后需要做三件事情/三个步骤：
 * 1 将数组（扑克牌）排序
 * 2 统计数组中0的个数（即大小王的个数）
 * 3 统计排序数组之后相邻数之间的空缺vacancy。如果空缺的总数小于或等于0的个数，则数组是连续的。
 * 此外，还需要注意判断数组中是否出现了重复的数字，即扑克牌中的对子，如果出现了，则一定不是顺子
 */
public class Problem61 {
    public static boolean isContinuous(int [] nums) {
        if (nums == null || nums.length == 0){
            return false;
        }
        Arrays.sort(nums);
        int N = nums.length;
        int zeroNums = 0, vacancy = 0;
        for (int i = 0; i < N; i++){
            // 统计0（大小王的数目）
            if (nums[i] == 0){
                zeroNums++;
                continue;
            }
            // 出现了对子，则不可能是顺子
            if (i > 0 && nums[i] == nums[i-1]){
                return false;
            }
            // 相邻两个数间隔超过1说明出现了空缺
            if (i > 0 && nums[i-1] != 0 && nums[i] - nums[i-1] > 1){
                vacancy += nums[i] - nums[i-1] - 1;
            }
        }
        return vacancy <= zeroNums;
    }

    public static void main(String[] args){
        int[] nums = {4,0,2,5,6};
        boolean isContinous = isContinuous(nums);
        System.out.println(isContinous);
    }
}
