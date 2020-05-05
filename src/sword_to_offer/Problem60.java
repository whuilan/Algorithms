package sword_to_offer;

/**
 * n个骰子的点数之和：把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。
 * 输入n，打印出s的所有可能的值出现的概率
 */
public class Problem60 {
    // 法一（我的思路）：类似于面试题17，是一个全排列的问题，s的范围为n(全1)~6n(全6)，对
    // n个骰子点数进行全排列，计算每种情况下点数之和s的值，并定义一个长为6n-n+1的数组来记录
    // 每个s的出现次数。是对的，但是运行时间超时
    private int[] times;

    public double[] twoSum1(int n) {
        if (n <= 0){
            return new double[0];
        }
        times = new int[5*n+1];
        int[] nums = new int[n];
        double[] probs = new double[5*n+1];
        allPermutation(nums,0);
        int totalTimes = getSum(times);
        for (int i = 0; i < 5*n+1; i++){
            probs[i] =  times[i] / (double) totalTimes;
        }
        return probs;
    }

    private void allPermutation(int[] nums, int seq){
        if (seq == nums.length){
            int s = getSum(nums);
            times[s-nums.length] += 1;
            return;
        }
        for (int i = 1; i <= 6; i++){
            nums[seq] = i;
            allPermutation(nums, seq + 1);
        }
    }

    private int getSum(int[] nums){
        int sum = 0;
        for (int n : nums){
            sum += n;
        }
        return sum;
    }

    // 法二：动态规划，用一个二维数组dp[n][s]存储当有n个骰子且各骰子点数之和为s时的情况数量（出现次数）
    // 当只有一个骰子，即n=1时，s的范围为1-6，且dp[1][s] = 1.
    // 当n>=2时，每一个s就有六种情况，即前n-1骰子的点数之和分别通过加上新的骰子的1~6才到s，即：
    // dp[n][s]=dp[n-1][s-1]+dp[n-1][s-2]+dp[n-1][s-3]+dp[n-1][s-4]+dp[n-1][s-5]+dp[n-1][s-6]
    public double[] twoSum2(int n) {
        if (n <= 0){
            return new double[0];
        }
        int[][] dp = new int[n+1][6*n+1]; // n行表示n个骰子，6*n表示n个骰子时最大的点数之和
        // 只有一个骰子时，每个s出现的情况都是1
        for (int s = 1; s <= 6; s++){
            dp[1][s] = 1;
        }
        for (int i = 2; i <= n; i++){
            for (int s = i; s <= 6*i; s++){
                for (int k = 1; k <= 6; k++){
                    if (s-k < i-1){
                        break; // i-1个骰子的点数之和最小就是i-1,不可能比i-1小，说明此时s较小，i-1可能的情况已经完了，不再继续循环
                    }
                    dp[i][s] += dp[i-1][s-k];
                }
            }
        }
        double total = Math.pow(6, n); // 一共出现的情况，即n个骰子的排列数
        double[] probs = new double[5*n+1]; // n~6n
        for (int i = 0; i < 5*n+1; i++){
            probs[i] = dp[n][i+n] / total;
        }
        return probs;
    }

    public static void main(String[] args){
        Problem60 problem60 = new Problem60();
        double[] probabilities = problem60.twoSum2(2);
        System.out.println("finish");
    }
}
