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

    // CONSTRUCTEUR QUI VA INTIALISER LES ATTRIBUTS
    public Node(String newUniqueID, String newName) {
        this.uniqueID = newUniqueID;
        this.nodeName = newName;
        this.neighbours = new ArrayList<>();
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
}
