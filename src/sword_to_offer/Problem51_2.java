package sword_to_offer;

/**
 * 只是51题归并的另一种写法
 */
public class Problem51_2 {

    private long cnt = 0;
    private int[] tmp;  // 在这里声明辅助数组，而不是在 merge() 递归函数中声明

    public int InversePairs(int[] nums) {
        tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return (int) (cnt % 1000000007);
    }

    private void mergeSort(int[] nums, int l, int h) {
        if (h - l < 1)
            return;
        int m = l + (h - l) / 2;
        mergeSort(nums, l, m);
        mergeSort(nums, m + 1, h);
        merge(nums, l, m, h);
    }

    private void merge(int[] nums, int l, int m, int h) {
        int i = l, j = m + 1, k = l;
        while (i <= m || j <= h) {
            if (i > m)
                tmp[k] = nums[j++];
            else if (j > h)
                tmp[k] = nums[i++];
            else if (nums[i] <= nums[j])
                tmp[k] = nums[i++];
            else {
                tmp[k] = nums[j++];
                this.cnt += m - i + 1;  // nums[i] > nums[j]，说明 nums[i...mid] 都大于 nums[j]
            }
            k++;
        }
        for (k = l; k <= h; k++)
            nums[k] = tmp[k];
    }

    public static void main(String[] args){
        int[] arr = {3,8,6,3,8,7};
        Problem51_2 problem51 = new Problem51_2();
        int n = problem51.InversePairs(arr);
        System.out.println(n);
    }
}
