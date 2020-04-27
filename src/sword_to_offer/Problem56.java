package sword_to_offer;

/**
 * P275数组中数字出现的次数 考点：二进制与位运算
 * 题目一：数组中只出现一次的两个数字，要求时间复杂度为O(n)，空间复杂度为O(1)
 */
public class Problem56 {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if (array == null || array.length < 2){
            return;
        }
        // 将原数组中的数依次按位与，得到的最后的结果wholeXor是两个只出现一次的数的异或运算结果
        int wholeXor = 0; // 0与任何数异或都等于该数本身
        for (int i = 0; i < array.length; i++){
            wholeXor  ^= array[i];
        }
        // 获得wholeXor中最右边的一个1的位置
        int indexOfOne = getFirst1Index(wholeXor);
        for (int num : array){
            if (isOneAtIndex(num, indexOfOne)){
                num1[0] ^= num;
            }else {
                num2[0] ^= num;
            }
        }
    }

    // 获取整数num的二进制表示中最右边等于1的那一位的索引位置
    private int getFirst1Index(int num){
        if (num != 0){
            int index = 0;
            while ((num & 1) != 1){
                index++;
                num = num >> 1;
            }
            return index;
        }
        return -1;
    }

    // 判断整数num的二进制表示中从右边数起的第index位是否为1
    private boolean isOneAtIndex(int num, int index){
         num = num >> index;
         return (num & 1) == 1;
    }

    public static void main(String[] args){
        int[] arr = {2,4,3,6,3,2,5,5};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        Problem56 problem56 = new Problem56();
        problem56.FindNumsAppearOnce(arr, num1, num2);
        System.out.println(num1[0] + " " + num2[0]);
    }
}