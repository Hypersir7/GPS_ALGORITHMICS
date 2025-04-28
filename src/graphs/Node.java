package graphs;

import java.util.ArrayList;

/*
 * CLASS NODE - REPRESENTE UN SOMMET DANS UN GRAPHE ORIENTEE
 * STRUCTURE : NOEUD = UNIQUE ID + NOM DU NOEUD + LISTE DES ARCS SORTANTS(LES VOISINS DU SOMMET)
 * VERSION BASIQUE POUR L'INSTANT AVEC LES METHODES ESSENTIELLES:
 *   - STOCKAGE DE L'IDENTIFIANT, NOM ET DES VOISINS DES SOMMETS
 *   - PERMET D'AJOUTER DES ARC VERS D'AUTRE SOMMETS
 *   - FOURNIT DES GETTERS POUR ACCEDER A CES INFORMATIONS
 * CLASSE EXTENSIBLE : A EVOLUER SELON LES BESOINS DE NOTRE PROJET
 */

public class Node {

    private String uniqueID;
    private String nodeName;
    private ArrayList<Arc> neighbours;
    private Position nodePosition;

    // CONSTRUCTEUR QUI VA INTIALISER LES ATTRIBUTS AVEC POSITION DE DEFAUT INITIALISEE A (0,0)
    public Node(String newUniqueID, String newName) {
        this.uniqueID = newUniqueID;
        this.nodeName = newName;
        this.neighbours = new ArrayList<>();
        this.nodePosition = new Position(0, 0);
    }
    // CONSTRUCTOR AVEC POSITION ENTREE PAR L'UTILISATEUR
    public Node(String newUniqueID, String newName, double newX, double newY) {
        this.uniqueID = newUniqueID;
        this.nodeName = newName;
        this.neighbours = new ArrayList<>();
        this.nodePosition = new Position(newX, newY);
    }
    // AJOUTER UN ARC SORTANT VERS UN AUTRE SOMMET AVEC UN POIDS DONNE
    public void addArc(Node endNode, double weight) {
        Arc arc = new Arc(this, endNode, weight);
        this.neighbours.add(arc);
    }

    // GETTER -> QUI VA RETOURNER UNE LISTE DES VOISINS (LES ARCS SORTANTS)
    public ArrayList<Arc> getNeighbours() {
        return neighbours;
    }

    // GETTER -> RETOURNE LE NOM DU SOMMET: VILLE, DESTINATION, SOURCE ETC
    public String getNodeName() {
        return nodeName;
    }

    // GETTER -> RETOURNE L'IDENTIFIANT DU SOMMET: IMPORTANT POUR AVOIR UN ACCEES RAPIDE DANS LE GRAPHE
    public String getUniqueID() {
        return uniqueID;
    }

    // --------------- GESTION DE LA POSITION DU SOMMET ---------------
    // GETTER: ACCEDER A LA POSITION DU SOMMET (X,Y)
    public Position getPosition(){
        return this.nodePosition;
    }
    // SETTER: MODIFIER LA POSITION DU SOMMET (new X, new Y)
    public void setPosition(Position newPosition){
        this.nodePosition = newPosition;
    }

    // ABSTRACTION : ACCEDER ET MODIFIER LES CORDONNEES x ET y DE L'OBJET 'Position'
    // SANS EXPOSER L'OBJECT 'Positon' LUI-MEME : ENCAPSULATION + ABSTRACTION : OOP
    public double getX(){
        return this.nodePosition.getX();
    }
    
    public void setX(double newX){
        this.nodePosition.setX(newX);
    }

    public double getY(){
        return this.nodePosition.getY();
    }

    public void setY(double newY){
        this.nodePosition.setY(newY);
    }
}
