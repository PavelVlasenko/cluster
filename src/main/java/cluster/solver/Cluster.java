package cluster.solver;

import cluster.model.Node;
import cluster.model.Sources;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

    private List<Node> nodes = new ArrayList<>();
    private Sources sources;

    public Cluster(Sources sources) {
        this.sources = sources;
    }

    private int getClusterWeight() {
        return 0;
    }

    private void addNode(Node node) {
        nodes.add(node);
    }

}
