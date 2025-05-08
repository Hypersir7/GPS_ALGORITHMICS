package graphs;

public class Trip {
    String id ;
    String RouteId;

    public Trip (String TripId, String RouteID){
        id = TripId;
        RouteId = RouteID;

    }
    public String getId(){
        return id;
    }

    public void setId(String newId){
        id = newId;
    }

    public String getRouteId(){
        return RouteId;
    }

    public void setRouteId(String newId){
        RouteId = newId;
    }
}
