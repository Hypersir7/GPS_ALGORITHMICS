package graphs;

import java.util.ArrayList;

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

    public boolean removeVertex(Node vertex){
        return vertices.remove(vertex);
    }
}

