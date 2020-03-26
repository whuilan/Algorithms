package sword_to_offer.sort;

/**
 * 堆排序：将N个元素排序，需要少于(2NlogN+2N)次比较（以及一半次数的交换）
 * 在最坏情况下也能保证使用~2NlogN次比较和恒定的额外空间（缺点：无法利用缓存：
 * 因为数组中的元素很少与相邻元素进行比较）
 */
public class Heap {
    public void sort(Comparable[] a){
        if (a == null || a.length == 0){
            return;
        }
        // 第一步：构造堆
        int N = a.length;
        for (int k = N / 2; k >= 1; k--){
            sink(a, k, N);
        }
        // 第二部：下沉排序
        while (N > 1){
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    private void sink(Comparable[] a, int k, int N){
        while (2 * k <= N){
            int j = 2 * k;
            if (j < N && less(a, j, j+1)){
                j++;
            }
            if (!less(a, k, j)){
                break;
            }
            exch(a, k, j);
            k = j;
        }
    }

    private boolean less(Comparable[] a, int i, int j){
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    private void exch(Comparable[] a, int i, int j){
        i--;
        j--;
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args){
        Integer[] a = {1,3,6,4,5,2,8,7,9,0,11,10};
        Heap heap = new Heap();
        heap.sort(a);
        for(int i : a){
            System.out.print(i + " ");
        }
    }
}
