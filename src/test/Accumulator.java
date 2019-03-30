package test;

public class Accumulator {
    private int N;
    private  double total;

    public void addDataValue(double val){
        N++;
        total+=val;
    }

    public double mean(){
        return total/N;
    }

    public String toString(){
        return "Mean ("+N+" values): "+String.format("%7.5f",mean());
    }
}
