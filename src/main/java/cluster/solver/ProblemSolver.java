package cluster.solver;

import cluster.model.Edge;
import cluster.model.Node;
import cluster.model.Sources;
import cluster.settings.Parameters;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class contains the main logic of problem solving.
 * There are two parts: cluster initialization(seed nodes) and improving current solution with tabu search algorithm.
 */
public class ProblemSolver {
    private Sources sources;
    private List<Cluster> clusters = new ArrayList<>();
    private int counter = 0;
    private double currentEdgesSum = 0;
    private List<Move> candidateList = new ArrayList<>();
    private Map<Cluster, Set<Integer>> neighbors;
    private List<Move> tabuIterations = new ArrayList<>();

    public ProblemSolver(Sources sources) {
        this.sources = sources;
        for(int i = 0; i<Parameters.numberOfClusters; i++) {
            clusters.add(new Cluster(sources, i));
        }

    }

    /**
     * First initialization of the clusters.
     *
     */
    public void seedNodes() {
        //Sort nodes by weight
        LinkedList<Node> nodes = new LinkedList<>(sources.getNodes());
        nodes.sort((o1, o2) -> {
            Integer i1 = o1.getWeight();
            return i1.compareTo(o2.getWeight());
        });

        //Insert nodes in clusters
        for(int i =0;i < clusters.size(); i++) {
            Node n = nodes.poll();
            clusters.get(i).addNode(n);
        }

        //Then randomly insert neighbors to the cluster until all nodes will be inserted
        while(!nodes.isEmpty()) {
            Cluster cluster = clusters.get(counter);
            insertNode(cluster, nodes);
        }
        System.out.println("Node seed is finished");
    }

    private void insertNode(Cluster cluster, List<Node> nodes) {
        Set<Node> coupledNodes = coupledNodes(cluster);
        if(!coupledNodes.isEmpty()) {
            for(Node a : coupledNodes) {
                if(nodes.contains(a)) {
                    cluster.addNode(a);
                    nodes.remove(a);
                    break;
                }
            }
        }
        incrementCounter();
    }

    private Set<Node> coupledNodes(Cluster cluster) {
        Set<Integer> nodes = new HashSet<>();
        for(Node node : cluster.getNodes()) {
            nodes.addAll(sources.getNodeNumbersCoupledWithNode(node));
        }

        Set<Node> result = new HashSet<>();
        for(Integer i : nodes) {
            Node n = sources.getNode(i);
            if(!cluster.getNodes().contains(n)) {
                result.add(n);
            }
        }
        return result;
    }

    /**
     * This counter is needed for insert nodes.
     * It works by round-robin algorithm. After node is inserted to the last cluster, counter returns to the first cluster.
     */
    private void incrementCounter() {
        counter++;
        if(counter >= clusters.size()) {
            counter = 0;
        }
    }

    /**
     * Print to console info about cluster.
     * It contains clusters number with all nodes, node weight sum and edges distances.
     */
    public void printClusters() {
        StringBuilder sb = new StringBuilder("=========================\r\nCluster result: \r\n");
        int totalSize = 0;
        for(Cluster cluster : clusters) {
            sb.append(cluster.toString()).append("\r\n");
            totalSize += cluster.getNodes().size();
        }
        System.out.println("Total node size = " + totalSize);
        System.out.println(sb.toString());
        System.out.println("Total edge sum = " + calculateEdgesSum(clusters));
    }

    /**
     * Improve current solution with tabu search algorithm.
     * We can invoke this method several times.
     * Number of iterations we set from console.
     */
    public void improve() {
        currentEdgesSum = calculateEdgesSum(clusters);
        calculateNeighbors();

        calculateCandidateList();
        sortCandidateList();

        Move bestSolution = null;
        for(Move item : candidateList) {
            if(!isTabuMove(item)) {
                bestSolution = item;
                break;
            }
        }
        tabuIterations.add(bestSolution);
        doMove(bestSolution);
    }

