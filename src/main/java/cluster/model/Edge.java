package cluster.model;

public class Edge {

    private int firstNode;
    private int secondNode;
    private double distance;

    public Edge(int firstNode, int secondNode, double distance) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.distance = distance;
    }

    public int getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(int firstNode) {
        this.firstNode = firstNode;
    }

    public int getSecondNode() {
        return secondNode;
    }

    public void setSecondNode(int secondNode) {
        this.secondNode = secondNode;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "firstNode=" + firstNode +
                ", secondNode=" + secondNode +
                ", distance=" + distance +
                '}';
    }
}
