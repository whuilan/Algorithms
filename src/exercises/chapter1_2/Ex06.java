package exercises.chapter1_2;

public class Ex06 {
    public static boolean isCircularRotation(String s,String t){
        return s.length()==t.length()&&((t+t).indexOf(s)>=0);
    }

    public static boolean isCircularRotation2(String s,String t){
        if(s.length()!=t.length()) return false;
        for(int i=1;i<=s.length();i++){
            String left = s.substring(0,i);
            String right = s.substring(i,s.length());
            if((right+left).equals(t)){
                System.out.println("互为回环变位");
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        String s = "ACTGACG";
        String t = "ACGACTG";
        System.out.println(isCircularRotation2(s,t));
    }
}
