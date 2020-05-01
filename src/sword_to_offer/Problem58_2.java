package sword_to_offer;

/**
 * 题目二：左旋转字符串
 */
public class Problem58_2 {
    // 法一（第一反应）：借助substring方法
    public static String LeftRotateString1(String str,int n) {
        if(str == null || str.length() == 0 || n <= 0){
            return str;
        }
        int N = str.length();
        n = n % N;
        String result = str.substring(n, N) + str.substring(0, n);
        return result;
    }

    // 法二：借助题目一中的reverseCharArray方法，如将"abcdefg"左旋2位，
    // 先将"ab"和”cdefg"这两部分别翻转/倒序，得到"bagfedc"，再将整个字符串
    // 旋转得到”cdefgab"，即调用三次reverse即可
    public static String LeftRotateString2(String str,int n) {
        if(str == null || str.length() == 0 || n <= 0){
            return str;
        }
        char[] chars = str.toCharArray();
        int N = chars.length;
        n = n % N;
        reverseCharArray(chars, 0, n - 1);
        reverseCharArray(chars, n, N -1);
        reverseCharArray(chars, 0, N - 1);
        return String.valueOf(chars);
    }

    private static void reverseCharArray(char[] chars, int start, int end){
        while (start < end){
            char t = chars[start];
            chars[start] = chars[end];
            chars[end] = t;
            start++;
            end--;
        }
    }

    public static void main(String[] args){
        String str = "abcdefg";
        String leftRotateStr = LeftRotateString2(str, 2);
        System.out.println(leftRotateStr);
    }
}
