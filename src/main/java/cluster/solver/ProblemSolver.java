package cluster.solver;

import cluster.model.Edge;
import cluster.model.Node;
import cluster.model.Sources;
import cluster.settings.Parameters;

import java.util.*;

public class ProblemSolver {

    private Sources sources;
    private List<Cluster> clusters = new ArrayList<>();
    private int counter = 0;
    private double currentEdgesSum = 0;

    private List<Move> candidateList = new ArrayList<>();
    private Map<Cluster, Set<Integer>> neighbors;

    public ProblemSolver(Sources sources) {
        this.sources = sources;
        for(int i = 0; i<Parameters.numberOfClusters; i++) {
            clusters.add(new Cluster(sources, i));
        }

    }

    public void seedNodes() {
        System.out.print("Start seed nodes");
        LinkedList<Node> nodes = new LinkedList<>(sources.getNodes());
        nodes.sort((o1, o2) -> {
            Integer i1 = o1.getWeight();
            return i1.compareTo(o2.getWeight());
        });

        System.out.print("Preseed started");
        for(int i =0;i < clusters.size(); i++) {
            Node n = nodes.poll();
            System.out.println("Put node " + n.getNumber() + " to cluster " + i);
            clusters.get(i).addNode(n);
        }
        System.out.println("Preseed is finished");

        while(!nodes.isEmpty()) {
            Cluster cluster = clusters.get(counter);
            tryToPasteNode(cluster, nodes);
        }
        System.out.println("Node seed is finished");
    }

    private void tryToPasteNode(Cluster cluster, List<Node> nodes) {
        Set<Node> coupledNodes = coupledNodes(cluster);
        if(!coupledNodes.isEmpty()) {
            for(Node a : coupledNodes) {
                if(nodes.contains(a)) {
                    System.out.println("Paste node " + a.getNumber() + " to cluster number " + counter);
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



    private boolean isClusterCoupledWithNode(Cluster cluster, Node n) {
        for(Node clusterNode : cluster.getNodes()) {
            if(sources.getNodeNumbersCoupledWithNode(clusterNode).contains(n.getNumber())){
                System.out.println("Node " + n.getNumber() + " coupled with cluster " + cluster.getClusterNumber());
                return true;
            }
        }
        System.out.println("Node " + n.getNumber() + " NOT coupled with cluster " + cluster.getClusterNumber());
        return false;
    }

    private void incrementCounter() {
        counter++;
        if(counter >= clusters.size()) {
            counter = 0;
        }
    }

    public void printClusters() {
        StringBuffer sb = new StringBuffer("Cluster result: \r\n");
        int totalSize = 0;
        for(Cluster cluster : clusters) {
            sb.append(cluster.toString()).append("\r\n");
            totalSize += cluster.getNodes().size();
        }
        System.out.println(sb.toString());
        System.out.println("Total size = " + totalSize);
    }

    public void improve() {
        calculateCandidateList();
        candidateList.sort(new Comparator<Move>() {
            @Override
            public int compare(Move o1, Move o2) {
                Double d1 = o1.getIncrease();
                return o1.getIncrease();
            }
        });
    }

    public void calculateCandidateList() {
        for(Cluster cluster : clusters) {
            for(Node node : cluster.getNodes()) {
                for(Map.Entry<Cluster, Set<Integer>> entry : neighbors.entrySet()) {
                    if(!cluster.equals(entry.getKey()) || entry.getValue().contains(node.getNumber())) {
                        Move move = calculateMove(node, cluster, entry.getKey());
                        candidateList.add(move);
                    }
                }
            }
        }
    }

    private Move calculateMove(Node node, Cluster fromCluster, Cluster toCluster) {
        List<Cluster> clusters = copyCluster(this.clusters);
        clusters.get(fromCluster.getClusterNumber()).getNodes().remove(node);
        clusters.get(toCluster.getClusterNumber()).addNode(node);
        double edgesSum = calculateEdgesSum();
        double increase = edgesSum - currentEdgesSum;

        return new Move(node, fromCluster.getClusterNumber(), toCluster.getClusterNumber(), currentEdgesSum);
    }

    private List<Cluster> copyCluster(List<Cluster> clusters) {
        List<Cluster> clustersCopy = new ArrayList<>(clusters);
        for(int i = 0; i<clusters.size(); i++) {
            clustersCopy.get(i).setNodes(new ArrayList<>(clusters.get(i).getNodes()));
        }
        return clustersCopy;
    }

    private double calculateEdgesSum() {

    }


}
