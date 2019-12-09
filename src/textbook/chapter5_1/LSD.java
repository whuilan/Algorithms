package textbook.chapter5_1;

/**
 * 低位优先的字符串排序（基于键索引计数法）
 */
public class LSD {
    public static void sort(String[] a, int W){
        // 通过前M个字符将a[]排序
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];  // 辅助数组
        // 根据第w个字符用键索引计数法排序
        for(int w = W - 1; w >= 0; w--){
            int[] count = new int[R + 1];  // 每个字符出现的次数
            // 第一步，计算相应位上每个字符出现的频率
            for(int i = 0; i < N; i++){
                count[a[i].charAt(w) + 1]++;
            }
            // 第二步，将频率转换为索引
            for(int r = 0; r < R; r++){
                count[r + 1] += count[r];
            }
            // 第三步，将元素分类
            for(int i = 0; i < N; i++){
                // 每个元素在aux中的位置在是由它的键对应的count[]值决定的
                int sorted_index = count[a[i].charAt(w)]++;
                aux[sorted_index] = a[i];
            }
            // 第四步，回写
            for(int i = 0; i < N; i++){
                a[i] = aux[i];
            }
        }
    }
}
