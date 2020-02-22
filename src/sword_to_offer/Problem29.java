package sword_to_offer;

import java.util.ArrayList;
/**
 * P161顺时针打印矩阵
 */
public class Problem29 {
    // 思路没错，但是写起来太复杂了，特别是在printMatrixClockwise中分情况时极容易出错
    public ArrayList<Integer> printMatrix0(int [][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return null;
        }
        int rows = matrix.length, cols = matrix[0].length;
        ArrayList<Integer> clockwiseMatrix = new ArrayList<>();
        int start = 0; // 起始顶点索引
        // 注意循环条件不能写成start<rows/2 && start<cols/2,不然当矩阵行数/列数为奇数时，正中间那个值就无法遍历到
        while (rows > start * 2 && cols > start * 2){
            printMatrixClockwise(matrix, start, rows, cols, clockwiseMatrix);
            start++;
        }
        return clockwiseMatrix;
    }

    private void printMatrixClockwise(int[][] matrix, int start, int rows,
                                      int cols, ArrayList<Integer> arrayList){
        // 打印最上面一行
        for (int i = start; i <= cols - start - 1; i++){
            arrayList.add(matrix[start][i]);
        }
        // 打印最右边一列
        for (int j = start + 1; j <= rows - start - 1; j++){
            arrayList.add(matrix[j][cols - start - 1]);
        }
        // 打印最下面一行，注意这种情况需要添加判断
        if (rows - start - 1 > start){
            for (int k = cols - start - 2; k >= start; k--){
                arrayList.add(matrix[rows - start - 1][k]);
            }
        }
        // 打印最左边一列，也要添加判断
        if (cols - start - 1 > start){
            for (int l = rows - start - 2; l > start; l--){
                arrayList.add(matrix[l][start]);
            }
        }
    }

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return null;
        }
        ArrayList<Integer> clockWiseMatrix = new ArrayList<>();
        int rowHead = 0, rowTail = matrix.length - 1,
            colHead = 0, colTail = matrix[0].length -  1;
        while (rowHead <= rowTail && colHead <= colTail){
            // 上面一行
            for (int c = colHead; c <= colTail; c++){
                clockWiseMatrix.add(matrix[rowHead][c]);
            }
            // 右边一行
            for (int r = rowHead + 1; r <= rowTail; r++){
                clockWiseMatrix.add(matrix[r][colTail]);
            }
            // 下面一行，注意这是横排第二次遍历，需要确保与第一次遍历的横排不同
            if (rowHead < rowTail){
                for (int c = colTail - 1; c >= colHead; c--){
                    clockWiseMatrix.add(matrix[rowTail][c]);
                }
            }
            // 左边一列，同样这是竖排第二次遍历，需要确保与第一次遍历的竖排不同
            if (colHead < colTail){
                for (int r = rowTail - 1; r > rowHead; r--){
                    clockWiseMatrix.add(matrix[r][colHead]);
                }
            }
            rowHead++;
            rowTail--;
            colHead++;
            colTail--;
        }
        return clockWiseMatrix;
    }

    public static void main(String[] args){
        int[][] matrix = {};
        Problem29 problem29 = new Problem29();
        ArrayList<Integer> result = problem29.printMatrix(matrix);
//        for (int n : result){
//            System.out.print(n + " ");
//        }
        System.out.println();
    }
}
