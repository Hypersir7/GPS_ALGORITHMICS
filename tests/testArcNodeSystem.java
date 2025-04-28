package tests;

import graphs.Arc;
import graphs.Node;
import graphs.Position;
import javafx.geometry.Pos;

/*
 * TEST LA CLASSE NODE ET LA CLASSE ARC
 * VERIFIE BIEN QUE L'INTERACTION ENTRE CES DEUX CLASSES EST CORRECTE
 * VERSION DE BASE: SIMPLE + EXTENSIBE
 */

public class testArcNodeSystem {

    public static void main(String[] args) {
        Node bruxelles = new Node("BR", "BRUXELLES", 60.743, 5.743);
        Node liege = new Node("LI", "LIEGE"); // DEVRAIT AVOIR Positon(0,0)
        Node namur = new Node("NA", "NAMUR", 55.463, 6.381);
        Node arlon = new Node("AR", "ARLON"); // DEVRAIT AVOIR Positon(0,0)
        Node ottignies = new Node("OT", "OTTIGNIES", 60.043, 4.143);

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

        // TEST DE POSITION
        displayNodePosition(liege);
        displayNodePosition(ottignies);
        displayDistance(bruxelles);
        displayDistance(namur);
        displayNodePosition(arlon);
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

    public static void displayNodePosition(Node node){
        Position nodePosition = node.getPosition();
        System.out.println("Position of: " + node.getNodeName() + "(ID: " + node.getUniqueID()
         + ") : (" + nodePosition.getX() + "," + nodePosition.getY() + ")");
    }
}
