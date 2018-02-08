package cluster;

import cluster.model.Sources;
import cluster.parsing.FileParser;
import cluster.settings.ClusterType;
import cluster.settings.Parameters;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Start program");
        Parameters.fileName = "Sparse82_10.txt";
        Parameters.clusterType = ClusterType.SPARSE_82;

        FileParser fp = new FileParser();
        Sources s = fp.parseFile();
    }

//    public static void main(String[] args) throws Exception {
//
//        System.out.println(logo);
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("\n Select an instace:");
//        System.out.println("[1] Sparse82 - 10 DB instances");
//        System.out.println("[2] RanReal480 - 20 RanReal instances with n = 480");
//        System.out.println("[3] RanReal240 - 20 RanReal instances with n = 240");
//        System.out.println("[4] Handover - 83 Synthetic instances");
//
//		/*
//		 * Get args parameters
//		 */
//        Parameters parameters = new Parameters();
//        JCommander commander = new JCommander(parameters, args);
//        commander.setProgramName("jvrp");
//
//        if (parameters.isHelp()) {
//            commander.usage();
//            System.exit(0);
//        }
//
//        if (parameters.getFiles().isEmpty()) {
//            List<URL> instances = menu();
//            for (URL i : instances) {
//                solve(i);
//            }
//        }
//        else {
//            for (String file : parameters.getFiles()) {
//                solve(new File(file).toURI().toURL());
//            }
//        }
//        System.out.println("exit");
//    }
//
//
//
//    private static List<URL> menu() {
//
//        System.out.println("\n Select an instace:");
//        System.out.println("[0] all instances");
//        System.out.println("[1] vrpnc1.txt");
//        System.out.println("[2] vrpnc2.txt");
//        System.out.println("[3] vrpnc3.txt");
//        System.out.println("[4] vrpnc4.txt");
//        System.out.println("[5] vrpnc5.txt");
//        System.out.println("[6] vrpnc11.txt");
//        System.out.println("[7] vrpnc12.txt");
//
//        @SuppressWarnings("resource")
//        Scanner scanner = new Scanner(System.in);
//
//        Integer input = 0;
//        try {
//            input = Integer.valueOf(scanner.next("[0-7]"));
//        }
//        catch (InputMismatchException e) {
//            System.out.println("Invalid input");
//            System.exit(0);
//        }
//        finally {
//            scanner.close();
//        }
//
//        List<URL> instances = new ArrayList<>();
//
//        switch (input) {
//            case 0:
//                instances.add(Main.class.getClassLoader().getResource("vrp/Christofides-Mingozzi-Toth_1979/vrpnc1.txt"));
//                instances.add(Main.class.getClassLoader().getResource("vrp/Christofides-Mingozzi-Toth_1979/vrpnc2.txt"));
//                instances.add(Main.class.getClassLoader().getResource("vrp/Christofides-Mingozzi-Toth_1979/vrpnc3.txt"));
//                instances.add(Main.class.getClassLoader().getResource("vrp/Christofides-Mingozzi-Toth_1979/vrpnc4.txt"));
//                instances.add(Main.class.getClassLoader().getResource("vrp/Christofides-Mingozzi-Toth_1979/vrpnc5.txt"));
//                instances.add(Main.class.getClassLoader().getResource("vrp/Christofides-Mingozzi-Toth_1979/vrpnc11.txt"));
//                instances.add(Main.class.getClassLoader().getResource("vrp/Christofides-Mingozzi-Toth_1979/vrpnc12.txt"));
//                break;
//            case 1: instances.add(Main.class.getClassLoader().getResource("vrp/Christofides-Mingozzi-Toth_1979/vrpnc1.txt"));
//                break;
//            case 2: instances.add(Main.class.getClassLoader().getResource("vrp/Christofides-Mingozzi-Toth_1979/vrpnc2.txt"));
//                break;
//            case 3: instances.add(Main.class.getClassLoader().getResource("vrp/Christofides-Mingozzi-Toth_1979/vrpnc3.txt"));
//                break;
//            case 4: instances.add(Main.class.getClassLoader().getResource("vrp/Christofides-Mingozzi-Toth_1979/vrpnc4.txt"));
//                break;
//            case 5: instances.add(Main.class.getClassLoader().getResource("vrp/Christofides-Mingozzi-Toth_1979/vrpnc5.txt"));
//                break;
//            case 6: instances.add(Main.class.getClassLoader().getResource("vrp/Christofides-Mingozzi-Toth_1979/vrpnc11.txt"));
//                break;
//            case 7: instances.add(Main.class.getClassLoader().getResource("vrp/Christofides-Mingozzi-Toth_1979/vrpnc12.txt"));
//                break;
//        }
//
//        return instances;
//    }
//
//
//
//    private static void solve(URL instanceFilename) throws IOException {
//
//        log.info("*************************************************************************************************************************");
//        log.info("*");
//        log.info("* Solving '{}' ", instanceFilename);
//        log.info("*");
//        log.info("*************************************************************************************************************************");
//
//		/*
//		 * Problem loading
//		 */
//        Loader loader = ChristofidesLoader.getInstance();
//        Problem problem = loader.load(instanceFilename);
//
//        log.info("Loaded problem '{}'", instanceFilename);
//        log.info("number of customers: {}", problem.getCustomers().size());
//        log.info("capacity: {}", problem.getVehicleCapacity());
//        log.info("problem is valid? {}", problem.isValid());
//
//		/*
//		 * Define
//		 * - Initializer: to obtain an initial solution
//		 * - Strategy: to minimize the solution at each step
//		 */
//        Initializer initializer = new BasicInitializer();
//        Strategy strategy = new SimpleStrategy(
//                problem,
//                TwoOptOption.BEST_IMPROVEMENT,
//                RelocateOption.BEST_IMPROVEMENT,
//                false
//        );
//
//        log.info("strategy: {}", strategy.toString());
//
//		/*
//		 * Preparing solver
//		 */
//        ProblemSolver solver = new ProblemSolver(
//                initializer,
//                strategy
//        );
//
//		/*
//		 * Solve the problem
//		 */
//        long t0 = System.currentTimeMillis();
//        Solution sol = solver.solve(problem);
//        long t1 = System.currentTimeMillis();
//
//        log.info("solution found \n{}", sol.toString(problem.getCostMatrix()));
//        log.info("total cost: {}", sol.cost(problem.getCostMatrix()));
//        log.info("time elapsed: {}", t1-t0);
//
//        System.out.println();
//    }
//
//    private static final String logo =
//                    " Cluster algorithm                                \n"
//            ;
}