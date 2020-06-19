package LeetCode.StackAndQueue;


import java.util.Stack;

/**
 * 有效的括号(simple)
 * 核心：遍历，将左括号压入栈，遇到右括号再比较！
 */
public class Solution20 {
    // 自己写的
    public static boolean isValid(String s) {
        if(s == null || (s.length() % 2 != 0)){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '['){
                // 遇到左括号则压入栈中
                stack.push(c);
            }
            else{
                if (!stack.empty()){
                    char top = stack.pop();
                    if ((c == ')' && top == '(') || (c == '}' && top == '{') ||
                            (c == ']' && top == '[')){
                        continue;
                    }
                }
                return false;
            }
        }
        return stack.isEmpty();
    }

    // 优化写法，上述中判断左右括号匹配有点繁杂，其实可以在遇到左括号时将对应的右括号压入栈！
    public static boolean isValid_simplified(String s) {
        if (s == null || s.length() % 2 != 0){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '('){
                stack.push(')');
            }
            else if(c == '{'){
                stack.push('}');
            }
            else if (c == '['){
                stack.push(']');
            }
            else {
                if (stack.isEmpty() || c != stack.pop()){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args){
        String s = "([)]";
        boolean valid = isValid_simplified(s);
        System.out.println(valid);
    }
}
