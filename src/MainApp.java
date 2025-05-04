
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import graphs.CSVReader;
import graphs.Graph;
import graphs.GraphFactory;
import graphs.Node;
import graphs.SimpleGraph;
import graphs.StopTime;
import graphs.Stop;
import algorithms.dijkstra;

public class MainApp {
    

    public static void main(String[] args) {
        
        String stopsFilePath = "src/database/stops.csv";
        String stopTimesFilePath = "src/database/stop_times.csv";
        String tripsFilePath = "src/database/trips.csv";
        String routesFilePath = "src/database/routes.csv";

        List<Stop> stops = CSVReader.loadStops(stopsFilePath);
        List<StopTime> stopTimes = CSVReader.loadStopTimes(stopTimesFilePath);
        Map <String, String> trips = CSVReader.loadTrips(tripsFilePath);
        Map <String, String> routes = CSVReader.loadRoutes(routesFilePath);


        List<Node> nodes = GraphFactory.stopsToNodes(stops);
        GraphFactory.buildArcs(stopTimes, nodes, trips, routes);
        Graph g = new Graph();

        g.addVertices(nodes);

        Node source = g.getVertex("STIB-4306"); // etterbeek
        Node destination = g.getVertex("STIB-5407"); // ulb
        
        System.out.println("Source : " + source.getNodeName());
        System.out.println("Destination : " + destination.getNodeName());

        dijkstra dij = new dijkstra();
        ArrayList<String> chemin =  dij.getShortestPath(g, source, destination, 28800);
        for (String s : chemin){
            System.out.print(s);
        }

        SimpleGraph.setGraph(g);
        SimpleGraph.draw();
    }
}