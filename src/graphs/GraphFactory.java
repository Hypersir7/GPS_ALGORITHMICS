package graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class GraphFactory {
    
    public static List<Node> stopsToNodes(List<Stop> db_stops){
        double screenWidth = 10000;
        double screenHeight = 10000;
        double scaleX = 1;
        double scaleY = 1;
        double offsetX = - (screenWidth / 2);
        double offsetY = - (screenHeight / 2);

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
            double x = coordinatesXY[0] * scaleX + offsetX; // x CONVERTI A PARTIR DE LA LONGITUDE
            double y = coordinatesXY[1] * scaleY + offsetY; // y CONVERTI A PARTIR DE LA LATITUDE

            Node node = new Node(stop.getStopID(), stop.getStopName(), x, y);
            nodes.add(node);
        }

        return nodes; // Liste d'OBJECT 'Node' PRET A L'USAGE MODELISANT 'stops.csv'
    }

    public static void buildArcs(List<StopTime> stopTimes, List<Node> nodes){

        Map<String, Node> mapNode = new HashMap<>();
        for (Node n : nodes) {
            mapNode.put(n.getUniqueID(), n);
        }
        Map<String, ArrayList<StopTime>> mapST = new HashMap<>();
        for (StopTime st : stopTimes){ // l'idee est de separer les st en fonction de leur tripId
            if (mapST.containsKey(st.getTripID())){ // contient la cle deja 
                mapST.get(st.getTripID()).add(st);
            }else{ // pas encore
                mapST.put(st.getTripID(), new ArrayList<>());
                mapST.get(st.getTripID()).add(st);
            }
        }
        for (ArrayList<StopTime> sts : mapST.values()){
            sts.sort(Comparator.comparingInt(StopTime::getStopSequence));
            for (int i = 0; i < sts.size(); i ++){
                if (i + 1 < sts.size()){
                    mapNode.get(sts.get(i).getStopID()).addArc(mapNode.get(sts.get(i + 1).getStopID()), 0);
                }
                
            }
        }
    }
    
}
