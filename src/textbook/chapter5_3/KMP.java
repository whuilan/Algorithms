package textbook.chapter5_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * Knuth-Morris-Pratt字符串查找算法：确定有限状态自动机DFA
 */
public class KMP {
    public String pat;    // 需要查找的模式字符串
    private int[][] dfa;  // 确定有限状态自动机，dfa[txt.charAt(i)][j]表示的是在比较了txt.charAt(i)和pat.charAt(j)之后，
                          // 应该继续和txt.charAt(i+1)比较的模式字符的位置！
    public KMP(String pat){
        // 根据模式字符串构造DFA
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for(int X = 0, j = 1; j < M; j++){
            // 相当于在pat.charAt(j)匹配失败时将文本指针完全回退，去处理前j-1个字符，最终到达的状态，将这个状态完全复制到状态j
            for(char c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];
            }
            dfa[pat.charAt(j)][j] = j + 1;  // 设置匹配成功情况下的值
            // 重启位置X的更新，相当于在现有位置X处文本指针完全回退到处理第j-1个字符时的情形
            X = dfa[pat.charAt(j)][X];
        }
    }
    public int search(String txt){
        // 在txt上模拟DFA的运行
        int i, N = txt.length();
        int j, M = pat.length();
        for(i = 0, j = 0; i < N && j < M; i++){
            j = dfa[txt.charAt(i)][j];
        }
        if(j == M){
            return i - M;   // 找到匹配（到达模式字符串的结尾）
        }else {
            return N;       // 未找到匹配（到达文本字符串的结尾）
        }
    }
    public static void main(String[] args){
        String pat = args[0];
        String txt = args[1];
        StdOut.println(txt);
        KMP kmp = new KMP(pat);
        int offset = kmp.search(txt);
        for(int i = 0; i < offset; i++){
            StdOut.print(" ");
        }
        StdOut.println(pat);
    }
}
