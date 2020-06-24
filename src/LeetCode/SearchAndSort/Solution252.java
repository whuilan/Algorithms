package LeetCode.SearchAndSort;

import java.util.Arrays;

/**
 * 会议室
 * 比较简单，先将区间按左端点排序，然后遍历，如果某个区间的右端点大于下一个区间的左端点，说明时间
 * 冲突，不能同时参加会议。
 */
public class Solution252 {
    public boolean canAttendMeetings(int[][] intervals) {
        // 加了非空判断居然错了，以后面试碰到这样的要问，笔试碰到了要留意，加和不加都试试
//        if (intervals == null || intervals.length == 0 || intervals[0].length == 0){
//            return false;
//        }
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        for (int i = 0; i < intervals.length - 1; i++){
            if (intervals[i][1] > intervals[i+1][0]){
                return false;
            }
        }
        return true;
    }
}
