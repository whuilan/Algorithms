package sword_to_offer;

/**
 * P243（字符串中）第一个只出现一次的字符
 */
public class Problem50 {
    public static int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0){
            return -1;
        }
        int[] times = new int[256];
        for (int i = 0; i < str.length(); i++){
            int index = str.charAt(i); // char类型自动转化为其ASCII编码值作为int类型的索引
            times[index]++;
        }
        for (int i = 0; i < str.length(); i++){
            char index = str.charAt(i);
            if (times[index] == 1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        String str = "google";
        int index = FirstNotRepeatingChar(str);
        System.out.println(index);
    }
}
