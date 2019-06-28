package exercises.chapter1_3;

/**
 *编写一个方法remove()，接受一个链表和一个字符串key作为参数，
 * 删除链表中所有item域为key的结点。
 **/
public class Ex26 {
    private static class Node{
        String item;
        Node next;
    }
    public static Node remove(Node first,String key){
        Node newFirst = new Node();//在顶部新增一个首结点，解决如果原来的首节点就等于item的情况
        newFirst.next = first;

        Node current = newFirst;
        while(current.next!=null){
            if(current.next.item.equals(key)){
                current.next = current.next.next;
            }else { //注意这里要有else，不然如果有连续的结点item=key,就会跳过第二个相同的结点
                current = current.next;
            }
            }
        return newFirst.next;
    }
    public static void main(String[] args){
        Node first = new Node();
        first.item = "apple";
        Node second = new Node();
        second.item = "banana";
        first.next = second;
        Node third = new Node();
        third.item = "banana";
        second.next = third;
        Node fourth = new Node();
        fourth.item = "orange";
        fourth.next= null;
        third.next = fourth;
        for(Node cur = first;cur!=null;cur=cur.next){
            System.out.print(cur.item+" ");
        }
        System.out.println();
        Node returnedFirst = remove(first,"apple");
        for(Node cur = returnedFirst;cur!=null;cur=cur.next){
            System.out.print(cur.item+" ");
        }
    }
}
