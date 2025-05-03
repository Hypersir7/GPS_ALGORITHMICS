package graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    
    public static List<Stop> loadStops(String filePath){
        // DEBUG
        System.out.println("[DEBUG] current filePath: " + filePath);
        List<Stop> stops = new ArrayList<Stop>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            boolean isHeader = true;
            while((line = br.readLine()) != null){
                if(isHeader){
                    isHeader = false; // ON SKIPPE LA PREMIERE LINGE: LE HEADER(NOMS DES COLONNES)
                    continue;
                }
                String[] data = line.split(",");
                
                String stopID = data[0].trim();
                String stopName = data[1].trim();
                double stopLongitude = Double.parseDouble(data[2].trim());
                double stopLatitude = Double.parseDouble(data[3].trim());
                Stop stopObject = new Stop(stopID, stopName, stopLongitude, stopLatitude);
                stops.add(stopObject);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stops;
    }
    public static List<StopTime> loadStopTimes(String filePath){
        List<StopTime> stopTimes = new ArrayList<StopTime>();
        // A IMPLEMENTER DE LA MEME MANIERE QUE 'loadStops'
        System.out.println("[DEBUG] current filePath: " + filePath);
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            boolean isHeader = true;
            while((line = br.readLine()) != null){
                if(isHeader){
                    isHeader = false; // ON SKIPPE LA PREMIERE LINGE: LE HEADER(NOMS DES COLONNES)
                    continue;
                }
                String[] data = line.split(",");
                
                String tripID = data[0].trim();
            
                String departureTime = data[1].trim();
                String stopID = data[2].trim();
                int stopSequence = Integer.parseInt(data[3].trim());
                StopTime stopTime = new StopTime(tripID, departureTime, stopID, stopSequence);
                stopTimes.add(stopTime);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stopTimes;
    }

    public static Map<String, String> loadTrips(String filePath){
        // DEBUG
        System.out.println("[DEBUG] current filePath: " + filePath);
        Map<String, String> tripID_RouteID = new HashMap<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            boolean isHeader = true;
            while((line = br.readLine()) != null){
                if(isHeader){
                    isHeader = false; // ON SKIPPE LA PREMIERE LINGE: LE HEADER(NOMS DES COLONNES)
                    continue;
                }
                String[] data = line.split(",");
                String tripID = data[0].trim();
                String routeID = data[1].trim();
                tripID_RouteID.put(tripID, routeID);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tripID_RouteID;
    }

    public static Map<String, String> loadRoutes(String filePath){
        // DEBUG
        System.out.println("[DEBUG] current filePath: " + filePath);
        Map<String, String> routeID_TransportType = new HashMap<>();
        //route_id,route_short_name,route_long_name,route_type
        //routeID_TransportType = {route_id:route_type}
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            boolean isHeader = true;
            while((line = br.readLine()) != null){
                if(isHeader){
                    isHeader = false; // ON SKIPPE LA PREMIERE LINGE: LE HEADER(NOMS DES COLONNES)
                    continue;
                }
                String[] data = line.split(",");
                String routeID = data[0].trim();
                String routeTransportType = data[3].trim();
                routeID_TransportType.put(routeID, routeTransportType);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return routeID_TransportType;
    }


    // FONCTION HELPER POUR AFFICHER LES DONNEES ET S'ASSURER QUE 'loadStops' FONCTIONNE BIEN
    public static void displayStops(List<Stop> stops){
        for(Stop stop : stops){
            stop.displayStopData();
        }
    }

    // FONCTION HELPER POUR AFFICHER LES DONNEES ET S'ASSURER QUE 'loadStopTimes' FONCTIONNE BIEN
    public static void displayStopTimes(List<StopTime> stopTimes){
        for(StopTime stopTime : stopTimes){
            stopTime.displayStopTimeData();
        }
    }
}
