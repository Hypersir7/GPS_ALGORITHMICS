
import graphs.Graph;
import graphs.Node;
import graphs.SimpleGraph;


public class MainApp {
    

    public static void main(String[] args) {
        // You can call launch here, even if MainApp is in another package.
        Graph g = new Graph();
        Node n1 = new Node("1", "Gare de remich",50,50);
        Node n2 = new Node("2", "Piscine de remich",90,90);
        Node n3 = new Node("3", "Bech-kleinmacher",150,150);
        Node n4 = new Node("4", "Wellenstein",250,150);
        Node n5 = new Node("5", "Wellenstein-Centre",250,250);

        n1.addArc(n2, 0);
        n2.addArc(n3, 0);
        n3.addArc(n4, 0);
        n4.addArc(n5, 0);
        n5.addArc(n4,0);
        
        g.addVertex(n1);
        g.addVertex(n2);
        g.addVertex(n3);
        g.addVertex(n4);
        g.addVertex(n5);

        SimpleGraph.setGraph(g);
        SimpleGraph.draw();
    }
}