package sword_to_offer;

/**
 * P129调整数组顺序使奇数位于偶数前面
 */
public class Problem21 {
    // 函数一，书上的，不要求奇数和偶数的相对位置不变，和我想的一模一样，棒棒的~
    public void reOrderArray(int [] array) {
        if (array == null || array.length == 0){
            return;
        }
        int i = 0, j = array.length - 1;
        while (i < j){
            if (isEven(array[i])){  // array[i]为偶数
                exch(array, i, j--);
            }else {
                i++;
            }
        }
    }
    // 判断一个整数是否为偶数，从原来的判断处即第13行独立出来，解耦
    private boolean isEven(int n){
        return (n & 1) == 0;
    }

    // 交换索引为i、j处的值
    private void exch(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // 函数二，牛客上的，要求奇数和偶数的相对位置不变,时间复杂度O(n)，空间复杂度O(n)
    public void reOrderArray2(int[] array){
        if (array == null || array.length == 0){
            return;
        }
        // 先统计数组中有多少个奇数
        int oddNums = 0;
        for (int a : array){
            if (!isEven(a)){
                oddNums++;
            }
        }
        int[] arrayCopy = array.clone();   // 复制一份辅助数组
        int i = 0 , j = oddNums;   // i, j为奇数和偶数的起始指针
        for (int c : arrayCopy){
            if (!isEven(c)){
                array[i++] = c;
            }else {
                array[j++] = c;
            }
        }
    }

    public static void main(String[] args){
        int[] arr = {1, 2, 3, 4, 5};
        Problem21 problem21 = new Problem21();
        problem21.reOrderArray2(arr);
        for(int n : arr){
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
