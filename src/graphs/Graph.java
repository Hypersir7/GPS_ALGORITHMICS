package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

    static String DELIJNStopsFilePath = "src/database/GTFS/DELIJN/stops.csv";
    static String DELIJNStopTimesFilePath = "src/database/GTFS/DELIJN/stop_times.csv";
    static String DELIJNTripsFilePath = "src/database/GTFS/DELIJN/trips.csv";
    static String DELIJNRoutesFilePath = "src/database/GTFS/DELIJN/routes.csv";

    static String SNCBStopsFilePath = "src/database/GTFS/SNCB/stops.csv";
    static String SNCBStopTimesFilePath = "src/database/GTFS/SNCB/stop_times.csv";
    static String SNCBTripsFilePath = "src/database/GTFS/SNCB/trips.csv";
    static String SNCBRoutesFilePath = "src/database/GTFS/SNCB/routes.csv";

    static String STIBStopsFilePath = "src/database/GTFS/STIB/stops.csv";
    static String STIBStopTimesFilePath = "src/database/GTFS/STIB/stop_times.csv";
    static String STIBTripsFilePath = "src/database/GTFS/STIB/trips.csv";
    static String STIBRoutesFilePath = "src/database/GTFS/STIB/routes.csv";

    static String TECStopsFilePath = "src/database/GTFS/TEC/stops.csv";
    static String TECStopTimesFilePath = "src/database/GTFS/TEC/stop_times.csv";
    static String TECTripsFilePath = "src/database/GTFS/TEC/trips.csv";
    static String TECRoutesFilePath = "src/database/GTFS/TEC/routes.csv";

    private ArrayList<Node> vertices;
    private HashMap<Node, Integer> NodeIndexHashMap ;
    private HashMap<Integer, Node> IndexNodeHashMap ;

    private HashMap<String, Node> IDNodeHashMap ;
    int maxIndex = 0;

    NodeMaker nm;
    TripMaker tm;
    RouteMaker rm;
    ArcMaker am;
    

    public Graph(){
        this.vertices = new ArrayList<>();
        this.NodeIndexHashMap = new HashMap<>();
        this.IndexNodeHashMap = new HashMap<>();
        this.IDNodeHashMap = new HashMap<>();

        System.out.println("loading ... ");
        long start = System.currentTimeMillis();
        nm = new NodeMaker(DELIJNStopsFilePath, SNCBStopsFilePath, STIBStopsFilePath, TECStopsFilePath);
        tm = new TripMaker(DELIJNTripsFilePath, SNCBTripsFilePath, STIBTripsFilePath, TECTripsFilePath);
        rm = new RouteMaker(DELIJNRoutesFilePath, SNCBRoutesFilePath, STIBRoutesFilePath, TECRoutesFilePath);
        am = new ArcMaker(DELIJNStopTimesFilePath, SNCBStopTimesFilePath, STIBStopTimesFilePath, TECStopTimesFilePath);

        nm.sync(); tm.sync(); rm.sync(); am.sync();

        long end = System.currentTimeMillis();
        long spentTime = end - start;
        System.out.println("Loaded in " + spentTime + " ms");

        nm.calcMaxMinLongLat();
    }

    public int getVertexCount(){
        return nm.getSize();
    }

    public Node makeVertex(String NodeId){
        Node node = nm.makeNode(NodeId);
        addVertex(node);
        return node; 
    }

    public void connectVertex(Node node){
        am.findArcs(node, this, nm, tm, rm);
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

