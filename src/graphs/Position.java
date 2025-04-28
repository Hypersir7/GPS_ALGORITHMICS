package graphs;

public class Position {
    
    private double x;
    private double y;

    // CONSTRUCTOR: INITIALISER LES CORDONNEES
    Position(double newX, double newY){
        this.x = newX;
        this.y = newY;
    }

    
    // SETTERS : POUR MODIFIER LES CORDONNEES
    public void setX(double newX){
        this.x = newX;
    }

    public void setY(double newY){
        this.y = newY;
    }

    // GETTERS : POUR ACCEDER AUX CORDONNEES
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }

}
