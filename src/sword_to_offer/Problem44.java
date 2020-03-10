package sword_to_offer;

/**
 * P225数字序列中某一位的数字
 */
public class Problem44 {
    public int digitAtIndex(int index){
        if (index < 0){
            return -1;
        }
        int digit = 1; // digit表示数字的位数，如1位数字（0-9），两位数字（10-99）
        while (true){
            int nums = countNumsOfDigit(digit);
            if (index < nums * digit){
                return digitAtIndex(index, digit);
            }
            index -= nums * digit;
            digit++;
        }
    }

    // 统计digit位数字一个有多少个，如1位的数字0-9一共有10个，2位的10-99一共90个，3位的100-999一共900个
    private int countNumsOfDigit(int digit){
        if (digit == 1){
            return 10;
        }
        return 9 * ( (int) Math.pow(10, digit - 1));
    }

    // 在digit位数字（组成的字符串）中，第index个数
    private int digitAtIndex(int index, int digit){
        int beginNum = getBeginNumOfDigit(digit);
        int shiftNum = index / digit;
        String number = beginNum + shiftNum + "";
        int count = index % digit;
        return number.charAt(count) - '0';
    }

    // digit位数字的第一个数字，即0、10、100...
    private int getBeginNumOfDigit(int digit){
        if (digit == 1){
            return 0;
        }
        return (int) Math.pow(10, digit - 1);
    }

    public static void main(String[] args){
        Problem44 problem44 = new Problem44();
        int n = problem44.digitAtIndex(15);
        System.out.println(n);
    }
}
