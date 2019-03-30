package whuilan.chapter1_1;

public class EX13 {
        public static void reverseArr(int[][] a){
            int M = a.length;
            int N = a[0].length;
            int[][] b = new int[N][M];
            for(int i=0;i<M;i++){
                for(int j=0;j<N;j++){
                    b[j][i] = a[i][j];
                }
            }
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    System.out.print(b[i][j]+" ");
                }
                System.out.println();
            }
        }

        public static void main(String[] args){
            int[][] arr = {{1,2,3},{4,5,6}};
            for(int i=0;i<2;i++){
                for(int j=0;j<3;j++){
                    System.out.print(arr[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
            reverseArr(arr);
        }
}
