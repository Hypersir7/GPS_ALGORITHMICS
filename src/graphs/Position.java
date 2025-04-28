package graphs;

public class Position {

    // CORDONNEES 'x' ET 'y' POUR L'AFFICHAGE DANS SIMPLEGRAPHE
    private double x;
    private double y;

    // GPS (DEPUIS 'stops.csv')
    private double longitude;
    private double latitude;


    // CONSTRUCTOR: INITIALISER LES CORDONNEES GPS
    Position(double newLongitude, double newLatitude){
        this.longitude = newLongitude;
        this.latitude = newLatitude;
        // PAR LA SUITE SERA CALCULE AVEC LES CORDONNEES GPS VERS 'x' ET 'y'
        this.x = 0; // INITIALISE A 0
        this.y = 0; // INITIALISE A 0
    }

    // SETTERS : POUR MODIFIER LES CORDONNEES GPS
    public void setLongitude(double newLongitude){
        this.x = newLongitude;
    }

    public void setLatitude(double newLatitude){
        this.x = newLatitude;
    }

    // GETTERS : POUR MODIFIER LES CORDONNEES GPS
    public double getLongitude(){
        return this.longitude;
    }
    
    public double getLatitude(){
        return this.latitude;
    }
    
    // --------- CONVERSION GPS TO 'x' ET 'y'
    
    public static double[] convertGPSToXY(double longitude, double latitude, double minLongitude,
     double maxLongitude, double minLatitude, double maxLatitude, double screenWidth, double screenHeight){
        double x = 0; // TO IMPLEMENT USING LONGITUDE VALUES
        double y = 0; // TO IMPLEMENT USING LATITUDE VALUES
        return new double[] {x, y};
    }


    // SETTERS : POUR MODIFIER LES CORDONNEES 'x' ET 'y'
    public void setX(double newX){
        this.x = newX;
    }

    public void setY(double newY){
        this.y = newY;
    }

    // GETTERS : POUR ACCEDER AUX CORDONNEES 'x' ET 'y'
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }

}
