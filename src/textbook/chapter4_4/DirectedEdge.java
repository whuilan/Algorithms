package textbook.chapter4_4;

/**
 * 加权有向边的数据类型
 */
public class DirectedEdge {
    private int v;  // 边的起点
    private int w;  // 边的终点
    private double weight;  // 边的权重

    public DirectedEdge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    public int from(){
        return v;
    }
    public int to(){
        return w;
    }
    public double weight(){
        return weight;
    }
    public String toString(){
        return String.format("%d->%d:%.2f", v, w, weight);
    }
}
