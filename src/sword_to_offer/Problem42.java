package sword_to_offer;

/**
 * P218连续子数组的最大和，时间复杂度为O(n)（而用枚举法列出所有的n(n+1)/2个子数组
 * 再分别求和进行比较的时间复杂度为O(n^2)
 */
public class Problem42 {
    public static int FindGreatestSumOfSubArray(int[] array) throws Exception {
        if (array == null || array.length == 0){
            throw new Exception("Empty array!");
        }
        int sequentialSum = 0, maxSum = Integer.MIN_VALUE; // 注意maxSum不能初始化为0，要考虑到全负的数组
        for (int i = 0; i < array.length; i++){
            if (sequentialSum <= 0){
                sequentialSum = array[i];
            }else {
                sequentialSum += array[i];
            }
            if (sequentialSum > maxSum){
                maxSum = sequentialSum;
            }
        }
        return maxSum;
    }

    public static void main(String[] args){
        // int[] array = {1, -2, 3, 10, -4, 7, 2, -5};
        int[] array = {-2, -8, -1, -5, -9};
        try{
            int maxSum = FindGreatestSumOfSubArray(array);
            System.out.println(maxSum);
        }catch (Exception e){
            System.out.println("error");
        }
    }
}
