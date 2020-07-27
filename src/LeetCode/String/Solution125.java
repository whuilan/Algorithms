package LeetCode.String;

public class Solution125 {
    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0){
            return true;
        }
        int lo = 0, hi = s.length() - 1;
        while (lo < hi){
            while (lo < hi && !Character.isLetterOrDigit(s.charAt(lo))){
                lo++;
            }
            while (lo < hi && !Character.isLetterOrDigit(s.charAt(hi))){
                hi--;
            }
            if (lo < hi){
                if (Character.toUpperCase(s.charAt(lo)) != Character.toUpperCase(s.charAt(hi))){
                    return false;
                }
                lo++;
                hi--;
            }
        }
        return true;
    }

    private boolean isNumOrChar(char c){
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
