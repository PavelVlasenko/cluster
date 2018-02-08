package cluster.settings;

import java.util.List;

public enum ClusterType {
    SPARSE_82("Sparse82"),
    RANREAL_480("RanReal480"),
    RANREAL_240("RanReal240"),
    HANDOVER("Handover");

    private List<String> files;
    private String folder;

    public String getFolder() {
        return folder;
    }

    ClusterType(String folder) {
        this.folder = folder;
    }
}
