package textbook.chapter5_3;

import edu.princeton.cs.algs4.StdOut;

import java.math.BigInteger;
import java.util.Random;

/**
 * Rabin-Karp指纹字符串查找方法（基于散列）
 */
public class RabinKarp {
    private String pat;           // 仅拉斯维加斯算法需要
    private long patHash;         // 模式字符串的散列值
    private int M;                // 模式字符串的长度
    private long Q;               // 一个很大的素数
    private int R = 256;          // 字母表的大小，对应于R进制
    private long RM;              // R^(M-1) % Q，用于减去前一个数时的计算  ？？为什么要取余

    public RabinKarp(String pat){
        this.pat = pat;
        M = pat.length();
        Q = longRandomPrime();    // 随机生成一个很大的素数
        patHash = hash(pat, M);   // 计算得到模式字符串的散列值
        RM = 1;
        for(int i = 1; i < M; i++){
            RM = (R * RM) % Q;
        }
    }

    public int search(String txt){
        // 在文本中查找和模式字符串相等的散列值
        long txtHash = hash(txt, M);  // 文本中从第一个字符开始的长为M的子字符串的散列值
        if(txtHash == patHash && check(txt,0)){
            return 0;   // 一开始就匹配成功
        }
        int N = txt.length();
        // 减去第一个数字，加上最后一个数字，再次检查匹配
        for(int i = M; i < N; i++){
            // i从M开始循环是因为i代表子字符串的最后一个数字，而不是第一个数字，所以才能取到M
            txtHash = (txtHash + Q - txt.charAt(i - M) * RM % Q) % Q; // 额外加上一个Q来保证所有数均为正，这样取余操作才能得到预期的结果
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if(txtHash == patHash && check(txt,i - M + 1)){
                return i - M + 1;    // 找到匹配
            }
        }
        return N;   // 未找到匹配
    }

    private long longRandomPrime(){
        // 使用BigInteger中的API来生成大素数
        BigInteger bigPrime = new BigInteger(31, new Random());
        return bigPrime.longValue();
    }

    // 将M作为参数传递，这样就可以将它同时用于模式字符串和正文
    private long hash(String key, int M){
        // Horner方法：除留余数法计算key[0..M-1]的散列值
        long h = 0;
        for(int i = 0; i < M; i++){
            int c = key.charAt(i);
            h = (R * h + c) % Q;
        }
        return h;
    }

    /**
    对在文本字符串中找到的和patHash相等的子字符串进行验证，检验是否是完全匹配（散列值相等的字符串不一定是相同的字符串）
    蒙特卡洛法验证永远返回true:将散列表的规模"Q"设为任意大的一个值（大于10^20的long型值），使得一个随机键的散列值与
     模式字符串的散列值冲突的概率小于10^-20，因此可以认为匹配到的和模式字符串散列值相等的子字符串一定和模式字符串相等
     */
    private boolean check(int i){
        return true;
    }
    // 拉斯维加斯方法验证：逐个验证模式pat与txt[i..i - M + 1]是否每个字符都相同
    private boolean check(String txt, int i){
        for(int j = 0; j < M; j++){
            if(pat.charAt(j) != txt.charAt(i + j)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        String pat = args[0];
        String txt = args[1];
        StdOut.println(txt);
        RabinKarp rabinKarp = new RabinKarp(pat);
        int offset = rabinKarp.search(txt);
        for(int i = 0; i < offset; i++){
            StdOut.print(" ");
        }
        StdOut.println(pat);
    }
}
