package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    private ArrayList<Node> vertices;
    private HashMap<Node, Integer> NodeIndexHashMap ;
    private HashMap<Integer, Node> IndexNodeHashMap ;

    private HashMap<String, Node> IDNodeHashMap ;
    int maxIndex = 0;
    

    public Graph(){
        this.vertices = new ArrayList<>();
        this.NodeIndexHashMap = new HashMap<>();
        this.IndexNodeHashMap = new HashMap<>();
        this.IDNodeHashMap = new HashMap<>();
    }

    public ArrayList<Node> getVertices(){
        return vertices;
    }

    public boolean addVertex(Node vertex){
        maxIndex ++;
        NodeIndexHashMap.put(vertex, maxIndex);
        IndexNodeHashMap.put(maxIndex, vertex);
        IDNodeHashMap.put(vertex.getUniqueID(), vertex);
        return vertices.add(vertex);
    }

    public boolean addVertices(ArrayList<Node> nodes){
        boolean res = vertices.addAll(nodes);
        HashMapNodes();
        return res;
    }

    public boolean addVertices(List<Node> nodes){
        boolean res = vertices.addAll(nodes);
        HashMapNodes();
        return res;
    }

    public boolean removeVertex(Node vertex){
        boolean res = vertices.remove(vertex);
        HashMapNodes();
        return res;
    }

    public int getVertexIndex(Node vertex){
        return this.NodeIndexHashMap.get(vertex);
    }

    public Node getVertex(int index){
        return this.IndexNodeHashMap.get(index);
    }

    public Node getVertex(String NodeID){
        return this.IDNodeHashMap.get(NodeID);
    }
    private void HashMapNodes(){
        HashMap<Node, Integer> hashMapNodeIndex = new HashMap<>();
        HashMap<Integer, Node> hashMapIndexNode = new HashMap<>();
        HashMap <String, Node> hmStringNode = new HashMap<>();
        int i = 0;
        for (; i < vertices.size(); i ++){
            hashMapNodeIndex.put(vertices.get(i), i);
            hashMapIndexNode.put(i, vertices.get(i));
            hmStringNode.put(vertices.get(i).getUniqueID(), vertices.get(i));
        }
        maxIndex = i;
        NodeIndexHashMap.clear();
        IndexNodeHashMap.clear();
        IDNodeHashMap.clear();
        NodeIndexHashMap = hashMapNodeIndex;
        IndexNodeHashMap = hashMapIndexNode;
        IDNodeHashMap = hmStringNode;
    }
}

