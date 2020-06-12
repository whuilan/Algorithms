package sword_to_offer;

/**
 * P92 回溯法：机器人的运动范围
 * 难点：r、c不知道各是几位数，又怎样去提取它的个位、十位、百位来计算各位数之和
 * 使用深度优先搜索（Depth First Search，BacktrackingAndDFS）方法进行求解。
 */
public class Problem13 {
    private static final int[][] next = {{0, -1}, {0, 1}, {-1,0}, {1, 0}};
    private int rows;
    private int cols;
    private boolean[][] marked;
    private int count;

    public int movingCount(int threshold, int rows, int cols)
    {
        if(threshold < 0 || rows < 1 || cols < 1){
            return 0;
        }
        this.rows = rows;
        this.cols = cols;
        marked = new boolean[rows][cols];
        dfs(0, 0, threshold);
        return count;
    }

    private void dfs(int r, int c,int threshold){
        if( r < 0 || r >= rows || c < 0 || c >= cols
                  || getDigitSum(r) + getDigitSum(c) > threshold || marked[r][c]){
            return;
        }
        marked[r][c] = true;
        count++;
        for(int[] n : next){
            dfs(r + n[0], c + n[1], threshold);
        }
    }

    private int getDigitSum(int num){
        int sum = 0;
        while (num > 0){
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args){
        int threshold = 2, rows = 2, cols = 3;
        Problem13 problem13 = new Problem13();
        // 测试单独一个整数类型传递给函数进行处理后，该整数本身的值是否会发生变化，
        // 结果证明不会，函数里是另外的变量
//        int n = 35;
//        int ds = problem13.getDigitSum(n);
//        System.out.println("n:" + n + " ds:" + ds);
        int moves = problem13.movingCount(threshold, rows, cols);
        System.out.println(moves);
    }
}
