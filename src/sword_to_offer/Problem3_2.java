package sword_to_offer;

/**
 * 不修改数组找出重复的数字,时间复杂度为:O(nlogn), 空间复杂度为O(1)，不能找出所有的重复数字
 */
public class Problem3_2 {
    public static int getDuplication(int[] nums, int length){
        if(nums == null || length <= 0){
            return -1;
        }
        int start = 1, end = length - 1; // 注意start初始值，题目P41中说了数组元素均在1~length-1之间
        while (start <= end){
            int mid = start + (end - start) / 2;
            int count = countRange(nums, length, start, mid);
            // 注意循环到最后的截止情况
            if(start == end){
                if(count > 1){
                    return start;
                }
                else {
                    break;
                }
            }
            if(count > (mid - start + 1)){
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static int countRange(int[] nums, int length, int start, int end){
        if(nums == null || length <= 0){
            return -1;
        }
        int count = 0;
        for(int i = 0;i < length;i++){
            if(nums[i] >= start && nums[i] <= end){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args){
        int[] nums = {2,4,5,4,3,2,6,7};
        int length = nums.length;
        int count = getDuplication(nums, length);
        System.out.println(count);
    }
}
