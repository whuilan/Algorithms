package sword_to_offer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * P209最小的K个数
 */
public class Problem40 {
    // 解法一：基于快排的Partition切分来实现，时间复杂度为O(n)，但需要改变输入的数组。
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || input.length == 0 || k <= 0 || k > input.length){
            return list;
        }
        GetLeastNumbers_Solution(input, k - 1, 0, input.length - 1);
        for (int i = 0; i < k; i++){
            list.add(input[i]);
        }
        return list;
    }

    // 递归调用：在索引范围为(lo..hi)之中查找j==k
    public void GetLeastNumbers_Solution(int[] input, int k, int lo, int hi){
        if (lo >= hi){
            return;
        }
        int j = Partition(input, lo, hi);
        if (j < k){
            GetLeastNumbers_Solution(input, k, j + 1, hi);
        }
        else if (j > k){
            GetLeastNumbers_Solution(input, k, lo, j -1);
        }
        else {
            return;
        }
    }

    private int Partition(int[] input, int lo, int hi){
        int i = lo, j = hi + 1;
        int v = input[lo];
        while (true){
            while (input[++i] < v){
                if (i == hi){
                    break;
                }
            }
            while (v < input[--j]){}   // j=lo时就不会满足循环条件了，因此不需要加if判断
            if (i >= j){
                break;
            }
            swap(input, i, j);
        }
        swap(input, lo, j);
        return j;
    }

    private void swap(int[] input, int i, int j){
        int t = input[i];
        input[i] = input[j];
        input[j] = t;
    }

    // 法二：优先队列（最大堆），时间复杂度为O(nlogk)(<<O(nlogn))
    public ArrayList<Integer> GetLeastNumbers_Solution2(int [] input, int k) {
        if (input == null || input.length == 0 || k <= 0 || k > input.length){
            return new ArrayList<>();
        }
        // java内置的优先队列默认是最小堆，需要转换为最大堆
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
        for (int n : input){
            maxPQ.offer(n);
            if (maxPQ.size() > k){
                maxPQ.poll();
            }
        }
        return new ArrayList<>(maxPQ);
    }

    public static void main(String[] args){
        int[] input = {4,5,1,6,2,7,3,8};
        int k = 4;
        Problem40 problem40 = new Problem40();
        ArrayList<Integer> minK = problem40.GetLeastNumbers_Solution2(input, k);
        for(int n : minK){
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
