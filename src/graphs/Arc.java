package graphs;

/*
 * CLASS ARC - REPRESENTE UNE ARETE ORIENTEE D'UN GRAPHE
 * STRUCTURE : ARC = SOURCE -> DESTINATION AVEC UN POIDS ASSOCIE
 * VERSION BASIQUE POUR L'INSTANT AVEC LES METHODES ESSENTIELLES:
 *   - STOCKAGE DE LA SOURCE, DESTINATION ET POIDS DE L'ARC
 *   - FOURNIT DES GETTERS POUR ACCEDER A CES INFORMATIONS
 * CLASSE EXTENSIBLE : A EVOLUER SELON LES BESOINS DE NOTRE PROJET
 */

public class Arc {

    private Node source;
    private double weight;
    private Node destination;

    private boolean selected;
    private int departureTimeInSeconds;



    // CONSTRUCTEUR QUI VA INTIALISER LES ATTRIBUTS
    public Arc(Node startNode, Node endNode, double newWeight) {
        this.source = startNode;
        this.destination = endNode;
        this.weight = newWeight;
        this.selected = false; // PAR DEFAUT PAS 'SELECTIONE'
        this.departureTimeInSeconds = 0;
    }

    public boolean isSelected(){
        return selected;
    }

    public int getDepartureTimeInSeconds(){
        return departureTimeInSeconds;
    }

    public void setArcSelection(boolean newIsSelected){
        this.selected = newIsSelected;
    }

    public void select(){
        this.selected = true;
    }

    public void deSelect(){
        this.selected = false;
    }

    public void setDepartureTimeInSeconds(int newDepartureTimeInSeconds){
        this.departureTimeInSeconds = newDepartureTimeInSeconds;
    }

    // LES METHODES D'ACCEES AUX ATTRIBUTS (LES GETTERS)
    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }
}
