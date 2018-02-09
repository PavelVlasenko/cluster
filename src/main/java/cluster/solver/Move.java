package cluster.solver;

import cluster.model.Node;

public class Move {
    private Node node;
    private int fromCluster;
    private int toCluster;
    private double increase;

    public Move(Node node, int fromCluster, int toCluster, double increase) {
        this.node = node;
        this.fromCluster = fromCluster;
        this.toCluster = toCluster;
        this.increase = increase;
    }

    public double getIncrease() {
        return increase;
    }

    public Node getNode() {
        return node;
    }

    public int getFromCluster() {
        return fromCluster;
    }

    public int getToCluster() {
        return toCluster;
    }
}
