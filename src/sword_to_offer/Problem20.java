package sword_to_offer;

/**
 * P127表示数值的字符串, 遵循模式A[.[B]][e|EC]或.B[e|EC]，其中A为数值的整数部分，B为小数部分，
 * C为e或E后面的指数部分。此处方括号[]表示其中的内容是可选的，可要可不要，A和C为整数（可正可负），
 * B为正整数。注意要时刻检查数组是否越界
 */
public class Problem20 {
    // 法一： 剑指offer上的思路
    private int currentIdx = 0; // 当前字符的索引

    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0){   // 注意不要忽略空字符串的情况
            return false;
        }
        // 从第一个字符开始遍历,判断A
        boolean numeric = isInteger(str, currentIdx);  // 记录字符串是否遵循模式
        // 判断B
        if (currentIdx < str.length && str[currentIdx] == '.'){
            // 使用||的原因是小数有好几种形式，如.123(=0.123), 123.(=123.0), 12.3
            currentIdx++;
            numeric = isUnSignedInteger(str, currentIdx) || numeric;
        }
        // 判断C
        if (currentIdx < str.length && (str[currentIdx] == 'e' || str[currentIdx] == 'E')){
            currentIdx++;
            numeric = isInteger(str, currentIdx) && numeric;
        }
        if (numeric && currentIdx == str.length){  // 记住要确保字符串比较到了末尾（最后一个字符也被比较）
            return true;
        }
        return false;
    }

    // 是否为（带符号）整数, 只需判断传进来的第一个字符是否为‘+’和'-'，然后再调用isUnSignedInteger
    private boolean isInteger(char[] str, int index){
        if (index < str.length && (str[index] == '+' || str[index] == '-')){
            currentIdx++;
        }
        return isUnSignedInteger(str, currentIdx);
    }

    // 是否为无符号整数（正整数）
    private boolean isUnSignedInteger(char[] str, int index){
        int initialIdx = index;
        while (index < str.length && (str[index] - '0' >= 0 && str[index] - '0' <= 9)){
            index++;
        }
        currentIdx = index;
        if (currentIdx > initialIdx){
            return true;
        }
        return false;
    }

    // 法二：使用java内置的正则表达式匹配,A[.[B]][e|EC]或.B[e|EC]， 方括号[]中的部分可要可不要
    public boolean isNumeric_v2(char[] str) {
        if (str == null || str.length == 0){
            return false;
        }
        String string = new String(str); // 字符数组转换成字符串
        return string.matches("[+-]?\\d*(\\.\\d*)?([eE][+-]?\\d+)?");
    }

    public static void main(String[] args){
        char[] str = {'1', '.', '2'};
        Problem20 problem20 = new Problem20();
        // int a = str[3] - '0';
        if(problem20.isNumeric_v2(str)){
            System.out.println("isNumeric");
        }else {
            System.out.println("not numeric");
        }
    }
}
