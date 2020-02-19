package sword_to_offer;

import edu.princeton.cs.algs4.StdOut;

/**
 * P114 打印从1到最大的n位（十进制）数，其实就是n个从0到9的全排列，用递归的方法实现全排列,
 * 数字的每一位都可能是从0到9中的一个数，然后设置下一位，类似深度优先搜索
 */
public class Problem17 {
    public void print1ToMaxOfNDigits(int n){
        if(n <= 0){
            return;
        }
        // 用字符数组表达大数，此时数组索引的由低到高对应大数的高位到地位，与物理位置相同
        char[] number = new char[n];
        print1ToMaxOfNDigits(number, 0);
    }

    private void print1ToMaxOfNDigits(char[] num, int digit){  // digit表示number第几位
        if(digit == num.length){
            printCharArrayNumber(num);
            return;
        }
        for(int i = 0; i < 10; i++){
            num[digit] = (char) (i + '0'); // 整数i加上0的ASCII编码才成表示成字符的'i'
            print1ToMaxOfNDigits(num, digit + 1);
        }
    }

    private void printCharArrayNumber(char[] num){
        int index = 0;
        while (index < num.length && num[index] == '0'){
            index++;
        }
        while (index < num.length){
            System.out.print(num[index++]);
        }
        System.out.println();
    }

    public static void main(String[] args){
        int n = 3;
        Problem17 problem17 = new Problem17();
        problem17.print1ToMaxOfNDigits(n);
    }
}
