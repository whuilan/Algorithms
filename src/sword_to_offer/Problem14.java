package sword_to_offer;

/**
 * P96 动态规划与贪婪算法：剪绳子
 */
public class Problem14 {
    // 法一：动态规划，时间复杂度为O(n^2)，空间复杂度为O(n)
    public static int cutRope0(int target) {
        if(target <= 1){
            return 0;
        }
        if(target == 2){
            return 1;
        }
        if(target == 3){
            return 2;
        }
        /*
        product[i]表示把长度为i（i > 3）的绳子剪成若干段之后各段长度乘积的最大值，
        就是将各个子问题的最优解存储到（一维数组）中
         */
        int[] product = new int[target + 1];
        product[1] = 1;
        product[2] = 2;
        product[3] = 3;  // 这三项是为了计算其他子问题

        for(int i = 4; i <= target; i++){ // i递增，表示是从下到上来计算子问题
            int max = 0;
            for(int j = 1; j <= i / 2; j++){
                int pro = product[j] * product[i - j];
                if(max < pro){
                    max = pro;
                }
            }
            product[i] = max;
        }
        return product[target];
    }

    // 法二：贪婪算法，时间复杂度和空间复杂度均为O(1)
    public static int cutRope(int target) {
        if(target <= 1){
            return 0;
        }
        if(target == 2){
            return 1;
        }
        if(target == 3){
            return 2;
        }
        // 尽可能多剪去长度为3的绳子
        int timesOf3 = target / 3;
        // 当绳子最后剩下的长度为4时，不能再剪去长度为3的绳子，二十将其剪成两段长度为2的绳子
        if(target - timesOf3 * 3 == 1){
            timesOf3 -= 1;
        }

        int timesOf2 = (target - timesOf3 * 3) / 2;

        return (int) (Math.pow(3, timesOf3) * Math.pow(2, timesOf2));
    }

    public static void main(String[] args){
        int target = 4;
        int result = cutRope(target);
        System.out.println(result);
    }
}
