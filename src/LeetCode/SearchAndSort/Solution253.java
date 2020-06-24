package LeetCode.SearchAndSort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 会议室II，简单会议室的升级
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间，为避免会议冲突，
 * 同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 * 核心思想；从每个会议的角度看，我们将每个会议按照开始时间排序，从第一个会议开始遍历，
 * 到了会议开始时间时，会去检查现有的会议室有没有已经开完会的，如果有就会在这个房间开会。
 * 如果没有空闲房间又到时间了，那只能新开一间会议室。晚开始可能晚结束,也可能比早开始的更早结束，
 * 所以需要一个高效的方法来判断当前是否有会议室空闲（记录最早开会完的房间）。
 * 基于这些分析，最小堆（优先队列）是很合适的！可以将所有房间（有冲突的会议）存在最小堆中，堆中
 * 的键值是正在开会的那些会议的结束时间，那么堆顶就是最早结束的时间，即它最先开完会腾出房间，我们
 * 遍历到一个会议的时候，只需要检查堆顶会议是否开完，如果开完了可以用它的房间（把它替换掉）。如果
 * 堆顶的会议都没开完，那堆中其他和堆顶有冲突的会议更没有结束，因此需要新开一个房间，即直接将其插入
 * 堆中。
 * 时间复杂度：O(NlogN),包括第一部分排序和最小堆的插入/删除
 * 空间复杂度：O(N)，额外空间用于建立最小堆。在最坏的情况下，堆需要容纳全部 N个元素。
 */
public class Solution253 {
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // 先按会议开始时间排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 创建最小堆，并按照每个会议的结束时间排序
        PriorityQueue<int[]> pq = new PriorityQueue<>((v1,v2) -> v1[1] - v2[1]);
        // 将第一个会议的时间压入最小堆
        pq.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++){
            int[] earliest = pq.peek();
            // 当前会议时间开始前，堆顶会议已经结束了，即有空闲房间
            if (intervals[i][0] >= earliest[1]){
                pq.poll();
            }
            pq.offer(intervals[i]);
        }
        return pq.size();
    }

    public static void main(String[] args){
        int[][] intervals = {{1,10},{2,7},{3,19},{8,12},{10,20},{11,30}};
        int rooms = minMeetingRooms(intervals);
        System.out.println(rooms);
    }
}
