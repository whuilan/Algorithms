package exercises;

import java.util.HashMap;

/**
 * 找出数组中重复最多的那个元素
 */
public class FrequencyCounter {
    public static int mostRepeat(int[] a){
        if (a == null || a.length == 0){
            return -1;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int v : a){
            if (!map.containsKey(v)){
                map.put(v, 1);
            }
            else {
                map.put(v, map.get(v) + 1);
            }
        }
        int mostV = a[0];
        for (int v : a){
            if (map.get(v) > map.get(mostV)){
                mostV = v;
            }
        }
        return map.get(mostV);
    }

    public static void main(String[] args){
        int[] a = {1,2,2,3,3,3,4};
        int mostV =  mostRepeat(a);
        System.out.println(mostV);
    }

}
