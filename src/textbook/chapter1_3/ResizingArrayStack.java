package textbook.chapter1_3;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void resize(int newLength) {
        Item[] temp = (Item[]) new Object[newLength];
        for (int i = 0; i < N; i++) { // 注意这里的循环条件是i<N,即将栈中的元素移到一个长度变化的数组中存储，而不是i<newLength(扩大时a[i]会超出范围)或者i<a.lenth(缩小时temp[i]会超出范围)
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(Item item) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    public Item pop() {
        Item t = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4) resize(a.length / 2);
        return t;
    }

    public  int arraySize(){
        return a.length;
    }

    public Iterator<Item> iterator() {
        return new RerseArrayIterator();
    }

    private class RerseArrayIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return a[--i];
        }

        public void remove() {
        }
    }

    public static ResizingArrayStack<String> copy(ResizingArrayStack<String> inputStack){
        ResizingArrayStack<String> outputStack = new ResizingArrayStack<>();
        outputStack = inputStack;
        return outputStack;
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!s.equals("-")) {
                stack.push(s);
            } else if (!stack.isEmpty()) {
                // StdOut.print(stack.pop() + " ");
                stack.pop();
            }
        }
        for(String l:stack){
            StdOut.print(l+"");
        }
        StdOut.println();
        StdOut.println("数组大小为："+stack.arraySize());
        StdOut.println("栈大小为："+stack.size());
        //StdOut.println("(" + stack.size() + " left on the stack)");
    }
}
