package sword_to_offer;

/**
 * P79 矩形覆盖
 */
public class Problem10_4 {
    public int RectCover(int target) {
        if(target <= 2){
            return target;
        }
        int preTwo = 1, preOne = 2;
        int result = 0;
        for(int i = 3; i <= target; i++){
            result = preOne + preTwo;
            preTwo = preOne;
            preOne = result;
        }
        return result;
    }
}
