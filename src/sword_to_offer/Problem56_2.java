package sword_to_offer;

/**
 * 题目二：在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。
 * 找出那个只出现一次的数字。时间复杂度：O(n)，空间复杂度：O（1）
 */
public class Problem56_2 {
    public static int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int singleNum = 0;
        // 分别求二进制各个位上的和
        for (int i = 0; i < 32; i++){ // i表示位数指针
            int digitSum = 0;
            for (int j = 0; j < nums.length; j++){
                digitSum += nums[j] & 1;
                nums[j] = nums[j] >> 1;
            }
            if (digitSum % 3 != 0){
                singleNum += Math.pow(2, i);
            }
        }
        return singleNum;
    }

    public static void main(String[] args){
        int[] nums = {1,2,2,2,3,3,3};
        int singleNum = singleNumber(nums);
        System.out.println(singleNum);
    }
}
