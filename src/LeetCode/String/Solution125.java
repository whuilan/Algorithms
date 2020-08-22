package LeetCode.String;

public class Solution125 {
    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0){
            return true;
        }
        int lo = 0, hi = s.length() - 1;
        while (lo < hi){
            // 忽略非字母和数字字符
            while (lo < hi && !Character.isLetterOrDigit(s.charAt(lo))){
                lo++;
            }
            while (lo < hi && !Character.isLetterOrDigit(s.charAt(hi))){
                hi--;
            }
            if (lo < hi){
                // 忽略大小写字母：都转换为小写字母来比较
                if (Character.toLowerCase(s.charAt(lo)) != Character.toLowerCase(s.charAt(hi))){
                    return false;
                }
                lo++;
                hi--;
            }
        }
        return true;
    }

    private boolean isCharOrNum(char c){
        if((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')
                || (c >= 'A' && c <= 'Z')){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        String s = "A man, a plan, a canal: Panama";
        Solution125 solution125 = new Solution125();
        boolean b = solution125.isPalindrome(s);
        int a = 3;
    }
}
