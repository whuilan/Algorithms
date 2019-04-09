package textbook.chapter1_4.chapter1_4_2;

public class Stopwatch {
    private final long start;

    public Stopwatch(){
        start = System.currentTimeMillis();
    }

    public double elapsedTime(){
        long now = System.currentTimeMillis();
        return (now-start)/1000.0;
    }
}
