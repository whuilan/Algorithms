package sword_to_offer;
import java.util.ArrayList;
import java.util.Collections;

/**
 * P227把数组排成最小的数
 */
public class Problem45 {
    public static String PrintMinNumber(int [] numbers) {
        if (numbers == null || numbers.length == 0){
            return "";
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (int num : numbers){
            arrayList.add(num + "");  // 将原来数组中的每个数用字符串表示
        }
        Collections.sort(arrayList, (strNum1, strNum2) -> (strNum1 + strNum2).compareTo(strNum2 + strNum1));
        StringBuilder sb = new StringBuilder();
        for (String s : arrayList){
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args){
        int[] numbers = {3, 32, 321};
        String min = PrintMinNumber(numbers);
        System.out.println(min);
    }
}
