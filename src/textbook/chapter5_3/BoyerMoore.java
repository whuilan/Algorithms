package textbook.chapter5_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * Boyer-Moore字符串匹配算法（启发式地处理不匹配的字符）
 */
public class BoyerMoore {
    private int[] right;  // 跳跃表：记录字母表中的每个字符在模式字符串中出现的最靠右的地方
    private String pat;
    public BoyerMoore(String pat){
        // 根据传入的模式字符串计算跳跃表
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        right = new int[R];
        // 初始化时默认所有字符都不在模式字符串中，将其在跳跃表中的值设为-1
        for(int c = 0; c < R; c++){
            right[c] = -1;
        }
        // 将在模式字符串中的字符设为其在模式字符串中最靠右的位置/索引
        for(int j = 0; j < M; j++){
            right[pat.charAt(j)] = j;
        }
    }

    public int search(String txt){
        int N = txt.length();
        int M = pat.length();
        int skip;  // 记录每一轮比较，应该将模式字符串向右移动多少个位置（即将i增加多少）
        for(int i = 0;  i < N - M; i += skip){
            skip = 0; // 进行每一轮比较（i取某个值时）,都将skip初始化为0,比较出现不匹配需要跳跃时再改变它的值
            // 接下来从右向左扫描模式字符串
            for(int j = M - 1; j >=0; j--){
                if (pat.charAt(j) != txt.charAt(i + j)){
                    skip = j - right[txt.charAt(i + j)];
                    if(skip < 0){
                        skip = 1;
                    }
                    break;
                }
            }
            if(skip == 0){
                return i;  // skip还是初始化值0,说明j的内循环中没有出现不匹配的情况，此时的i即为模式字符串在txt中的位置
            }
        }
        return N;    // 未找到匹配

    }

    // 下面这个search是在没看书的情况下自己写的，经用例代码测试也是对的
    public int search_while(String txt){
        int N = txt.length();
        int M = pat.length();
        int i = 0;
        int j = M - 1;
        while (i < N - M && j >= 0){
            if(txt.charAt(i + j) == pat.charAt(j)){
                j--;
            }
            else {
                j = M - 1;
                int s = j - right[txt.charAt(i + j)];
                if(s > 0){
                    i += s;
                }
                else {
                    i += 1;
                }
            }
        }
        if(j == -1){
            return i;
        }else {
            return N;
        }
    }

    public static void main(String[] args){
        String pat = args[0];
        String txt = args[1];
        StdOut.println(txt);
        BoyerMoore boyerMoore = new BoyerMoore(pat);
        int offset = boyerMoore.search(txt);
        for(int i = 0; i < offset; i++){
            StdOut.print(" ");
        }
        StdOut.println(pat);
    }
}
