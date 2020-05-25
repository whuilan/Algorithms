package sword_to_offer;

import java.util.ArrayList;
import java.util.Stack;


/**
 * P58从尾到头打印链表，法二基于递归的代码有一个问题：当链表非常长时，就会导致函数调用的层级
 * 很深，从而有可能导致函数调用栈溢出，因此法一利用栈基于循环实现的代码鲁棒性更好！
 */
public class Problem6 {
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 法一：利用栈，后进先出的特点
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        for(ListNode x = listNode;x != null;x = x.next){
            stack.push(x.val);
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (!stack.empty()){
            arrayList.add(stack.pop());
        }
        return arrayList;
    }

    // 法二：利用递归思想，每访问一个节点时，先递归输出它后面的节点，再输出该节点自身
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        // 非法输入测试不要忘了！！！
        if(listNode == null){
            return arrayList;
        }
        reverse(listNode, arrayList);
        return arrayList;
    }

    public void reverse(ListNode listNode, ArrayList<Integer> arrayList){
        if(listNode == null){
             return;
        }
        reverse(listNode.next, arrayList);
        arrayList.add(listNode.val);
    }

    // 法二的简便写法
//    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        if(listNode != null){
//            arrayList.addAll(printListFromTailToHead(listNode.next));
//            arrayList.add(listNode.val);
//        }
//        return arrayList;
//    }

    public static void main(String[] args){
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = null;
        first.next = second;
        second.next = third;
        Problem6 printReverse = new Problem6();
        ArrayList<Integer> a = printReverse.printListFromTailToHead(first);
        for(Integer i : a){
            System.out.println(i);
        }
    }
}

