package whuilan.chapter1_1;

public class EX15 {
    public static int[] histogram(int[] a,int M){
        int[] b = new int[M];
        for(int i=0;i<M;i++){
            int count = 0;
            for (int j=0;j < a.length;j++){
                if(i == a[j]) {count++;}
            }
            b[i] = count;
        }
        return b;
    }
}

    /*测试用例*/
//    int[] arr = {3,3,1,0};
//    int[] newArr = EX15.histogram(arr,4);
//    int sum = 0;
//        for(int i=0;i < newArr.length;i++){
//        System.out.print(newArr[i]+" ");
//        sum += newArr[i];
//        }
//        System.out.print(sum == arr.length);