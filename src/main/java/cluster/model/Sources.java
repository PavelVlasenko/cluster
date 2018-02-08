package cluster.model;

import java.util.ArrayList;
import java.util.List;

public class Sources {

    private List<Node> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        return "Sources{" +
                "nodes=" + nodes +
                ", edges=" + edges +
                '}';
    }
}
