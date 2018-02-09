package cluster.model;

public class Node implements Comparable<Integer> {

    private int number;
    private int weight;

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Integer o) {
        Integer numberWrap = weight;
        return numberWrap.compareTo(o);
    }
}
