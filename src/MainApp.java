
import java.util.List;
import java.util.Map;

import graphs.CSVReader;
import graphs.Graph;
import graphs.GraphFactory;
import graphs.Node;
import graphs.SimpleGraph;
import graphs.StopTime;
import graphs.Stop;

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

        SimpleGraph.setGraph(g);
        SimpleGraph.draw();
    }
}