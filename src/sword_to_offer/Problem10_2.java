package sword_to_offer;

/**
 * P77 青蛙跳台阶问题
 */
public class Problem10_2 {
    public int JumpFloor(int target) {
        if (target <= 2) {
            return target;
        }
        int preTwo = 1, preOne = 2;
        int num = 0;
        for (int i = 3; i <= target; i++) {
            num = preOne + preTwo;
            preTwo = preOne;
            preOne = num;
        }
        return num;
    }
}
