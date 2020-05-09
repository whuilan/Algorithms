package sword_to_offer;

/**
 * P304 股票的最大利润：假设把某股票的价格按照时间先后顺序存储在数组中，
 * 请问买卖该股票一次可能获得的最大利润是多少？注意买入必须出现在卖出前，
 * 且卖出价格一定要大于买入价格，不然就算交易没有完成，最大利润为0。
 */
public class Problem63 {
    // 只需遍历一次数组，时间复杂度为O(n)（空间复杂为O(1)）比暴力法的时间复杂度O(n^2)好太多！
    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2){
            return 0;
        }
        int N = prices.length;
        // 使用一个变量maxProfit来保存当卖出价为数组中第i个数字时可能获得的最大利润
        int maxProfit = 0;
        // 使用一个变量curMin来保存当前（前i-1个数字）的最小值，即股票的最低价
        int curMin = prices[0];
        // 从第二个索引开始遍历数组，得到在第i个数字卖出时能够得到的最大利润
        for (int i = 1; i < N; i++){
            maxProfit = Math.max(maxProfit, prices[i]-curMin);
            curMin = Math.min(curMin, prices[i]);
        }
        return maxProfit > 0 ? maxProfit : 0;
    }

    public static void main(String[] args){
        int[] prices = {7,6,4,3,1};
        int max = maxProfit(prices);
        System.out.println(max);
    }
}
