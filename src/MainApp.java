
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import graphs.CSVReader;
import graphs.Graph;
import graphs.GraphFactory;
import graphs.Node;
import graphs.NodeMaker;
import graphs.Route;
import graphs.SimpleGraph;
import graphs.StopTime;
import tools.Split;
import graphs.Stop;
import algorithms.dijkstra;

public class MainApp {
    

    public static void main(String[] args) {
  
        String DELIJNStopsFilePath = "src/database/GTFS/DELIJN/stops.csv";
        String DELIJNStopTimesFilePath = "src/database/GTFS/DELIJN/stop_times.csv";
        String DELIJNTripsFilePath = "src/database/GTFS/DELIJN/trips.csv";
        String DELIJNRoutesFilePath = "src/database/GTFS/DELIJN/routes.csv";

        String SNCBStopsFilePath = "src/database/GTFS/SNCB/stops.csv";
        String SNCBStopTimesFilePath = "src/database/GTFS/SNCB/stop_times.csv";
        String SNCBTripsFilePath = "src/database/GTFS/SNCB/trips.csv";
        String SNCBRoutesFilePath = "src/database/GTFS/SNCB/routes.csv";

        String STIBStopsFilePath = "src/database/GTFS/STIB/stops.csv";
        String STIBStopTimesFilePath = "src/database/GTFS/STIB/stop_times.csv";
        String STIBTripsFilePath = "src/database/GTFS/STIB/trips.csv";
        String STIBRoutesFilePath = "src/database/GTFS/STIB/routes.csv";

        String TECStopsFilePath = "src/database/GTFS/TEC/stops.csv";
        String TECStopTimesFilePath = "src/database/GTFS/TEC/stop_times.csv";
        String TECTripsFilePath = "src/database/GTFS/TEC/trips.csv";
        String TECRoutesFilePath = "src/database/GTFS/TEC/routes.csv";


        long start = System.currentTimeMillis();
        NodeMaker nm = new NodeMaker(DELIJNStopsFilePath, SNCBStopsFilePath, STIBStopsFilePath, TECStopsFilePath);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        System.out.println("Size : " + nm.getSize());

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(nm.makeNode("STIB-4306"));
        nodes.add(nm.makeNode("STIB-1293"));

        Graph g = new Graph();
        g.addVertices(nodes);


        // Node source = g.getVertex("STIB-1293"); // gare du nord
        // Node destination = g.getVertex("STIB-4306"); // etterbeek

        // System.out.println("Source : " + source.getNodeName());
        // System.out.println("Destination : " + destination.getNodeName());

        // dijkstra dij = new dijkstra();
        // ArrayList<String> chemin =  dij.getShortestPath(g, source, destination, 8 * 3600 + 15 * 60);
        // for (String s : chemin){
        //     System.out.print(s);
        // }

        SimpleGraph.setGraph(g);
        SimpleGraph.draw();
    }
}