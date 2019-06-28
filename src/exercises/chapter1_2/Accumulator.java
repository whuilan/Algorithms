package exercises.chapter1_2;

public class Accumulator {
    private double m;
    private double s;
    private int N;
    public void addDataValue(double x){
        N++;
        s = s + 1.0*(N-1)/N*(x-m)*(x-m);
        m = m + (x - m)/N;
    }
    public double mean(){
        return m;
    }
    public double var(){
        return s/(N-1);
    }
    public double stddev(){
        return Math.sqrt(this.var());
    }

    public static void main(String[] args){
        Accumulator accumulator = new Accumulator();
        int[] a = {1,2,3,4};
        for(int i=0;i<a.length;i++){
            accumulator.addDataValue(a[i]);
        }
        System.out.print(accumulator.stddev());
    }
}
