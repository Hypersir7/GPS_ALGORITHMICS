package graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
