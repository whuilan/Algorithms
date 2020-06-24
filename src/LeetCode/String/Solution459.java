package LeetCode.String;

/**
 * 招银网络科技提前批笔试最后一题
 * 重复的子字符串：给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。
 * 给定的字符串只含有小写英文字母，并且长度不超过10000。
 * 核心思想：由重复子串组成的串的特点：按位旋转，转动一定位数，会和原来的串一模一样，如"abcabc",
 * 旋转1位得到“bcabca",2位得到"cabcab",旋转3位得到"abcabc"和原来一样。也就是如果子串有k位，
 * 旋转k位就会得到和原来一模一样的子串，但现在的问题是我们不知道重复的子串是啥，有多少位。暴力地去
 * 试会超时。有个很巧妙的方法！倍长字符串！也就是在字符串后面拼接上它自己，这时候的字符串包含了原来
 * 字符串旋转任意位数的所有情况！（类似滑动窗口）拼接后破坏首尾子串，如果剩下的部分还包含原字符串则
 * 说明旋转一定位数可以得到原来的字符串，即原字符串是由重复的子串构成！
 */
public class Solution459 {
    // 很简洁，主体代码就两行！
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0){
            return false; // 要问空或""算不算
        }
        // 将原字符串拷贝一遍组成新字符串
        String str = s + s;
        // 插头去尾留中间，如果还包含原字符串则说明原字符串是由一个子串重复多次构成
        return str.substring(1, str.length() - 1).contains(s);
    }

    // 正则写法
    public boolean repeatedSubstringPattern2(String s) {
        String regex = "([a-z]+)(\\1)+";
        return s.matches(regex);
    }
}
