package sword_to_offer;

/**
 * P240丑数
 * 重点：如何生成下一个丑数的逻辑
 * 其实就是动态规划！这里的times数组就相当于dp[]
 */
public class Problem49 {
    public static int GetUglyNumber_Solution(int index) {
        if (index <= 0){
            return 0;
        }
//        if (index <= 6){
//            return index;
//        }
        int[] nums = new int[index];
        nums[0] = 1;
        int t2 = 0, t3 = 0, t5 = 0; // 指针，在该处乘以2/3/5时对应的数大于此时数组中最大的数
        for (int i = 1; i < index; i++){
            int next_2 = nums[t2] * 2, next_3 = nums[t3] * 3, next_5 = nums[t5] * 5;
            nums[i] = Math.min(next_2, Math.min(next_3, next_5));
            if (nums[i] == next_2){
                t2++;
            }
            if (nums[i] == next_3){
                t3++;
            }
            if (nums[i] == next_5){
                t5++;
            }
        }
        return nums[index - 1];
    }

    public static void main(String[] args){
        int index = 10;
        int uglyNum = GetUglyNumber_Solution(index);
        System.out.println(uglyNum);
    }
}
