package cluster.settings;

public class Parameters {

    /**
     * Type of cluster(one of the folder in archive)
     */
    public static ClusterType clusterType;

    /**
     * File name in archive
     */
    public static  String fileName;

    /**
     * Number of clusters
     */
    public static int numberOfClusters;

    /**
     * Max nodes weight sum
     */
    public static int clusterMaxLimit;

    /**
     * Min nodes weight sum
     */
    public static int clusterMinLimit;

    /**
     * Number of iteration for tabu search
     */
    public static int iterations;
}
