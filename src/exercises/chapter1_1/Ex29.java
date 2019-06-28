package exercises.chapter1_1;

public class Ex29 {
    public static int rank(int key,int[] a){
        int N = 0;
        for(int i=0;i<a.length;i++){
            if (a[i]<key) {
                N++;
            }
        }
        return N;
    }

    public static int count(int key,int[] a){
        int N = 0;
        for(int i=0;i<a.length;i++){
            if (a[i]==key) {
                N++;
            }
        }
        return N;
    }

}
