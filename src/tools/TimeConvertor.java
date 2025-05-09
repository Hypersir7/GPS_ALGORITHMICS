package tools;

public class TimeConvertor {
    
    public static int convertTimeToSeconds(String timeInString){
        String[] timeParts = timeInString.split(":");
        if(timeParts.length <= 3){
            int hour = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);
            int seconds = Integer.parseInt(timeParts[2]);
            return hour * 3600 + minutes * 60 + seconds;
        }else{
            System.err.println("Time format is not valide!");
            return 0;
        }
    }
}
