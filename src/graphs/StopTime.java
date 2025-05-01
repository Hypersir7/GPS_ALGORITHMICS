import java.time.LocalTime;

/*
 * CLASS 'StopTimes' : MODELISE UN ARRET
 * // 'stop_times.csv' A CETTE FORME: trip_id,departure_time,stop_id,stop_sequence
                                        // STIB-124698409288866001,04:29:00,STIB-1780,1
                                        // STIB-124698409288866001,04:30:00,STIB-6433,2
 * AYANT LES DONNEES SUIVANTES:
 *      tripID;
 *      departureTime
 *      stopID;
 *      stopSequence;
*/


public class StopTime {
    private String tripID;
    private String departureTime;
    private String stopID;
    private int stopSequence;
    
    StopTime(String newTripID, String newDepartureTime, String newStopID, int newStopSequence){
        this.tripID = newTripID;
        this.departureTime = newDepartureTime;
        this.stopID = newStopID;
        this.stopSequence = newStopSequence;
    }

    // GETTERS POUR ACCEDER AUX DONNEES
    public String getTripID(){
        return this.tripID;
    }

    public String getDepartureTime(){
        return this.departureTime;
    }

    public String getStopID(){
        return this.stopID;
    }

    public int getStopSequence(){
        return this.stopSequence;
    }

    public void displayStopTimeData(){
        System.out.println("Trip_ID: " + tripID + " | Departure_Time: " + departureTime + " | Stop_ID: "
         + stopID + " | Stop_Sequence: " + stopSequence);   
       }

}
