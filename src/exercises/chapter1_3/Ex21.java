package exercises.chapter1_3;

public class Ex21 {
    private static class Node{
        String str;
        Node next;
    }
    public static boolean find(Node first,String key){
        if(first == null) {return false;}
//        Node current = first;
//        //boolean isExisted = false;
//        while(current!=null){
//            if(current.str == key){
//                //isExisted = true;
//                return true;
//            }
//            current = current.next;
//        }
        for(Node current = first;current!=null;current = current.next){
            if (current.str == key){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        Node first = new Node();
        first.str = "apple";
        Node second = new Node();
        second.str = "banana";
        first.next = second;
        Node third = new Node();
        third.str = "orange";
        second.next = third;
        Node forth = new Node();
        forth.str = "pear";
        forth.next = null;
        third.next = forth;
        boolean result = find(first,"pear");
        System.out.print(result);
    }
}
