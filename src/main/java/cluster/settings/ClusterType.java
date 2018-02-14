package cluster.settings;

import java.util.Arrays;
import java.util.List;

public enum ClusterType {

    SPARSE_82("Sparse82", 25, 75, Arrays.asList(
            "Sparse82_01.txt",
            "Sparse82_02.txt",
            "Sparse82_03.txt",
            "Sparse82_04.txt",
            "Sparse82_05.txt",
            "Sparse82_06.txt",
            "Sparse82_07.txt",
            "Sparse82_08.txt",
            "Sparse82_09.txt",
            "Sparse82_10.txt"
    )),
    RANREAL_480("RanReal480", 100, 150, Arrays.asList(
            "RanReal480_01.txt",
            "RanReal480_02.txt",
            "RanReal480_03.txt",
            "RanReal480_04.txt",
            "RanReal480_05.txt",
            "RanReal480_06.txt",
            "RanReal480_07.txt",
            "RanReal480_08.txt",
            "RanReal480_09.txt",
            "RanReal480_10.txt",
            "RanReal480_11.txt",
            "RanReal480_12.txt",
            "RanReal480_13.txt",
            "RanReal480_14.txt",
            "RanReal480_15.txt",
            "RanReal480_16.txt",
            "RanReal480_17.txt",
            "RanReal480_18.txt",
            "RanReal480_19.txt",
            "RanReal480_20.txt"

            )),
    RANREAL_240("RanReal240", 75, 125, Arrays.asList(
            "RanReal240_01.txt",
            "RanReal240_02.txt",
            "RanReal240_03.txt",
            "RanReal240_04.txt",
            "RanReal240_05.txt",
            "RanReal240_06.txt",
            "RanReal240_07.txt",
            "RanReal240_08.txt",
            "RanReal240_09.txt",
            "RanReal240_10.txt",
            "RanReal240_11.txt",
            "RanReal240_12.txt",
            "RanReal240_13.txt",
            "RanReal240_14.txt",
            "RanReal240_15.txt",
            "RanReal240_16.txt",
            "RanReal240_17.txt",
            "RanReal240_18.txt",
            "RanReal240_19.txt",
            "RanReal240_20.txt"
    )),
    HANDOVER("Handover", 75, 150, Arrays.asList(
            "20_5_270001",
            "20_5_270002",
            "20_5_270003",
            "20_5_270004",
            "20_5_270005",
            "20_10_270001",
            "20_10_270002",
            "20_10_270003",
            "20_10_270004",
            "30_5_270001",
            "30_5_270002",
            "30_5_270003",
            "30_5_270004",
            "30_5_270005",
            "30_10_270001",
            "30_10_270002",
            "30_10_270003",
            "30_10_270004",
            "30_10_270005",
            "30_15_270001",
            "30_15_270002",
            "30_15_270003",
            "30_15_270004",
            "40_5_270001",
            "40_5_270002",
            "40_5_270003",
            "40_5_270004",
            "40_5_270005",
            "40_10_270001",
            "40_10_270002",
            "40_10_270003",
            "40_10_270004",
            "40_10_270005",
            "40_15_270001",
            "40_15_270002",
            "40_15_270003",
            "40_15_270004",
            "40_15_270005",
            "100_15_270001",
            "100_15_270002",
            "100_15_270003",
            "100_15_270004",
            "100_15_270005",
            "100_25_270001",
            "100_25_270002",
            "100_25_270003",
            "100_25_270004",
            "100_25_270005",
            "100_50_270001",
            "100_50_270002",
            "100_50_270003",
            "100_50_270004",
            "100_50_270005",
            "200_15_270001",
            "200_15_270002",
            "200_15_270003",
            "200_15_270004",
            "200_15_270005",
            "200_25_270001",
            "200_25_270002",
            "200_25_270003",
            "200_25_270004",
            "200_25_270005",
            "200_50_270001",
            "200_50_270002",
            "200_50_270003",
            "200_50_270004",
            "200_50_270005",
            "400_15_270001",
            "400_15_270002",
            "400_15_270003",
            "400_15_270004",
            "400_15_270005",
            "400_25_270001",
            "400_25_270002",
            "400_25_270003",
            "400_25_270004",
            "400_25_270005",
            "400_50_270001",
            "400_50_270002",
            "400_50_270003",
            "400_50_270004",
            "400_50_270005"
    ));

    private List<String> files;
    private String folder;
    private int weightMaxLimit;
    private int weightMinLimit;


    public String getFolder() {
        return folder;
    }

    public int getWeightMaxLimit() {
        return weightMaxLimit;
    }

    public List<String> getFiles() {
        return files;
    }

    public int getWeightMinLimit() {
        return weightMinLimit;
    }

    ClusterType(String folder, int weightMinLimit, int limit, List<String> files) {
        this.folder = folder;
        this.weightMinLimit = weightMinLimit;
        this.weightMaxLimit = limit;
        this.files = files;
    }
}
