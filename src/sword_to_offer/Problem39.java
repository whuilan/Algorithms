package sword_to_offer;

/**
 * P205数组中出现次数超过一半的数字
 */
public class Problem39 {
    public static int MoreThanHalfNum_Solution(int [] array) {
        if (array == null || array.length == 0){
            return 0;
        }
        int target = array[0], cnt = 1;
        for (int i = 1; i < array.length; i++){
            if (array[i] == target){
                cnt++;
            }
            else {
                cnt--;
                if (cnt == 0){
                    target = array[i];
                    cnt = 1;
                }
            }
        }
        // 统计最终target的次数到底有没有超过数组长度的一半
        int times = 0;
        for (int num : array){
            if (num == target){
                times++;
            }
        }
        return times > array.length / 2 ? target : 0;
    }

    public static void main(String[] args){
        int[] arr = {2,1,2,3,2,4,2,5};
        int r = MoreThanHalfNum_Solution(arr);
        System.out.println(r);
    }
}
