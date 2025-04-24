package tests;

import graphs.Arc;
import graphs.Node;

/*
 * TEST LA CLASSE NODE ET LA CLASSE ARC
 * VERIFIE BIEN QUE L'INTERACTION ENTRE CES DEUX CLASSES EST CORRECTE
 * VERSION DE BASE: SIMPLE + EXTENSIBE
 */

public class testArcNodeSystem {

    public static void main(String[] args) {
        Node bruxelles = new Node("BR", "BRUXELLES");
        Node liege = new Node("LI", "LIEGE");
        Node namur = new Node("NA", "NAMUR");
        Node arlon = new Node("AR", "ARLON");
        Node ottignies = new Node("OT", "OTTIGNIES");
        bruxelles.addArc(namur, 87.6);
        bruxelles.addArc(liege, 95.4);
        liege.addArc(namur, 64.4);
        namur.addArc(arlon, 130);
        arlon.addArc(namur, 130);
        // AFFICHER LES VOISINS DE BRUXLLES (ARCS SORTANTS)
        displayDistance(bruxelles);
        displayDistance(arlon);
        displayDistance(namur);
        displayDistance(ottignies);
    }

    // AFFICHE LES DISTANES POUR LES ARCS SORTANT D'UN NOEUD DONNE EN PARAMETRE
    public static void displayDistance(Node node) {
        if (node.getNeighbours().isEmpty()) {
            System.err.println(
                "THE NODE " +
                node.getNodeName() +
                "(ID: " +
                node.getUniqueID() +
                ") DOES NOT HAVE ANY NEIGHBOUR!"
            );
        }
        for (Arc arc : node.getNeighbours()) {
            System.out.println(
                "DISTANCE FROM " +
                arc.getSource().getNodeName() +
                " -> " +
                arc.getDestination().getNodeName() +
                " = " +
                arc.getWeight() +
                " km"
            );
        }
    }
}
