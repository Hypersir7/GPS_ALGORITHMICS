
/*
 * CLASS 'Stop' : MODELISE UN ARRET
 * AYANT LES DONNEES:
 *      stopID;
 *      stopName;
 *      stopLongitude;
 *      stopLatitude;
*/

public class Stop {
    private String stopID;
    private String stopName;
    private double stopLongitude;
    private double stopLatitude;

    Stop(String newStopID, String newStopName, double newLongitude, double newLatitude){
        this.stopID = newStopID;
        this.stopName = newStopName;
        this.stopLongitude = newLongitude;
        this.stopLatitude = newLatitude;
    }

    // GETTERS POUR ACCEDER AUX DONNEES DE L'ARET (STOP)

    public String getStopID(){
        return this.stopID;
    }
    public String getStopName(){
        return this.stopName;
    }
    public double getLongitude(){
        return this.stopLongitude;
    }
    public double getLatitude(){
        return this.stopLatitude;
    }

    public void displayStopData(){
     System.out.println("Stop_ID: " + stopID + " | Stop_Name: " + stopName + " | Stop_Longitude: "
      + stopLongitude + " | Stop_Latitude: " + stopLatitude);   
    }
}



