package sword_to_offer;

/**
 * P312构建乘积数组（要求不能使用除法）
 * 时间复杂度为O(n)（直观的双层循环暴力解法时间复杂度为O(n^2)）
 */
public class Problem66 {
    public static int[] multiply(int[] A) {
        if(A == null || A.length < 2){
            return new int[0];
        }
        int N = A.length;
        int[] B = new int[N];
        int productOfLeft = 1, productOfRight = 1;
        // 从左往右累乘
        B[0] = 1;
        for (int i = 1; i < N; i++){
            productOfLeft *= A[i-1];
            B[i] = productOfLeft;
        }
        for (int j = N - 2; j >= 0; j--){
            productOfRight *= A[j+1];
            B[j] *= productOfRight;
        }
        return B;
    }

    public static void main(String[] args){
        int[] A = {1,2,3,4};
        int[] B = multiply(A);
        for (int b : B){
            System.out.print(b + " ");
        }
    }
}
