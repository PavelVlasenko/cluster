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

    private int getClusterWeight() {
        return 0;
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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("Cluster nodes=");
        for(Node node : nodes) {
            sb.append(node.getNumber()).append(",");
        }
        return sb.toString();
    }
}
