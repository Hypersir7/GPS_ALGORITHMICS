
import java.util.List;

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
        List<Stop> stops = CSVReader.loadStops(stopsFilePath);
 
        List<StopTime> stopTimes = CSVReader.loadStopTimes(stopTimesFilePath);

        List<Node> nodes = GraphFactory.stopsToNodes(stops);
        GraphFactory.buildArcs(stopTimes, nodes);
        Graph g = new Graph();

        g.addVertices(nodes);

        SimpleGraph.setGraph(g);
        SimpleGraph.draw();
    }
}