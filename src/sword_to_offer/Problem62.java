package sword_to_offer;

/**
 * P300圆圈中最后剩下的数字(约瑟夫(Josephuse)环问题)
 */
public class Problem62 {
    private int N;

    // 法一（自己写出来的！）：用环形链表模拟圆圈，每删除一个数字需要m-1步运算，一共要删除n-1个
    // 数字，因此时间复杂度为O(mn)，同时还需要一个辅助链表来模拟圆圈，因此空间复杂度为O(n)
    public int LastRemaining_Solution1(int n, int m) {
        if (n < 1 || m < 1){
            return -1;
        }
        this.N = n;
        // 构建从0到n-1的单向链表
        ListNode first = buildList(0);
        // 第一次遍历：将单向链表首尾连接成环形链表
        ListNode node = first;
        while (node.next != null){
            node = node.next;
        }
        node.next = first;
        int count = 0;
        // 第二次遍历：不断删除第m个数字
        ListNode x = first;
        while (N > 1){
            count++;
            if (count == m - 1){ // 到达要删除节点的上一个节点
                x.next = x.next.next;
                N--;
                count = 0;
            }
            x = x.next;
        }
        return x.val;
    }

    private ListNode buildList(int val){
        if (val == N){
            return null;
        }
        ListNode node = new ListNode(val);
        node.next = buildList(val+1);
        return node;
    }

    // 法二：数学分析法，分析被删除的数字的规律，得到相关的规律，设f(n,m)表示在n个数字
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
        int v = problem62.LastRemaining_Solution2(5, 3);
        System.out.println(v);
    }
}
