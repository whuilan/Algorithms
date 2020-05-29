package sword_to_offer;

import java.util.ArrayList;

/**
 * P300圆圈中最后剩下的数字(约瑟夫(Josephuse)环问题)
 */
public class Problem62 {

    // 法一（自己写出来的！）：用环形链表模拟圆圈，每删除一个数字需要m-1步运算，一共要删除n-1个
    // 数字，因此时间复杂度为O(mn)，同时还需要一个辅助链表来模拟圆圈，因此空间复杂度为O(n)
    // 但是再leetcode上给出范围：1 <= n <= 10^5，1 <= m <= 10^6时运行会超时
    private ListNode initialTail;
    private int N;

    public int LastRemaining_Solution(int n, int m) {
        if (n < 1 || m < 1){
            return -1;
        }
        this.N = n;
        // 创建环形链表
        ListNode start = constructList(0);
        // 忽略了就删除一个节点的情况！
        if (m == 1){
            return initialTail.val;
        }
        initialTail.next = start;
        ListNode x = start;
        while (N > 1){
            for (int i = 1; i <= m - 2;i++){ // 找到第m-1个节点
                x = x.next;
            }
            x.next = x.next.next; // 删除第m个节点
            N--;
            x = x.next;
        }
        return x.val;
    }

    private ListNode constructList(int seq){
        if (seq == N){
            return null;
        }
        ListNode node = new ListNode(seq);
        if (seq == N - 1){
            initialTail = node; // 记录下最后一个节点
        }
        node.next = constructList(seq+1);
        return node;
    }

    // 法二：当m,n很大时，用法一中单纯的链表会超时，因此改用LinkedList，也会发现超时
    // 然后又改用ArrayList，勉强可以通过，因为ArrayList 的 remove 操作在后续移位的时候，
    // 其实是内存连续空间的拷贝的！所以相比于LinkedList大量非连续性地址访问，ArrayList的性能是很OK的
    public int lastRemaining(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }

    // 法三：数学分析法，分析被删除的数字的规律，得到相关的规律，设f(n,m)表示在n个数字
    // 0,1,...,n-1中不断删除第m个数字最后剩下的数字，经过推到可得：
    // f(n,m)=0, n=1
    // f(n,m)=[f(n-1,m)+m]%n, n>1
    // 则基于循环可写出以下代码，时间复杂度为O(n)，空间复杂度为O(1)
    public int LastRemaining_Solution2(int n, int m) {
        if (n < 1 || m < 1){
            return -1;
        }
        int last = 0;
        for (int i = 2; i <=n; i++){
            last = (last + m) % i;
        }
        return last;
    }

    public static void main(String[] args){
        Problem62 problem62 = new Problem62();
        int v = problem62.LastRemaining_Solution(5, 3);
        System.out.println(v);
    }
}
