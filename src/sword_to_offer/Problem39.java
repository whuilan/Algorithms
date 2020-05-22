package sword_to_offer;

/**
 * P205数组中出现次数超过一半的数字
 */
public class Problem39 {
    /*
    法一：直观易懂。时间复杂度为O(n)，空间复杂度为O(1)
        遍历数组时保存两个值，一个是数组中的一个数字target，一个是该数字出现的次数
        如果存在出现次数超过一半的数字，那么最后记录的数字target就是它。
        * 注意就算数组中没有数字出现的次数超过一半，target也总会是某个数字，因此遍历完
        后还要去统计一下target到底有没有超过一半。
        cnt在这里的作用是变更target（变不变/变成谁）
     */
    public static int MoreThanHalfNum_Solution1(int [] array) {
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

    /*
    法二：借助快排的切分方法partition()，时间复杂度也为O(n)，但会改变输入数组。
         如果数组中有数字的出现次数超过一半，那么将数组排序后数组正中间上就是该数字
         因此借助pattition()来找出排序后索引为2/N的数字，但是不需要将整个数组排序
         我们找到这个索引的数字即可
     */
    // 基于循环的写法
    public int MoreThanHalfNum_Solution2_1(int [] array) {
        if (array == null || array.length == 0){
            return 0;
        }
        if (array.length == 1){
            return array[0];
        }
        int target = 0;
        int N = array.length;
        int lo = 0, hi = N - 1;
        while (lo < hi){
            int index = partition(array, lo, hi);
            if (index < N / 2){
                lo = index + 1;
            }
            else if(index > N / 2){
                hi = index - 1;
            }
            else {
                target = array[lo];
                break;
            }
        }
        int times = 0;
        for (int num : array){
            if (num == target){
                times++;
            }
        }
        return times > array.length / 2 ? target : 0;
    }

    // 基于递归的写法
    public int MoreThanHalfNum_Solution2_2(int[] array){
        if (array == null || array.length == 0){
            return 0;
        }
        int N = array.length;
        MoreThanHalfNum_Solution2_2(array,0,N - 1, N / 2);
        // 利用paititon找到了排在正中间的元素
        int target = array[N/2];
        int times = 0;
        for (int num : array){
            if (num == target){
                times++;
            }
        }
        return times > array.length / 2 ? target : 0;
    }

    private void MoreThanHalfNum_Solution2_2(int[] a, int lo, int hi, int targetIdx){
        if (hi <= lo){
            return;
        }
        int j = partition(a, lo, hi);
        if (j < targetIdx){
            MoreThanHalfNum_Solution2_2(a, j + 1 , hi, targetIdx);
        }
        else if(j > targetIdx){
            MoreThanHalfNum_Solution2_2(a, lo, j -1, targetIdx);
        }
        else {
            return;
        }
    }

    // 切分：排定a[lo]的位置，返回其排序后的索引
    private int partition(int[] a, int lo, int hi){
        int i = lo, j = hi+1;
        int v = a[lo];
        while (true){
            while (a[++i] < v){
                if (i == hi){
                    break;
                }
            }
            while (v < a[--j]){}
            if (i >= j){
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private void exch(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args){
        int[] arr = {1,3,2};
        Problem39 problem39 = new Problem39();
        int r = problem39.MoreThanHalfNum_Solution2_2(arr);
        System.out.println(r);
    }
}
