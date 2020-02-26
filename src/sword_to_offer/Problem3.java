package sword_to_offer;

/**
 * 面试题3，数组中重复的数字
 */
public class Problem3 {
    // 法一：暴力比较，时间复杂度为N^2，另外少了对非法输入的检测
//    public boolean duplicate(int numbers[],int length,int [] duplication) {
//        for(int i = 0; i < length; i++){
//            for(int j = i + 1; j < length; j++){
//                if(numbers[i] == numbers[j]){
//                    duplication[0] = numbers[i];
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    // 法二：将值为i的元素调整到第i个位置上,时间复杂度为O(N),空间复杂度为O(1)，加入对非法输入的检测
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if(numbers == null || length <= 0){
            return false;
        }
        for(int i = 0;i < length;i++){
            if(numbers[i] < 0 || numbers[i] > length -1){
                return false;
            }
        }
        for(int i = 0; i < length; i++){
            while(numbers[i] != i){
                if(numbers[i] == numbers[numbers[i]]){
                    // numbers[i]同时出现在索引为i和处，重复
                    duplication[0] = numbers[i];
                    return true;
                }
                // 交换i和numbers[i]位置处的值
                int temp = numbers[i];
                numbers[i] = numbers[temp];
                numbers[temp] = temp;
            }
        }
        return false;
    }

    public boolean duplicate2(int numbers[],int length,int [] duplication) {
        if(numbers == null || length <= 0){
            return false;
        }
        for(int i = 0; i < length; i++){
            while(i != numbers[i]){
                if(numbers[i] == numbers[numbers[i]]){
                    duplication[0] = numbers[i];
                    return true;
                }
                exch(numbers, i, numbers[i]);
            }
        }
        return false;
    }

    public void exch(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args){
        int[] numbers = {2, 3, 1, 0, 2, 5};
        int length = numbers.length;
        int[] duplication = new int[1];
        Problem3 ex = new Problem3();
        boolean isFound = ex.duplicate(numbers, length, duplication);
        System.out.println(isFound);
    }
}
