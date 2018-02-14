package cluster.parsing;

import cluster.model.Edge;
import cluster.model.Node;
import cluster.model.Sources;
import cluster.settings.Parameters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Parses file and provide all info about nodes and edges
 */
public class FileParser {

    private ParsingStatus status = ParsingStatus.INIT;
    private int nodeCounter = 0;

    public Sources parseFile() {
        System.out.println("Start parsing file");
        Sources sources = new Sources();

        ClassLoader classLoader = getClass().getClassLoader();
        ZipFile zipFile;
        try {
            zipFile = new ZipFile(classLoader.getResource("ccplib.zip").getFile());

            Enumeration<? extends ZipEntry> entries =  zipFile.entries();
            ZipEntry zipEntry = null;
            String fileFullName = Parameters.clusterType.getFolder() + "/" + Parameters.fileName;
            while (entries.hasMoreElements()) {
                ZipEntry currentEntry = entries.nextElement();
                if(currentEntry.getName().equals(fileFullName)) {
                    zipEntry = currentEntry;
                    break;
                }
            }
            Iterator<String> it = new BufferedReader(
                    new InputStreamReader(zipFile.getInputStream(zipEntry))).lines().iterator();

            String firstLine = it.next();
            String[] firstLineElements = firstLine.split(" ");
            Parameters.numberOfClusters = Integer.parseInt(firstLineElements[1]);
            for (String element : firstLineElements) {
                if (status == ParsingStatus.NODE) {
                    Node node = new Node(nodeCounter, Integer.parseInt(element));
                    sources.addNode(node);
                    nodeCounter++;
                } else if (element.equals("W")) {
                    status = ParsingStatus.NODE;
                }
            }

            while (it.hasNext()) {
                String[] edgeElements = it.next().split(" ");
                double distance = Double.parseDouble(edgeElements[2]);
                if (distance != 0.0) {
                    Edge edge = new Edge(Integer.parseInt(edgeElements[0]),
                            Integer.parseInt(edgeElements[1]),
                            distance);
                    sources.addEdge(edge);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error while parsing file " + Parameters.fileName);
        }

        System.out.println("Finish parsing");
        System.out.println("All nodes and edges:");
        System.out.println(sources);

        return sources;
    }

}
