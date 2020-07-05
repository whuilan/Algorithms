package LeetCode.SearchAndSort;

/**
 * 搜索二维矩阵II:编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。
 * 该矩阵具有以下特性：
 * 1.每行的元素从左到右升序排列。
 * 2.每列的元素从上到下升序排列
 * 剑指第4题原题！这个题记得还比较清楚，选取右上角和目标值进行比较，每次都可以排除掉一行
 * 或者一列，这样不断缩小范围。
 */
public class Solution240 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int r = 0, c = n - 1; // 将第一个和目标值比较的元素设置为右上角的元素
        while (r < m && c >= 0){
            int topRight = matrix[r][c];
            if (topRight < target){ // 当前右上角所在那一整行的元素都会小于target
                r++;
            }else if (topRight > target){
                c--; // 当前右上角所在的列上的数都比target大
            }else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        int[][] matrix = {{1,4,7},{2,5,8},{3,6,9}};
        boolean b = searchMatrix(matrix, 3);
        System.out.println(b);
    }
}
