package textbook.chapter5_3;

/**
 * 暴力字符串查找的两种方法，总的思路：一个指针i跟踪文本，一个指针j跟踪模式
 */
public class DirectSearch {
    // 方法1
    public static int searchOne(String pat, String txt){
        int M = pat.length();
        int N = txt.length();
        /*
        注意i的取值范围，如果在模式字符串结束之前，文本字符串就已经结束了（i=N-M+1）,那么就没有找到匹配，
        即模式字符串在文本中不存在，约定在不存在时返回N的值
         */
        for(int i = 0; i <= N - M; i++){
            int j;
            for(j = 0; j < M; j++){
                if(txt.charAt(i + j) != pat.charAt(j)){
                    break;
                }
            }
            if(j == M){
                return i;  // 找到匹配，返回pat在txt中第一次出现的位置
            }
        }
        return N;
    }

    // 方法2 显示回退的实现方式，i相当于上一段代码中的i+j，即指向已经匹配过的字符序列的末端
    public static int searchTwo(String pat, String txt){
        int j, M = pat.length();
        int i, N = txt.length();
        for(i = 0, j = 0; i < N & j < M; i++){
             if(txt.charAt(i) == pat.charAt(j)){
                 j++;
             }else {
                 i -= j; // 注意此处的回退非常重要,i和j的差值就是本次比较在文本中的起始位置i
                 j = 0;
             }
        }
        if(j == M){
            return i - M; // 找到匹配
        }else {
            return N;
        }
    }
}
