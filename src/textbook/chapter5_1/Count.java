package textbook.chapter5_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 从命令行参数接受一个字符串并打印出从从标准输入获得的每个字符的出现频率
 */
public class Count {
    public static void main(String[] args){
        Alphabet alphabet = new Alphabet(args[0]);
        int R = alphabet.R();
        int[] count = new int[R]; // count的索引即对应字符串中每个字符的索引

        String s = StdIn.readAll();
        int N = s.length();
        for(int i = 0; i < N; i++){
            char c = s.charAt(i);
            if (alphabet.contains(c)){
                // 使用toIndex获取字符c在alphabet中的索引即count的索引
                int index = alphabet.toIndex(c);
                count[index]++;
            }
        }
        // 如果输入中只含有字母表中的字母，可以把以上内循环改为如下简洁的形式
//        int[] indexes = alphabet.toIndexes(s);
//        for(int i = 0; i < N; i++){
//            count[indexes[i]]++;
//        }
        for(int i = 0; i < R; i++){
            StdOut.println(alphabet.toChar(i)+" "+ count[i]);
        }
    }
}