    /**
     * Checks that move is already in tabu list.
     * Returns true if move in tabu, false otherwise.
     */
    private boolean isTabuMove(Move move) {
        for(Move tabuMove : tabuIterations) {
            if(tabuMove.getNode().getNumber() == move.getNode().getNumber()
                    && tabuMove.getToCluster() == move.getToCluster()
                    && tabuMove.getFromCluster() == move.getFromCluster()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sorts candidate list from higher increase to the lower.
     */
    private void sortCandidateList() {
        candidateList.sort((o1, o2) -> {
            Double d2 = o2.getIncrease();
            return d2.compareTo(o1.getIncrease());
        });
    }

    /**
     * Calculates all neighbors for cluster
     */
    private void calculateNeighbors() {
        neighbors = new HashMap<>();
        for(Cluster cluster : clusters) {
            Set<Integer> set = coupledNodes(cluster).stream().map(node -> node.getNumber()).collect(Collectors.toSet());
            neighbors.put(cluster, set);
        }
    }


    /**
     * Realize node moving from one cluster to another
     */
    private void doMove(Move move) {
        System.out.println("Move node " + move.getNode().getNumber() + " from cluster " + move.getFromCluster() +
        " to cluster " + move.getToCluster() + "\r\nIncrease = " + move.getIncrease());
        clusters.get(move.getFromCluster()).getNodes().remove(move.getNode());
        clusters.get(move.getToCluster()).addNode(move.getNode());

    }

    /**
     * Calculates all candidates for next iteration.
     * Then we need to choose the best from it
     */
    public void calculateCandidateList() {
        for(Cluster cluster : clusters) {
            for(Node node : cluster.getNodes()) {
                for(Map.Entry<Cluster, Set<Integer>> entry : neighbors.entrySet()) {
                    if(!cluster.equals(entry.getKey()) && entry.getValue().contains(node.getNumber())) {
                        Move move = calculateMove(node, cluster, entry.getKey());
                        candidateList.add(move);
                    }
                }
            }
        }
    }

    /**
     * Calculates increase for node moving
     */
    private Move calculateMove(Node node, Cluster fromCluster, Cluster toCluster) {
        List<Cluster> clustersCopy = copyCluster(this.clusters);
        if(!clustersCopy.get(fromCluster.getClusterNumber()).getNodes().remove(node)) {
            System.out.println("");
        }
        clustersCopy.get(toCluster.getClusterNumber()).addNode(node);

        int nodeWeight = clustersCopy.get(toCluster.getClusterNumber()).getNodesWeight();

        //If node weight sum out of range, create item with negative infinity increase
        if(nodeWeight > Parameters.clusterMaxLimit || nodeWeight < Parameters.clusterMinLimit) {
            return new Move(node, fromCluster.getClusterNumber(), toCluster.getClusterNumber(), -99999999.9);
        }
        double edgesSum = calculateEdgesSum(clustersCopy);
        double increase = edgesSum - currentEdgesSum;

        return new Move(node, fromCluster.getClusterNumber(), toCluster.getClusterNumber(), increase);
    }

    /**
     * Copy cluster. It's needed for calculating iteration without affect in current cluster
     */
    private List<Cluster> copyCluster(List<Cluster> clusters) {
        List<Cluster> newClusters = new ArrayList<>();
        for(Cluster cluster : clusters) {
            Cluster newCluster = new Cluster(cluster.getSources(), cluster.getClusterNumber());
            for (Node node : cluster.getNodes()) {
                Node newNode = new Node(node.getNumber(), node.getWeight());
                newCluster.addNode(newNode);
            }
            newClusters.add(newCluster);
        }
        return newClusters;
    }

    /**
     * Calculates edge sum
     */
    private double calculateEdgesSum(List<Cluster> clusters) {
        double edgeSum = 0;
        for(Cluster cluster : clusters) {
            List<Edge> edges = getClusterEdges(cluster);
            for(Edge edge : edges) {
                edgeSum += edge.getDistance();
            }
        }
        return edgeSum;
    }

    private List<Edge> getClusterEdges(Cluster cluster) {
        List<Integer> nodes = cluster.getNodes().stream().map(node -> node.getNumber()).collect(Collectors.toList());
        List<Edge> result = new ArrayList<>();
        for(Edge edge : sources.getEdges()) {
            if(nodes.contains(edge.getFirstNode()) && nodes.contains(edge.getSecondNode())) {
                result.add(edge);
            }
        }
        return result;
    }



}
