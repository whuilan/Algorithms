package sword_to_offer;

/**
 * P318字符串转换为整数
 * 需要想到的各种可能输入：null/空字符串、负数、字符串中有其他字符（该字符串不能表示一个整数）
 * 还有溢出！整数的范围为：-2147483648~2147483647
 */
public class Problem67 {
    // 法一：最简单的，不考虑0的区分（“0”或者非法输入都返回0）和溢出
    public int StrToInt1(String str) {
        // null或空字符串为非法输入
        if (str == null || str.length() == 0){
            return 0;
        }
        boolean isNegative = str.charAt(0) == '-';
        int num = 0;
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if (i == 0 && (c == '+' || c == '-')){
                continue;
            }
            if (c < '0' || c > '9'){
                return 0;
            }
            num = num * 10 + (c - '0');
        }
        return isNegative ? -num : num;
    }

    // 考虑溢出的实现，其实就是库函数Integer.parseInt()的内部实现
    public int StrToInt2(String str) {
        if (str == null || str.length() == 0)
            return 0;
        int result = 0;
        boolean negative = false;//是否负数
        int i = 0, len = str.length();
        /**
         * limit 默认初始化为 负的 最大正整数 ，假如字符串表示的是正数
         * 那么result(在返回之前一直是负数形式)就必须和这个最大正数的负数来比较，
         * 判断是否溢出
         */
        int limit = -Integer.MAX_VALUE; // -2147483647
        int multmin;
        int digit;

        char firstChar = str.charAt(0);//首先看第一位
        if (firstChar < '0') { // Possible leading "+" or "-"
            if (firstChar == '-') {
                negative = true;
                limit = Integer.MIN_VALUE;//在负号的情况下，判断溢出的值就变成了 整数的 最小负数了
            } else if (firstChar != '+')//第一位不是数字和-只能是+
                return 0;
            if (len == 1) // Cannot have lone "+" or "-"
                return 0;
            i++;
        }
        multmin = limit / 10;
        while (i < len) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            digit = str.charAt(i++)-'0';//char转int
            if (digit < 0 || digit > 9)//0到9以外的数字
                return 0;

            //（最后一位的时候）判断溢出
            if (result < multmin) {
                return 0;
            }
            result *= 10;
            if (result < limit + digit) {
                return 0;
            }
            result -= digit;
        }
        //如果是正数就返回-result（result一直是负数）
        return negative ? result : -result;
    }

    public static void main(String[] args){
        String s = "-2147483649";
        int i = Integer.parseInt(s);
        Problem67 problem67 =  new Problem67();
        int n = problem67.StrToInt2(s);
        System.out.println(n);
    }
}
