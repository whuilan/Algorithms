package whuilan.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ResizingArrayQueueOfStrings<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;
    private int head = 0;
    private int tail = 0;

    public boolean isEmpty(){ return N == 0; }

    public int size(){ return N;}

    public void resize(int newLength){
        Item[] temp = (Item[]) new Object[newLength];
        for(int i = head;i < tail;i++){
            temp[i-head] = a[i];
        }
        a = temp;
        head = 0;
    }

    public void enqueue(Item item){
        if(N == a.length){
            resize(2*a.length);
        }
        a[tail++] = item;
        N++;
    }

    public Item dequeue(){
        Item item = a[head];
        a[head] = null;
        head++;
        N--;
        if(N != 0 && N == a.length/4) { resize(a.length/2); }
        return item;
    }
    public Iterator<Item> iterator(){ return new ReverseArrayIterator();};

    public class ReverseArrayIterator implements Iterator<Item>{
        private int i = N;
        public boolean hasNext(){return tail>head;}
        public Item next(){ return a[head++];}
        public void remove(){}
    }

    public static void main(String[] args){
        ResizingArrayQueueOfStrings<Integer> queue = new ResizingArrayQueueOfStrings<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
//        StdOut.print(queue.dequeue());
//        StdOut.print(queue.dequeue());
//        StdOut.print(queue.dequeue());
        for(int q:queue){
            StdOut.print(q+" ");
        }
    }

}
