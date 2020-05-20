package sword_to_offer;

/**
 * P179二叉搜索树的后序遍历序列
 */
public class Problem33 {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence == null || sequence.length == 0){
            return false;
        }
        return VerifySquenceOfBST(sequence, 0, sequence.length - 1);
    }

    private boolean VerifySquenceOfBST(int[] seq, int start, int end){
        if (end - start <= 1){  // 传进来的只有两个值或一个值时，一定可以构成一棵二叉搜索树
            return true;
        }
        int rootVal = seq[end];
        int findRightIdx = start;   // 记录寻找右子树的起始索引
        while (findRightIdx < end && seq[findRightIdx] < rootVal){
            findRightIdx++;
        }
        for (int j = findRightIdx; j < end; j++){
            if (seq[j] < rootVal){  // 右子树中出现了比根节点小的点，说明这不可能是二叉搜素树的序列
                return false;
            }
        }
        return VerifySquenceOfBST(seq, start, findRightIdx - 1)
                && VerifySquenceOfBST(seq, findRightIdx , end - 1);
    }

    public static void main(String[] args){
        int[] seq = {7,4,5,6};
        Problem33 problem33 = new Problem33();
        if (problem33.VerifySquenceOfBST(seq)){
            System.out.println("true");
        }else {
            System.out.println("false" );
        }
    }
}
