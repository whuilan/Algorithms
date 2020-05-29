package sword_to_offer;

/**
 * P310不用加减乘除做加法
 * 思路：位运算！
 */
public class Problem65 {
    // 第一次写的
    public static int Add(int num1,int num2) {
        while (true){
            // 考虑进位，只有1和1才会产生进位，因此用&运算来模拟获得进位
            int and = num1 & num2;
            // 用异或来代替不考虑进位的加法：0加0、1加1都为0，0加1、1+0为1，符合异或运算特点
            num1 = num1 ^ num2;
            // 当前num1和num2没有产生进位，则终止循环，此时的num1就是最终的结果
            if (and == 0){
                break;
            }
            // 真正的进位相当于向左移动一位
            num2 = and << 1;
        }
        return num1;
    }

    // 第二次写的，更简洁易懂！
    public static int Add2(int num1,int num2) {
        while ((num1 & num2) != 0){
            // 用异或来模拟不考虑进位的位与运算
            int xor = num1 ^ num2;
            // 用与来求进位，求了之后记得一定要左移一位！
            int and = num1 & num2;
            num1 = xor;
            num2 = and << 1; // 不能忘/漏
        }
        return (num1 ^ num2);
    }

    public static void main(String[] args){
        int sum = Add(3, 7);
        System.out.println(sum);
    }
}
