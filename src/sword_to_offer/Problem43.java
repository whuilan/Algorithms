package sword_to_offer;

/**
 * P211从1到n的整数中1出现的次数（数学规律题）
 */
public class Problem43 {
    public static int NumberOf1Between1AndN_Solution(int n){
        if (n < 1){
            return 0;  // n < 1就是没有1呗，直接返回0就好了，不用抛出异常！
        }
        int count = 0;
        for (int i = 1; i <= n; i *= 10){  // i表示当前分析的是哪一个数位，个位？十位？...
            int left = n / i, right = n % i;
            count += (left + 8) / 10 * i + (left % 10 == 1 ? right + 1: 0);
        }
        return count;
    }

    public static void main(String[] args){
        int n = 12;
        int num = NumberOf1Between1AndN_Solution(n);
        System.out.println(num);
    }
}
