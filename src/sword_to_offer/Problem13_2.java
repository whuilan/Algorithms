package sword_to_offer;

import java.util.Scanner;

/**
 * 携程春招笔试第三道编程题：二维矩阵（每个元素的值为0或1）中连通的1的最长长度
 * 即相互连通的1的格子数最多是多少？
 */
public class Problem13_2 {
    private static int[][] moves = {{-1,0},{1,0},{0,-1},{0,1}};

    private static void dfs(int[][] matrix, int i, int j, boolean[][] marked, int[] area){
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length ||
                matrix[i][j] == 0 || marked[i][j]){
            return;
        }
        marked[i][j] = true;
        area[0]++;
        for(int[] move : moves){
            dfs(matrix, i + move[0], j + move[1], marked, area);
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt(), c = sc.nextInt();
        int[][] matrix = new int[r][c];
        boolean[][] marked = new boolean[r][c];
        for (int i = 0;i < r;i++){
            for (int j = 0;j < c;j++){
                matrix[i][j] = sc.nextInt();
            }
        }
        int max = 0;
        for (int i = 0;i < r;i++){
            for (int j = 0;j < c;j++){
                if (matrix[i][j] == 1 && !marked[i][j]){
                    int[] area = new int[1];
                    dfs(matrix, i, j, marked,area);
                    if (area[0] > max){
                        max = area[0];
                    }
                }
            }
        }
        System.out.println(max);
    }
}
