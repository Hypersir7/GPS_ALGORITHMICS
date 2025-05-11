
import java.util.ArrayList;

import graphs.Graph;
import graphs.Node;
import graphs.SimpleGraph;
import tools.SortStopTimes;
import algorithms.dijkstra;

public class MainApp {

    static String DELIJNStopTimesFilePath = "src/database/GTFS/DELIJN/stop_times.csv";
    static String SNCBStopTimesFilePath = "src/database/GTFS/SNCB/stop_times.csv";
    static String STIBStopTimesFilePath = "src/database/GTFS/STIB/stop_times.csv";
    static String TECStopTimesFilePath = "src/database/GTFS/TEC/stop_times.csv";

    
    public void sortStopTimes(){
        System.out.println("Sorting ...");
        long start = System.currentTimeMillis();
        SortStopTimes sstDelijn = new SortStopTimes(DELIJNStopTimesFilePath); sstDelijn.sort();
        SortStopTimes sstSncb = new SortStopTimes(SNCBStopTimesFilePath); sstSncb.sort();
        SortStopTimes sstStib = new SortStopTimes(STIBStopTimesFilePath); sstStib.sort();
        SortStopTimes sstTec = new SortStopTimes(TECStopTimesFilePath); sstTec.sort();
        long end = System.currentTimeMillis();
        long spentTime = end - start;
        System.out.println("Sorted in " + spentTime + " ms");
    }

    public static void main(String[] args) {
        // faire une fois avant de traiter les donnees
        // MainApp mainApp = new MainApp();
        // mainApp.sortStopTimes();

        Graph g = new Graph();

        Node source = g.makeVertex("STIB-4306"); // etterbeek
        Node destination = g.makeVertex("STIB-3559"); // ulb

        System.out.println("Source : " + source.getNodeName());
        System.out.println("Destination : " + destination.getNodeName());


        System.out.println("Searching ...");
        long start = System.currentTimeMillis();

        System.out.println("Result : ");

        dijkstra dij = new dijkstra();
        ArrayList<String> chemin =  dij.getShortestPath(g, source, destination, 8 * 3600 + 15 * 60);

        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Search ended in : " + duration + " ms");

        for (String s : chemin){
            System.out.print(s);
        }

        SimpleGraph.setGraph(g);
        SimpleGraph.draw();
    }
}