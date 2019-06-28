package exercises.chapter1_4;

/**
 * 1.4.2 修改TreeSum,正确处理两个较大的int值相加可能溢出的情况
 * 解题思路：两个异号的int类型数字相加结果一定不会溢出，如果两个同号的int类型数字相加，可能出现溢出情况。
 * a+b+c的计算顺序为(a+b)+c，所以对于ThreeSum，只需要判断a+b是否有溢出（溢出结果不可能等于0），若溢出则a+b+c≠0。
 * 总的来说就是用大范围的long接受小范围的int的值
 **/
public class Ex02 {
    public static int count(int[] a){
        int N = a.length;
        int cnt = 0;
        long temp1;
        long temp2;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                for(int k=0;k<N;k++){
                    temp1 = a[i];
                    temp2 = a[j];
                    if(temp1+temp2+a[k]==0){
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}
