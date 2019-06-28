package exercises.chapter1_1;

public class Ex20 {
     public static double ln(int N){
         if(N==0) return 0;
         if(N==1) return 0;
         return Math.log(N) +ln(N-1);
     }

     public static void main(String[] args){
         double res = ln(1);
         System.out.print(res);
     }
}
