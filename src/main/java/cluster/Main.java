package cluster;

import cluster.model.Sources;
import cluster.parsing.FileParser;
import cluster.settings.ClusterType;
import cluster.settings.Parameters;
import cluster.solver.ProblemSolver;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static void setupSettings() {
        Scanner scanner = new Scanner(System.in);

        // Chose an instance
        System.out.println("\n Select an instace:");
        System.out.println("[0] Sparse82 - 10 DB instances");
        System.out.println("[1] RanReal480 - 20 RanReal instances with n = 480");
        System.out.println("[2] RanReal240 - 20 RanReal instances with n = 240");
        System.out.println("[3] Handover - 83 Synthetic instances");

        int type = scanner.nextInt();
        switch(type) {
            case 0: Parameters.clusterType = ClusterType.SPARSE_82;break;
            case 1: Parameters.clusterType = ClusterType.RANREAL_480;break;
            case 2: Parameters.clusterType = ClusterType.RANREAL_240; break;
            case 3: Parameters.clusterType = ClusterType.HANDOVER; break;
            default: System.out.println("Incorrect number"); System.exit(0);
        }
        Parameters.clusterMaxLimit = Parameters.clusterType.getWeightMaxLimit();
        Parameters.clusterMinLimit = Parameters.clusterType.getWeightMinLimit();

        //Choose fileName
        StringBuilder chooseFile = new StringBuilder();
        chooseFile.append("\n Select a file name:");

        List<String> files = Parameters.clusterType.getFiles();
        for(int i =0; i < files.size(); i++) {
            chooseFile.append("\r\n [").append(i).append("] ").append(files.get(i));
        }
        System.out.println(chooseFile.toString());
        int fileNumber = scanner.nextInt();
        Parameters.fileName = Parameters.clusterType.getFiles().get(fileNumber);

        //Choose number of iterations
        System.out.println("\r\n Enter the number of the tabu search iterations");
        int iterations = scanner.nextInt();
        Parameters.iterations = iterations;
    }

    private static void startAlgorithm() {
        FileParser fp = new FileParser();
        Sources source = fp.parseFile();

        //Seed nodes
        ProblemSolver problemSolver = new ProblemSolver(source);
        problemSolver.seedNodes();
        System.out.println("Seed nodes iteration");
        problemSolver.printClusters();

        //Improve current solution
        for(int i = 0; i < Parameters.iterations; i++) {
            System.out.println("====================");
            System.out.println("Iteration " + (i+1));
            problemSolver.improve();
            problemSolver.printClusters();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== START PROGRAM ===");
        setupSettings();
        startAlgorithm();
        System.out.println("=== FINISH PROGRAM ===");
    }
}