package textbook.chapter5_1;

public class Alphabet {
    private char[] alphabet; // 字符数组表示的字母表
    private int[] indexes; // 每个字符对应的索引
    private final int R; // 基数，即字母表中的字符数量

    // 根据s中的字符创建一张新的字母表
    public Alphabet(String s){
        /*
        创建unicode数组记录字母是否已经存在于字母表中，核查字母表中没有重复的字母，大小取两个字节0-65535，
        这样任意字符都能在unicode编码中找到自己的位置/索引
         */
        boolean[] unicode = new boolean[Character.MAX_VALUE];
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(unicode[c]){
                throw new IllegalArgumentException("Illegal alphabet: repeated character = '" + c +"'");
            }
            unicode[c] = true;
        }
        alphabet = s.toCharArray();
        R = s.length();
        /*
        indexes以字符为索引的数组，即使用字符的unicode编码为索引的数组，
        数组元素为输入字符串中每个字符对应的索引（0-R之间）
         */
        indexes = new int[Character.MAX_VALUE];
        for(int i = 0; i < indexes.length; i++){
            indexes[i] = -1;
        }
        for(int i = 0; i < R; i++){
            char c = alphabet[i];
            indexes[c] = i;
        }
    }
    public int R(){
        return R;
    }
    // 表示R个字符所需的比特数
    public int lgR(){
        int lgR = 0;
        for(int t = R - 1; t >= 1; t /= 2){ // 巧妙
            lgR++;
        }
        return lgR;
    }
    // c是否在字母表中
    public boolean contains(char c){
        return indexes[c] != -1;
    }
    // 由字母表中的索引位置获取字符
    public char toChar(int index){
        if(index < 0 || index >= R){
            throw new IllegalArgumentException("index must be between 0 and " + R + ": " + index);
        }
        return alphabet[index];
    }
    public String toChars(int[] indices){
        int L = indices.length;
        char[] a = new char[L];
        for(int i = 0; i < L; i++){
            int indice = indices[i];
            a[i] = toChar(indice);
        }
        String s = new String(a);
        return s;
    }
    // 获得字符c在字母表中的索引
    public int toIndex(char c){
        // 表示c要可以用unicode编码，并且存在于本字母表中
        if(c >= indexes.length || indexes[c] == -1){
            throw new IllegalArgumentException("Character c " + c + " not in alphabet");
        }
        return indexes[c];
    }
    // 将s转换为R进制的整数，即获取传入字符中每个字符对应的索引，注意这个s和输入本类的构造函数中的s不一定相同
    public int[] toIndexes(String s){
        char[] source = s.toCharArray();
        int L = source.length;
        int[] target = new int[L];
        for(int i = 0; i < L; i++){
            char c = source[i];
            target[i] = toIndex(c);
        }
        return target;
    }
}
