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
        a[N]= item;
        N++;
    }
    public String pop(){
        --N;
        return a[N];
    }
}
