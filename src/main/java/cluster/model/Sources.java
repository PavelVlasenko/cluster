package cluster.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class contains all nodes and edges
 */
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

    public Set<Integer> getNodeNumbersCoupledWithNode(Node node) {
        Set<Integer> result = new HashSet<>();
        List<Edge> edges = getEdgesForNode(node);
        for(Edge edge : edges) {
            result.add(edge.getFirstNode());
            result.add(edge.getSecondNode());
        }
        return result;
    }

    public List<Edge> getEdgesForNode(Node node) {
        int nodeNumber = node.getNumber();
        List<Edge> result = new ArrayList<>();
        for(Edge edge : edges) {
            if(edge.getFirstNode() == nodeNumber || edge.getSecondNode() == nodeNumber) {
                result.add(edge);
            }
        }
        return result;
    }

    public Node getNode(int number) {
        for(Node n : nodes) {
            if (n.getNumber() == number) {
                return n;
            }
        }
        System.out.println("ERROR while getting node by number, node " + number + " not found");
        return null;
    }

    @Override
    public String toString() {
        return "Sources{" +
                "nodes=" + nodes +
                ", edges=" + edges +
                '}';
    }
}
