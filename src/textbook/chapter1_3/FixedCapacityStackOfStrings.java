package textbook.chapter1_3;

public class FixedCapacityStackOfStrings {
    private String[] a;
    private int N;
    public FixedCapacityStackOfStrings(int cap){
        a = new String[cap];
    }
    public boolean isEmpty(){ return N==0;}
    public int size(){ return N;}
    public void push(String item){
        a[N++]= item;
    }
    public String pop(){
        return a[--N];
    }

    /*练习1.3.1 为该类添加一个isFull()方法*/
    public boolean isFull(){ return N == a.length;}
}
