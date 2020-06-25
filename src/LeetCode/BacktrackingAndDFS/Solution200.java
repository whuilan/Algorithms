package LeetCode.BacktrackingAndDFS;

/**
 * 岛屿数量：给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。此外，你可以
 * 假设该网格的四条边均被水包围。
 * 思路：这题其实就是求矩阵中‘1’的连通分量有多少个，一看就是深度优先搜索，直接用剑指上12，13
 * 题的模板就好了，还有携程春招的最后一道编程题，就是这道题变一点！出现次数还不少，要牢记解法！
 * （本题还可以不用创建marked数组，遇到连通的‘1’时就把‘1’变成‘0’就好了）
 */
public class Solution200 {
    // 这类题一般最少都要设以下4个全局变量
    private int rows;
    private int cols;
    private static final int[][] nexts = {{-1,0},{1,0},{0,-1},{0,1}};
    private boolean[][] marked;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        rows = grid.length;
        cols = grid[0].length;
        marked = new boolean[rows][cols];
        int nums = 0;
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                // 如果是陆地并且没有被访问过（说明是一个新的连通分量），就将岛屿数加1
                if (grid[r][c] == '1' && !marked[r][c]){
                    nums++;
                    // 将与这个'1'连通的1进行标记
                    dfs(grid, r, c);
                }
            }
        }
        return nums;
    }

    private void dfs(char[][] grid, int r, int c){
        if (r < 0 || r >= rows || c < 0 || c >= cols
                  || grid[r][c] != '1' || marked[r][c]){
            return;
        }
        marked[r][c] = true;
        for (int[] next : nexts){
            dfs(grid, r + next[0], c + next[1]);
        }
    }
}
