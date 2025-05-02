
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

        //CSVReader.displayStopTimes(stopTimes);

        double screenWidth = 4000;
        double screenHeight = 4000;

        List<Node> nodes = GraphFactory.stopsToNodes(stops, screenWidth, screenHeight);

        Graph g = new Graph();

        g.addVertices(nodes);

        SimpleGraph.setGraph(g);
        SimpleGraph.draw();
    }
}