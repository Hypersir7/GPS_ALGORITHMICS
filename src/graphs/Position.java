package graphs;

public class Position {

    // CORDONNEES 'x' ET 'y' POUR L'AFFICHAGE DANS SIMPLEGRAPHE
    private double x;
    private double y;

    // CONSTRUCTOR: INITIALISER LES CORDONNEES GPS
    Position(double newX, double newY){
        this.x = newX;
        this.y = newY;
    }

    // // SETTERS : POUR MODIFIER LES CORDONNEES GPS
    // public void setLongitude(double newLongitude){
    //     this.longitude = newLongitude;
    // }

    // public void setLatitude(double newLatitude){
    //     this.latitude = newLatitude;
    // }

    // // GETTERS : POUR MODIFIER LES CORDONNEES GPS
    // public double getLongitude(){
    //     return this.longitude;
    // }
    
    // public double getLatitude(){
    //     return this.latitude;
    // }
    
    // --------- CONVERSION GPS TO 'x' ET 'y'
    
    public static double[] convertGPSToXY(double longitude, double latitude, double minLongitude,
        double maxLongitude, double minLatitude, double maxLatitude, double screenWidth, double screenHeight){
        // [min, max] -> [0,screenWidth]
        double x = ((longitude - minLongitude) / (maxLongitude - minLongitude)) * screenWidth;

        // [min, max] -> [0,screenHeight]
        double y = (1- (latitude - minLatitude) / (maxLatitude - minLatitude)) * screenHeight; // TO IMPLEMENT USING LATITUDE VALUES
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
