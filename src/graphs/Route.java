package graphs;

public class Route {
    private String RouteID;
    private String shortName;
    private String longName;
    private String TransportType;

    public Route(String newRouteID, String newShortName, String newLongName, String newTransportType){
        this.RouteID = newRouteID;
        this.shortName = newShortName;
        this.longName = newLongName;
        this.TransportType = newTransportType;
    }

    public void setRouteId(String newRouteID){
        this.RouteID = newRouteID;
    }

    public String getRouteID(){
        return this.RouteID;
    }

    
    public void setShortName(String newShortName){
        this.shortName = newShortName;
    }

    public String getShortName(){
        return this.shortName;
    }
    
    public void setLongName(String newLongName){
        this.longName = newLongName;
    }

    public String getLongName(){
        return this.longName;
    }
    
    public void setTransportType(String newTransportType){
        this.TransportType = newTransportType;
    }

    public String getTransportType(){
        return this.TransportType;
    }
}
