package sword_to_offer;

/**
 * P307求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case
 * 等关键字及条件判断语句（A?B:C）
 * 关键思路：使用逻辑与&&运算的短路特点，当(a && b)中a为false时，就不再执行b，利用这个特点
 * 来代替递归时用if来判断递归结束的语句
 */
public class Problem64 {
    public static int Sum_Solution(int n) {
        int sum = n;
        boolean continueAdd = n > 1 && (sum += Sum_Solution(n-1)) > 0;
        return sum;
    }

    public static void main(String[] args){
        int s = Sum_Solution(3);
        System.out.println(s);
    }
}
