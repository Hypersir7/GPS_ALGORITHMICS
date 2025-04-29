
import graphs.Graph;
import graphs.Node;
import graphs.SimpleGraph;


public class MainApp {
    

    public static void main(String[] args) {
        // You can call launch here, even if MainApp is in another package.
        Graph g = new Graph();
        Node n1 = new Node("1", "gare de remich",50,50);
        Node n2 = new Node("2", "piscine de remich",70,70);
        Node n3 = new Node("3", "Bech-kleinmacher",90,90);

        n1.addArc(n2, 0);
        n2.addArc(n3, 0);
        g.addVertex(n1);
        g.addVertex(n2);
        g.addVertex(n3);

        SimpleGraph.setGraph(g);
        SimpleGraph.draw();
    }
}