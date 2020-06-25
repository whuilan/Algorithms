package sword_to_offer;

/**
 * P89 回溯法，矩阵中的路径：判断在一个矩阵中是否存在一条包含某个字符串中所有字符的路径
 * 回溯是深度优先搜索的一种特例，它在一次搜索过程中需要记录一些本次搜索过程的局部状态，
 * 并在本次搜索结束之后清除状态。而普通的深度优先搜索(如Problem13)并不需要使用这些局部状态，
 * 虽然还是有可能设置一些全局状态。
 */
public class Problem12 {
    private static final int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private int rows;
    private int cols;

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        if(matrix == null || rows < 1 || cols < 1 || str == null){
            return false;
        }
        this.rows = rows;
        this.cols = cols;

        char[][] twoDMatrix = new char[rows][cols];  // 将输入的一维数组转换成二维矩阵
        int index = 0; // 一维数组起始索引
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                twoDMatrix[r][c] = matrix[index++];
            }
        }

        boolean[][] marked = new boolean[rows][cols];  // 记录每个格子是否在路径上

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (backtracking(twoDMatrix, str, marked, 0, i, j))
                    return true;

        return false;
    }

    private boolean backtracking(char[][] matrix, char[] str, boolean[][] marked,
                                 int pathLen, int r, int c ){
        if(pathLen == str.length){
            return true;
        }
        if(r < 0 || r >= rows || c < 0 || c >= cols ||
                matrix[r][c] != str[pathLen] || marked[r][c]){
            return false;
        }
        marked[r][c] = true;
        for(int[] n : next){
            if(backtracking(matrix, str, marked, pathLen + 1, r + n[0], c + n[1])){
                return true;
            }
        }
        marked[r][c] = false;
        return false;
    }

    public static void main(String[] args){
//        char[] charArray = {'a', 'b', 't', 'g', 'c', 'f', 'c', 's', 'j', 'd', 'e','h'};
//        char[] str = {'b', 'f', 'c', 'e'};
        char[] charArray = {'a', 'b', 'c', 'e', 's', 'f', 'c', 's', 'a', 'd', 'e', 'e'};
        char[] str = {'s', 'e', 'e'};
        int rows = 3, cols = 4;
        Problem12 problem12 = new Problem12();
        boolean hasPath = problem12.hasPath(charArray, rows, cols, str);
        System.out.println(hasPath);
    }
}
