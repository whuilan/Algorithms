package sword_to_offer;

/**
 * P231把数字翻译成字符串（leetcode中91题的解码方法），按从1开始映射到A
 */
public class Problem46 {
    // 普通动态规划dynamic planning
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0'){
            return 0;
        }
        int len = s.length();
        // dp[i]代表s从i开始到结尾的字符串的解码方式（使用动态规划将子问题的的最优解存储在（一维）数组中）
        int[] dp = new int[len + 1];
        dp[len] = 1; // 对应直接递归中的结束条件
        if (s.charAt(len - 1) != '0'){
            dp[len - 1] = 1;
        }
        for (int i = len - 2; i >= 0; i--){ // 自下而上循环求解
            if (s.charAt(i) == '0'){
                continue;  // s中从0开始到结尾的字符串不对应任何数字，直接返回，此时dp[i]为默认值0
            }
            int part1 = dp[i + 1];
            int part2 = 0;
            int a = (s.charAt(i) - '0') * 10;
            int b = s.charAt(i + 1) - '0';
            if (a + b <= 26){
                part2 = dp[i + 2];
            }
            dp[i] = part1 + part2;
        }
        return dp[0];
    }

    /**
     * 当更新到 dp[i]的时候，我们只用到dp[i+1]和dp[i+2]，之后的数据就没有用了。
     * 所以我们不需要 dp 开 len + 1 的空间。只申请三个空间就行，把数组dp[]的下标
     * 对3求余就行了
     */
    public static int numDecodings2(String s){
        if (s == null || s.length() == 0){
            return 0;
        }
        int len = s.length();
        int[] dp = new int[3];
        dp[len % 3] = 1;
        if (s.charAt(len - 1) != '0'){
            dp[(len - 1) % 3] = 1;
        }
        for (int i = len - 2;i >= 0;i--){
            if (s.charAt(i) == '0'){
                dp[i % 3] = 0;  // 因为空间是复用的，所以此处要清零！
                continue;
            }
            int part1 = dp[(i + 1) % 3];
            int part2 = 0;
            int a = (s.charAt(i) - '0') * 10;
            int b = s.charAt(i + 1) - '0';
            if (a + b <= 26){
                part2 = dp[(i + 2) % 3];
            }
            dp[i % 3] =  part1 + part2;
        }
        return dp[0];
    }

    // 自己写的，对于'0'的判断有点复杂和混乱
    public static int numDecodings_initial(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0')
            return 0;
        int[] nums = new int[s.length()];
        for (int i = s.length() - 1; i >= 0; i--){  // 从右往左算起
            if (i == s.length() - 1){
                nums[i] = s.charAt(i) == '0' ? 0 : 1;
            }
            else {
                if (s.charAt(i) == '0' && s.charAt(i + 1) == '0'){
                    nums[0] = 0;
                    break;
                }
                else if (s.charAt(i + 1) != '0'){
                    nums[i] = nums[i + 1];
                }
                int digit1 = s.charAt(i) - '0';
                int digit2 = s.charAt(i + 1) - '0';
                int digit = digit1 * 10 + digit2;
                if (digit >= 10 && digit <= 26){
                    if (i == s.length() - 2){
                        nums[i] += 1;
                    }else {
                        nums[i] += nums[i + 2];
                    }
                }
            }
        }
        return nums[0];
    }

    public static void main(String[] args){
        String s = "2018";
        int n = numDecodings2(s);
        System.out.println(n);
    }
}
