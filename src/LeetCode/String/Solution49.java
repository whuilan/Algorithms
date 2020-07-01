package LeetCode.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 字母异位词分组。给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 这个题的难点在于怎么判断两个词互为异位词，我的想法是互为异位词的两个词中它们的字符相同，且每个字符出现
 * 的次数也相同，所以就想到了用map来存储每个字符串的字符及其出现次数，但是这样实现起来比较困难且时间和
 * 空间复杂度都有点大（当然笔试的时候如果没有想出更好的方法可以试试~）
 * 官方解法：互为异位词的两个字符串，它们各自按字符排序之后是相等的！如"eat"和"tea"，排序之后都是“aet"
 * 巧妙！因此可以将排序字符串作为键，异位词列表作为值
 * 时间复杂度: O(NKlogK)，N表示strs的长度，K表示最长字符串的长度
 * 空间复杂度：O(NK)，
 */
public class Solution49 {
    public static List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0){
            return new ArrayList<>();
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs){
            char[] chars = str.toCharArray(); // 先转化成字符数组得到其排序字符串！
            Arrays.sort(chars);
            String sortedStr = String.valueOf(chars); // 排序后的字符数组转换成字符串
            if (!map.containsKey(sortedStr)){
                map.put(sortedStr, new ArrayList<>());
            }
            map.get(sortedStr).add(str);
        }
        return new ArrayList<>(map.values()); // 注意java里获得map中的所有的值的方法
    }

    public static void main(String[] args){
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams(strs);
        System.out.println("end");
    }
}
