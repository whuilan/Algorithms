package sword_to_offer;

/**
 * P310不用加减乘除做加法
 * 思路：位运算！
 */
public class Problem65 {
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
            // 真正的进位相当于想左移动一位
            num2 = and << 1;
        }
        return num1;
    }

    public static void main(String[] args){
        int sum = Add(3, 7);
        System.out.println(sum);
    }
}
