package sword_to_offer;

import edu.princeton.cs.algs4.StdOut;

/**
 * P110 数值的整数次方（特殊输入，异常处理）
 * 注意0^0即0的0次方在数学上没有任何意义，返回0或者1都可以
 */
public class Problem16 {
    public double Power(double base, int exponent) throws Exception {
        if(Double.compare(base, 0.0) == 0 && exponent < 0){
            throw new Exception("Invalid input");
        }
        int nonNegativeExp = exponent;
        if(exponent < 0){
            nonNegativeExp = Math.abs(exponent);
        }
        double result = PowerForNonNegativeExp(base, nonNegativeExp);
        if(exponent < 0){
            return 1.0 / result;
        }
        return result;
    }

    // 法一：简单直接的方式，循环次数为指数exponent的大小
    private double PowerForNonNegativeExp_v1(double base, int exponent){
        double result = 1.0;
        for(int i = 1; i <= exponent; i++){
            result *= base;
        }
        return result;
    }

    // 法二：优化法一，P112公式，递归次数为log(exponent), 并且用位运算代替乘除，提高效率
    private double PowerForNonNegativeExp(double base, int exponent){
        if(exponent == 0){
            return 1.0;
        }
        if(exponent == 1){
            return base;
        }
        double result = PowerForNonNegativeExp(base * base, exponent >> 1);
        if((1 & exponent) == 1){  // 判断指数exponent是否为奇数，相当于exponent % 2 == 1
            result *= base;
        }
        return  result;
    }

    public static void main(String[] args){
        double a = 3;
        int b = 5;
        Problem16 problem16 = new Problem16();
        try{
            double c = problem16.Power(a, b);
            StdOut.println(c);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
