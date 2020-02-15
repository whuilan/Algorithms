package sword_to_offer;

import edu.princeton.cs.algs4.StdOut;

/**
 * P100 位运算：二进制中1的个数
 */
public class Problem15 {
    // 法一：将1和该整数的二进制表示按位与
    public static int NumberOf1_v1(int n) {
        int count = 0, flag = 1;
        // 循环的次数等于整数二进制的位数，32位的int整数需要循环32次
        while (flag != 0){
            if((flag & n) != 0){
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    /**
     *法二：n & (n - 1), 整数中有几个1就循环几次
     * 把一个整数减去1（n-1）后就将n的二进制表示中最右边的1变为0，如果它右边还有0，就把所有的0都变为1
     * ，而它左边的所有位都保持不变，相当于从最右边的1到最低位逐次取反，因此 n & (n - 1)就相当于把n
     * 的二进制表示中最右边的1变为0.那么一个整数的二进制表示中有多少个1，就可以进行多少次这样的操作。
     */
    public static int NumberOf1_v2(int n) {
        int count = 0;
        while (n != 0){
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    // 法三：利用java内置类 Integer.bitCount
    public static int NumberOf1(int n){
        return Integer.bitCount(n);
    }


    public static void main(String[] args){
        int n = 12;
        int c = NumberOf1(n);
        StdOut.println(c);
    }
}
