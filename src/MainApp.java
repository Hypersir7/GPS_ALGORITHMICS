
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import graphs.CSVReader;
import graphs.Graph;
import graphs.GraphFactory;
import graphs.Node;
import graphs.Route;
import graphs.SimpleGraph;
import graphs.StopTime;
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

        List<Stop> Delijn_stops = CSVReader.loadStops(DELIJNStopsFilePath);
        List<StopTime> Delijn_stopTimes = CSVReader.loadStopTimes(DELIJNStopTimesFilePath);
        Map <String, String> Delijn_trips = CSVReader.loadTrips(DELIJNTripsFilePath);
        Map <String, Route> Delijn_routes = CSVReader.loadRoutes(DELIJNRoutesFilePath);

        List<Stop> Sncb_stops = CSVReader.loadStops(SNCBStopsFilePath);
        List<StopTime> Sncb_stopTimes = CSVReader.loadStopTimes(SNCBStopTimesFilePath);
        Map <String, String> Sncb_trips = CSVReader.loadTrips(SNCBTripsFilePath);
        Map <String, Route> Sncb_routes = CSVReader.loadRoutes(SNCBRoutesFilePath);

        List<Stop> Stib_stops = CSVReader.loadStops(STIBStopsFilePath);
        List<StopTime> Stib_stopTimes = CSVReader.loadStopTimes(STIBStopTimesFilePath);
        Map <String, String> Stib_trips = CSVReader.loadTrips(STIBTripsFilePath);
        Map <String, Route> Stib_routes = CSVReader.loadRoutes(STIBRoutesFilePath);

        List<Stop> Tec_stops = CSVReader.loadStops(TECStopsFilePath);
        List<StopTime> Tec_stopTimes = CSVReader.loadStopTimes(TECStopTimesFilePath);
        Map <String, String> Tec_trips = CSVReader.loadTrips(TECTripsFilePath);
        Map <String, Route> Tec_routes = CSVReader.loadRoutes(TECRoutesFilePath);

        List<Node> Delijn_nodes = GraphFactory.stopsToNodes(Delijn_stops);
        GraphFactory.buildArcs(Delijn_stopTimes, Delijn_nodes, Delijn_trips, Delijn_routes);

        List<Node> Sncb_nodes = GraphFactory.stopsToNodes(Sncb_stops);
        GraphFactory.buildArcs(Sncb_stopTimes, Sncb_nodes, Sncb_trips, Sncb_routes);

        List<Node> Stib_nodes = GraphFactory.stopsToNodes(Stib_stops);
        GraphFactory.buildArcs(Stib_stopTimes, Stib_nodes, Stib_trips, Stib_routes);

        List<Node> Tec_nodes = GraphFactory.stopsToNodes(Tec_stops);
        GraphFactory.buildArcs(Tec_stopTimes, Tec_nodes, Tec_trips, Tec_routes);

        Delijn_stops = null;
        Delijn_stopTimes = null;
        Delijn_trips = null;
        Delijn_routes = null;
        Sncb_stops = null;
        Sncb_stopTimes = null;
        Sncb_trips = null;
        Sncb_routes = null;
        Stib_stops = null;
        Stib_stopTimes = null;
        Stib_trips = null;
        Stib_routes = null;
        Tec_stops = null;
        Tec_stopTimes = null;
        Tec_trips = null;
        Tec_routes = null;
        System.gc();

        Graph g = new Graph();
        g.addVertices(Delijn_nodes);
        g.addVertices(Sncb_nodes);
        g.addVertices(Stib_nodes);
        g.addVertices(Tec_nodes);


        Node source = g.getVertex("STIB-1293"); // gare du nord
        Node destination = g.getVertex("STIB-4306"); // etterbeek

        System.out.println("Source : " + source.getNodeName());
        System.out.println("Destination : " + destination.getNodeName());

        dijkstra dij = new dijkstra();
        ArrayList<String> chemin =  dij.getShortestPath(g, source, destination, 8 * 3600 + 15 * 60);
        for (String s : chemin){
            System.out.print(s);
        }

        SimpleGraph.setGraph(g);
        SimpleGraph.draw();
    }
}