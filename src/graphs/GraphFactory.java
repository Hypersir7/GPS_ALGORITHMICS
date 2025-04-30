
import java.util.ArrayList;
import java.util.List;



public class GraphFactory {
    
    public static List<Node> stopsToNodes(List<Stop> db_stops, double screenWidth, double screenHeight){
        double minLongitude = db_stops.get(0).getLongitude();
        double maxLongitude = minLongitude;
        double minLatitude = db_stops.get(0).getLatitude();
        double maxLatitude = minLatitude;

        List<Node> nodes = new ArrayList<>();

        for(Stop stop: db_stops){
            double longitude = stop.getLongitude();
            double latitude = stop.getLatitude();
            if(longitude < minLongitude) minLongitude = longitude;
            if(longitude > maxLongitude) maxLongitude = longitude;
            if(latitude < minLatitude) minLatitude = latitude;
            if(latitude > maxLatitude) maxLatitude = latitude;
        }


        // CONSTRUCTION DES SOMMETS(NODES) A PARTIR DES OBJECTS DE TYPE 'Stop'
        for (Stop stop: db_stops) {
            double[] coordinatesXY = Position.convertGPSToXY(stop.getLongitude(), stop.getLatitude(),
             minLongitude, maxLongitude, minLatitude, maxLatitude, screenWidth, screenHeight);
            double x = coordinatesXY[0]; // x CONVERTI A PARTIR DE LA LONGITUDE
            double y = coordinatesXY[1]; // y CONVERTI A PARTIR DE LA LATITUDE

            Node node = new Node(stop.getStopID(), stop.getStopName(), x, y);
            nodes.add(node);
        }

        return nodes; // Liste d'OBJECT 'Node' PRET A L'USAGE MODELISANT 'stops.csv'
    }

    public static List<Arc> buildArcs(List<StopTime> stopTimes, List<Node> nodes){
        List<Arc> arcs = new ArrayList<>();
        // A VOIR COMMENT ON VA GERER TOUT CELA
        // MAIS L'IDEE EST LA MEME:
        // - LIRE LE CSV: 'stop_times.csv' ET RETOURNER UNE LISTE CONTEANT CES OBJECTS DE TYPE 'StopTime'
        // - ENSUITE DANS 'GraphFactory' CONSTRUIRE ET CONNECTER LES NOEUDS ENSEMBLES
        // 'stop_times.csv' A CETTE FORME: trip_id,departure_time,stop_id,stop_sequence
                                        // STIB-124698409288866001,04:29:00,STIB-1780,1
                                        // STIB-124698409288866001,04:30:00,STIB-6433,2
                                        
        return arcs;
    }
    
}
