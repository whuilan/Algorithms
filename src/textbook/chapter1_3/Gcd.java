package textbook.chapter1_3;

/**
 * 欧几里得算法：计算两个非负整数p,q的最大公约数
 * 若q为0，则最大公约数为p。否则将p除以q得到余数r，p和q的最大公约数
 * 即为q和r的最大公约数（又称为辗转相除法）
 * */
public class Gcd {
    public static int gcd(int p, int q){
        if (q == 0){
            return p;
        }
        int r = p % q;
        return gcd(q, r);
    }

    public static void main(String[] args){
        int p = 13, q = 15;
        int g = gcd(p, q);
        System.out.println(g);
    }
}
