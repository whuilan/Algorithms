package sword_to_offer;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * P214数据流中的中位数,时间复杂度为O(logN)，得到中位数复杂度为O(1)
 */
public class Problem41 {
    PriorityQueue<Integer> leftMaxPQ = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> rightMinPQ = new PriorityQueue<>();
    int N = 0;

    public void Insert(Integer num) {
        // 如果堆中元素总数为偶数，就将新元素放到左边最大堆中，但新元素可能比右边的元素大，为了保证左半边
        // 的元素均小于右半边的元素，应先将新元素放入右边最小堆中，再将其堆顶最小元素插入到左边最大堆中。
        if (N % 2 == 0){
            rightMinPQ.offer(num);
            leftMaxPQ.offer(rightMinPQ.poll());
        }else {
            leftMaxPQ.offer(num);
            rightMinPQ.offer(leftMaxPQ.poll());
        }
        N++;
    }

    public Double GetMedian() throws Exception{
        if (N == 0){
            throw new Exception("No numbers are available!");
        }
        if (N % 2 == 0){
            // 注意只用peek()返回堆顶元素就可以了，不能使用poll()删除！因为后面还要持续地读入数据，返回中位数
            return (rightMinPQ.peek() + leftMaxPQ.peek()) / 2.0; // 除以2.0就自动转成double啦，然后java自动装箱为Double
        }
        return (double) leftMaxPQ.peek();
    }
}
