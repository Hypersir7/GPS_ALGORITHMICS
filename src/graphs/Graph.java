package graphs;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private ArrayList<Node> vertices;
    

    public Graph(){
        this.vertices = new ArrayList<>();
    }

    public ArrayList<Node> getVertices(){
        return vertices;
    }

    public boolean addVertex(Node vertex){
        return vertices.add(vertex);
    }

    public boolean addVertices(ArrayList<Node> nodes){
        return vertices.addAll(nodes);
    }

    public boolean addVertices(List<Node> nodes){
        return vertices.addAll(nodes);
    }

    public boolean removeVertex(Node vertex){
        return vertices.remove(vertex);
    }
}

