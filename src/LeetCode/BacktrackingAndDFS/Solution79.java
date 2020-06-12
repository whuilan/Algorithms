package LeetCode.BacktrackingAndDFS;

/**
 * 单词搜索：给定一个二维网格和一个单词，找出该单词是否存在于网格中。其实就是剑指第12题：矩阵中的路径
 */
public class Solution79 {
    private int rows;
    private int cols;
    private boolean[][] marked;
    private int[][] steps = {{-1,0},{1,0},{0,-1},{0,1}};

    public boolean exist(char[][] board, String word) {
        rows = board.length;
        cols = board[0].length;
        marked = new boolean[rows][cols];
        // 网格中每个点都有可能与word的第一个字符相等，因此以每个点为起点和word的第一个字符比较
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                if (backtracking(board, r, c, word,0)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtracking(char[][] board, int r, int c, String word, int idx){
        // word中所有字符都走完，说明当前路径满足要求
        if (idx == word.length()){
            return true;
        }
        // 网格r，c超出界限，或者该网格已经被访问过，或者该网格处的值和word的第idx个字符不相等
        // 说明当前网格不符合条件
        if (r < 0 || r >= rows || c < 0 || c >= cols
                || marked[r][c] || board[r][c] != word.charAt(idx)){
            return false;
        }
        marked[r][c] = true;
        // 走每一步都有上下左右四个响铃的选择
        for (int[] next : steps){
            if (backtracking(board, r + next[0], c + next[1], word, idx + 1)){
                return true;
            }
        }
        marked[r][c] = false; // 这一步忘了！
        return false;
    }

    public static void main(String[] args){
        char[][] board = {{'a','b','t','g'},{'c','f','c','s'},{'j','d','e','h'}};
        String word = "bfce";
        Solution79 solution79 = new Solution79();
        boolean exists = solution79.exist(board, word);
        System.out.println(exists);
    }
}
