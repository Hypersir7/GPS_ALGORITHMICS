
import java.util.ArrayList;

import graphs.ArcMaker;
import graphs.Graph;
import graphs.Node;
import graphs.NodeMaker;
import graphs.RouteMaker;
import graphs.SimpleGraph;
import graphs.TripMaker;
import tools.SortStopTimes;
import algorithms.dijkstra;

public class MainApp {

    static String DELIJNStopsFilePath = "src/database/GTFS/DELIJN/stops.csv";
    static String DELIJNStopTimesFilePath = "src/database/GTFS/DELIJN/stop_times.csv";
    static String DELIJNTripsFilePath = "src/database/GTFS/DELIJN/trips.csv";
    static String DELIJNRoutesFilePath = "src/database/GTFS/DELIJN/routes.csv";

    static String SNCBStopsFilePath = "src/database/GTFS/SNCB/stops.csv";
    static String SNCBStopTimesFilePath = "src/database/GTFS/SNCB/stop_times.csv";
    static String SNCBTripsFilePath = "src/database/GTFS/SNCB/trips.csv";
    static String SNCBRoutesFilePath = "src/database/GTFS/SNCB/routes.csv";

    static String STIBStopsFilePath = "src/database/GTFS/STIB/stops.csv";
    static String STIBStopTimesFilePath = "src/database/GTFS/STIB/stop_times.csv";
    static String STIBTripsFilePath = "src/database/GTFS/STIB/trips.csv";
    static String STIBRoutesFilePath = "src/database/GTFS/STIB/routes.csv";

    static String TECStopsFilePath = "src/database/GTFS/TEC/stops.csv";
    static String TECStopTimesFilePath = "src/database/GTFS/TEC/stop_times.csv";
    static String TECTripsFilePath = "src/database/GTFS/TEC/trips.csv";
    static String TECRoutesFilePath = "src/database/GTFS/TEC/routes.csv";
    
    public void sortStopTimes(){
        System.out.println("Sorting ...");
        long start = System.currentTimeMillis();
        SortStopTimes sstDelijn = new SortStopTimes(DELIJNStopTimesFilePath); sstDelijn.sort();
        SortStopTimes sstSncb = new SortStopTimes(SNCBStopTimesFilePath); sstSncb.sort();
        SortStopTimes sstStib = new SortStopTimes(STIBStopTimesFilePath); sstStib.sort();
        SortStopTimes sstTec = new SortStopTimes(TECStopTimesFilePath); sstTec.sort();
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    public static void main(String[] args) {
        // faire une fois avant de traiter les donnees
        // MainApp mainApp = new MainApp();
        // mainApp.sortStopTimes();


        System.out.println("loading ... ");
        long start = System.currentTimeMillis();
        NodeMaker nm = new NodeMaker(DELIJNStopsFilePath, SNCBStopsFilePath, STIBStopsFilePath, TECStopsFilePath);
        TripMaker tm = new TripMaker(DELIJNTripsFilePath, SNCBTripsFilePath, STIBTripsFilePath, TECTripsFilePath);
        RouteMaker rm = new RouteMaker(DELIJNRoutesFilePath, SNCBRoutesFilePath, STIBRoutesFilePath, TECRoutesFilePath);
        ArcMaker am = new ArcMaker(DELIJNStopTimesFilePath, SNCBStopTimesFilePath, STIBStopTimesFilePath, TECStopTimesFilePath);

        nm.sync(); tm.sync(); rm.sync(); am.sync();

        long end = System.currentTimeMillis();
        System.out.println(end - start);

        nm.calcMaxMinLongLat();

        int size = nm.getSize() + tm.getSize() + rm.getSize() + am.getSize();
        System.out.println("Size : " + size);

        // ArrayList<Node> nodes = new ArrayList<>();
        // nodes.add(nm.makeNode("STIB-4306"));
        // nodes.add(nm.makeNode("STIB-1293"));

        // Graph g = new Graph();
        // g.addVertices(nodes);


        // Node source = g.getVertex("STIB-1293"); // gare du nord
        // Node destination = g.getVertex("STIB-4306"); // etterbeek

        // System.out.println("Source : " + source.getNodeName());
        // System.out.println("Destination : " + destination.getNodeName());

        // dijkstra dij = new dijkstra();
        // ArrayList<String> chemin =  dij.getShortestPath(g, source, destination, 8 * 3600 + 15 * 60);
        // for (String s : chemin){
        //     System.out.print(s);
        // }

        // SimpleGraph.setGraph(g);
        // SimpleGraph.draw();
    }
}