package cluster.solver;

import cluster.model.Node;
import cluster.model.Sources;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

    private int clusterNumber;
    private List<Node> nodes = new ArrayList<>();
    private Sources sources;

    public Cluster(Sources sources, int clusterNumber) {
        this.sources = sources;
        this.clusterNumber = clusterNumber;
    }

    public Sources getSources() {
        return sources;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }


    public int getClusterNumber() {
        return clusterNumber;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }


    /**
     * Return all nodes weight sum
     */
    public int getNodesWeight() {
        int weightSum = 0;
        for(Node node : nodes) {
            weightSum += node.getWeight();
        }
        return weightSum;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("Cluster " + clusterNumber + " = ");
        for(Node node : nodes) {
            sb.append(node.getNumber()).append(",");
        }
        sb.append(" Node weight sum = ").append(getNodesWeight());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cluster cluster = (Cluster) o;

        return clusterNumber == cluster.clusterNumber;

    }

    @Override
    public int hashCode() {
        return clusterNumber;
    }
}
