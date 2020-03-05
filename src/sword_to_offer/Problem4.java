package sword_to_offer;

/**
 * （有序）二维数组中的查找，核心：不断将待查找元素与二维矩阵的右上角元素比较
 */
public class Problem4 {
    public static boolean Find(int target, int [][] array) {
        if(array == null){
            return false;
        }
        int  rows = array.length, cols = array[0].length;
        if(rows == 0 || cols == 0){
            return false;
        }
        int m = 0,  n = cols - 1;
        while (m <= rows - 1 && n >= 0){    // m,n表示索引
            if(target < array[m][n]){
                n--;
            }
            else if(target > array[m][n]){
                m++;
            }
            else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        int[][] array = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int target = 7;
        boolean isFound = Find(target, array);
        System.out.println(isFound);
    }
}
