import java.util.List;


// TESTING SI 'CSVReader.java', 'GraphFactory', 'Stop', 'StopTme' FONCTIONNENT BIEN 
public class Main {
    public static void main(String[] args){
        String stopsFilePath = "../database/stops.csv";
        String stopTimesFilePath = "../database/stop_times.csv";
        List<Stop> stops = CSVReader.loadStops(stopsFilePath);
        List<StopTime> stopTimes = CSVReader.loadStopTimes(stopTimesFilePath);

        CSVReader.displayStopTimes(stopTimes);

        double screenWidth = 900;
        double screenHeight = 750;

        List<Node> nodes = GraphFactory.stopsToNodes(stops, screenWidth, screenHeight);

        for (Node node : nodes) {
            System.out.println("[Node] " + node.getUniqueID() + " | " + node.getNodeName() + 
                               " (X: " + node.getX() + ", Y: " + node.getY() + ")");
        }


    }
}
