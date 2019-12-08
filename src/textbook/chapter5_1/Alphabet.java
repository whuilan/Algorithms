package textbook.chapter5_1;

public class Alphabet {
    private char[] alphabet;
    private int[] indexes;
    private final int R; // 基数，即字母表中的字符数量

    public Alphabet(String s){
        // 根据s中的字符创建一张新的字母表，注：先默认s中没有重复的字符串
        alphabet = s.toCharArray();
        R = s.length();
        indexes = new int[Character.MAX_VALUE]; // 这里不太懂
        for(int i = 0; i < indexes.length; i++){
            indexes[i] = -1;
        }
        for(int i = 0; i < R; i++){
            indexes[alphabet[i]] = i;
        }
    }
    public int R(){
        return R;
    }
    // 表示R所需的比特数
    public int lgR(){
        int lgR = 0;
        for(int t = R - 1; t >= 1; t /= 2){ // 巧妙
            lgR++;
        }
        return lgR;
    }
    // c是否在字母表中
    public boolean contains(char c){
        return indexes[c] != -1;        // 不是很懂
    }
    // 由字母表中的索引位置获取字符
    public char toChar(int index){
        if(index < 0 || index >= R){
            throw new IllegalArgumentException("index must be between 0 and " + R + ": " + index);
        }
        return alphabet[index];
    }
    // 获得字符c在字母表中的索引
    public int toIndex(char c){
        if(c >= indexes.length || indexes[c] == -1){
            throw new IllegalArgumentException("Character c " + c + " not in alphabet");
        }
        return indexes[c];
    }
}
