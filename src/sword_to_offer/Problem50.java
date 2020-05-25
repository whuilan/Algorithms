package sword_to_offer;

/**
 * P243找出（字符串中）第一个只出现一次的字符，返回其索引
 * 由于256为常数，因此可以认为空间复杂度为O(1)
 */
public class Problem50 {
    public static int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0){
            return -1;
        }
        int[] times = new int[256];
        // 第一次遍历，统计每个字符出现的次数
        for (int i = 0; i < str.length(); i++){
            int index = str.charAt(i); // char类型自动转化为其ASCII编码值作为int类型的索引
            times[index]++;
        }
        // 第二次遍历，顺序找出第一个只出现一次的字符
        for (int i = 0; i < str.length(); i++){
            char index = str.charAt(i);
            if (times[index] == 1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        String str = "a我oogle";
        int index = FirstNotRepeatingChar(str);
        System.out.println(index);
    }
}
