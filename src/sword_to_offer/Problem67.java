package sword_to_offer;

/**
 * P318字符串转换为整数，如输入字符串“123”，输出数字123
 * 需要想到的各种可能输入：null/空字符串、负数、字符串中有其他字符（该字符串不能表示一个整数）
 * 还有溢出！整数的范围为：-2147483648~2147483647
 */
public class Problem67 {
    // 法一：最简单的，不考虑0的区分（“0”或者非法输入都返回0）和溢出，面试时可以先写这个
    // 能通过85.71%
    public int StrToInt1(String str) {
        // *null或空字符串为非法输入
        if (str == null || str.length() == 0){
            return 0;
        }
        // *负数
        boolean isNegative = str.charAt(0) == '-';
        // 设置返回的整数的初始值为0
        int num = 0;
        // 遍历字符串，计算数值
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            // *第一位为'+'或'-'符号位，则数值的计算应该从第二位开始
            if (i == 0 && (c == '+' || c == '-')){
                continue;
            }
            // *字符串中有字符不在0-9之间，则输入非法，直接返回0
            if (c < '0' || c > '9'){
                return 0;
            }
            num = num * 10 + (c - '0');
        }
        return isNegative ? -num : num;
    }

    public int StrToInt1_2(String str) {
        if (str == null || str.length() == 0){
            return 0;
        }
        boolean negative = false;
        int result = 0;
        int i = 0, len = str.length();
        // 先判断第一个字符
        char firstChar = str.charAt(0);
        if (firstChar < '0' || firstChar > '9'){
            if (firstChar == '-'){
                negative = true;
            }
            // 非法字符
            else if(firstChar != '+'){
                return 0;
            }
            // 字符串只有一个符号位'+'或'-'
            if (len == 1){
                return 0;
            }
            i++;
        }
        int digit;
        while (i < len){
            digit = str.charAt(i++) - '0';
            if (digit < 0 || digit > 9){
                return 0;
            }
            result = result * 10 + digit;
        }
        return negative ? -result : result;
    }

    // 考虑溢出的实现，其实就是库函数Integer.parseInt()的内部实现
    public int StrToInt2(String str) {
        if (str == null || str.length() == 0)
            return 0;
        boolean negative = false;//是否负数
        int result = 0;
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
        multmin = limit / 10; // -214748364
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

    public int StrToInt2_2(String str) {
        // 字符串非空
        if (str == null || str.length() == 0){
            return 0;
        }
        // 记录负数
        boolean negative = false;
        // 将转换后的数字初始化为0
        int result = 0;
        // 计算时遍历的指针
        int i = 0, len = str.length();
        int limit = - Integer.MAX_VALUE; // -2147483647
        // 先看一下第一个字符，可能为正负号，但不能只有正负号
        char firstChar = str.charAt(0);
        if (firstChar < '0' || firstChar > '9'){
            if (firstChar == '-'){
                negative = true;
                // 更新limit为负数最小值
                limit = Integer.MIN_VALUE; // -2147483648
            }
            else if (firstChar != '+'){
                return 0;  // 不为'-'也不为'+'，说明是无效字符，直接返回0
            }
            if (len == 1){
                return 0;  // 只有一个'+'或'-'也是无效的
            }
            i++; // 更新遍历起始索引
        }
        int oneDigitAheadOfLimit = limit / 10;  // -214748364
        int digit;
        while (i < len){
            digit = str.charAt(i++) - '0'; // 这里老是忘了让i自增！那就无限循环了！
            if (digit < 0 || digit > 9){
                return 0;
            }
            if (result < oneDigitAheadOfLimit){
                return 0; // 如果比-214748364小，就不用往下乘以10了，因为乘以10之后肯定超出边界了。
            }
            result = result * 10;
            if (result < limit + digit){
                return 0; // 超出边界
            }
            result -= digit;
        }
        return negative ? result : -result;
    }

    public static void main(String[] args){
        String s = "-214";
        int i = Integer.parseInt(s);
        Problem67 problem67 =  new Problem67();
        int n = problem67.StrToInt1_2(s);
        System.out.println(n);
    }
}
