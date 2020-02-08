package sword_to_offer;

/**
 * P78 变态跳台阶问题
 */
public class Problem10_3 {
    public int JumpFloorII(int target) {
        if(target <= 0){
            return 0;
        }
        return (int) Math.pow(2, target - 1);
    }
}
