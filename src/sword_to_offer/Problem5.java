package sword_to_offer;

/**
 * P51替换空格
 */
public class Problem5 {
    public static String replaceSpace(StringBuffer str) {
        if(str == null){
            return null;
        }
        int P1 = str.length() - 1;
        for(int i = 0;i <= P1;i++){
            if(str.charAt(i) == ' '){
                str.append("  "); // 每遇到原字符串的一个空格就在末尾添加两个空格(or其他字符)
            }
        }
        int P2 = str.length() - 1;
        while(P1 >= 0 && P2 > P1){
            if(str.charAt(P1) == ' '){
                str.setCharAt(P2--, '0');
                str.setCharAt(P2--, '2');
                str.setCharAt(P2--, '%');
                P1--;
            }
            else {
                str.setCharAt(P2--, str.charAt(P1--));
            }
        }
        return str.toString();
    }

    public static void  main(String[] args){
        StringBuffer str = new StringBuffer("I  love ");
        String replacedStr = replaceSpace(str);
        System.out.println(replacedStr);
    }
}
