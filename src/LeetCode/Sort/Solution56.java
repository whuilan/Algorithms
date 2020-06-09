package LeetCode.Sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 合并区间（medium）：给出一个区间的集合，请合并所有重叠的区间
 * 思路：先将二维数组中的区间按照最小值/最大值排序，再两两合并，一共n-1次
 * 我卡在第一步排序，因为这不是一维数组的单个元素，而是二维数组，其实用Lambda表达式还是一样的。
 * 另外我合并的思路是根据第一个区间右端点和第二个区间左端点的大小关系分三种情况讨论，实现起来
 * 代码非常复杂，并且用普通循环的话不知道怎么用合并后的区间去和下一个区间进行比较、合并。而甜姨
 * 只用了两行代码解决了这个问题，牛逼！
 */
public class Solution56 {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0){
            return new int[0][0];
        }
        // 根据每个区间的左端点/最小值将区间排序，我就是卡在这里！
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        int[][] ret = new int[intervals.length][2];
        int idx = -1; // idx表示ret的当前索引
        for (int[] interval : intervals){
            // 如果idx为-1或当前区间interval的左端点大于合并结果ret中最后一个区间的右端点
            // 则说明没有交集，直接给ret中添加一个新的区间
            if (idx == -1 || interval[0] > ret[idx][1]){
                ret[++idx] = interval;
            }
            // 否在存在交集，更新当前ret的最后一个区间
            else {
                ret[idx][1] = Math.max(ret[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(ret, idx + 1); // 复制指定长度的数组，去除ret后面为0的数组
    }

    public static void main(String[] args){
        int[][] intervals = {{8,10},{1,3},{15,18},{2,6}};
        Solution56 solution56 = new Solution56();
        int[][] mergedIntervals = solution56.merge(intervals);
        System.out.print("finish");
    }
}
