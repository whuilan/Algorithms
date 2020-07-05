package LeetCode.SearchAndSort;

/**
 * 搜索二维矩阵：编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 1.行中的整数从左到右按升序排列。
 * 2.每行的第一个整数大于前一行的最后一个整数。
 * 分析：第一反应是先确定目标值在哪一行，然后可以遍历那一行或者用二分查找在那一行找目标值
 *      思路是对的，但是运行结果超时，这样做的时间复杂度是线性的。题目提示写一个高效的算法。
 *      其实根据该矩阵的特性，把这个矩阵降为一维数组的话，就是个递增的一维数组啊，可以整体用
 *      二分查找的！难点在于将二维矩阵的行数r、列数c和一维数组的索引i对应起来，二者的对应关系为：
 *      r = i / n, c = i % n
 *      这是个标准的二分查找，时间复杂度为O(log(mn))，空间复杂度为O(1)
 */
public class Solution74 {
    // 第一反应，运行时间超时
    public static boolean searchMatrix0(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int r = 0; // target所在行数
        while (r < m){
            if (target <= matrix[r][n-1]){
                break;
            }
        }
        if (r == m){
            return false;
        }
        for (int j = 0; j < n; j++){
            if (target == matrix[r][j]){
                return true;
            }
        }
        return false;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        // 像一维数组一样的去使用
        int lo = 0, hi = m * n - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            // 取出中间索引之后再把索引映射到二维数组的索引r,c
            int midValue = matrix[mid / n][mid % n];
            if (midValue < target){
                lo = mid + 1;
            }
            else if(midValue > target){
                hi = mid - 1;
            }
            else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        int[][] matrix = {{1, 3, 5, 7},{10, 11, 16, 20},{23, 30, 34, 50}};
        boolean b = searchMatrix(matrix, 3);
        System.out.println(b);
    }
}
