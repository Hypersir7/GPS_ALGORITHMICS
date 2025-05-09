package graphs;

import java.util.ArrayList;

import database.IndexedFileReader;
import tools.Split;
import tools.TimeConvertor;

public class ArcMaker {
    String delijnPath;
    String sncbPath;
    String stibPath;
    String tecPath;

    IndexedFileReader deljin ;
    IndexedFileReader sncb ;
    IndexedFileReader stib ;
    IndexedFileReader tec ;

    Thread t1 ;
    Thread t2 ;
    Thread t3 ;
    Thread t4 ;

    public ArcMaker(String p1, String p2, String p3, String p4){
        delijnPath = p1;
        sncbPath = p2;
        stibPath = p3;
        tecPath = p4;

        t1 = new Thread(() -> {
            deljin = new IndexedFileReader(delijnPath, 2, false);
        });
        t2 = new Thread(() -> {
            sncb = new IndexedFileReader(sncbPath, 2, false);
        });
        t3 = new Thread(() -> {
            stib = new IndexedFileReader(stibPath, 2, false);
        });
        t4 = new Thread(() -> {
            tec = new IndexedFileReader(tecPath, 2, false);
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
    }

    public void findArcs (Node node, Graph g, NodeMaker nodeMaker, TripMaker tripMaker, RouteMaker routeMaker){
        String nodeId = node.getUniqueID();
        String company = nodeId.split("-")[0];
        String lineInDB = "";
        String nextLineInDB = "";
        ArrayList<Integer> indexList;
        
        switch (company) {
            case "DELIJN":
                indexList = deljin.getIndexList(nodeId);
                for (int i : indexList){
                    lineInDB = deljin.getLine(i);
                    nextLineInDB = deljin.getLine(i + 1);
                    if (lineInDB != null && nextLineInDB != null){
                        makeArc(node, lineInDB, nextLineInDB, g, nodeMaker, tripMaker, routeMaker);
                    }
                }
                
                break;
        
            case "SNCB":
                indexList = sncb.getIndexList(nodeId);
                for (int i : indexList){
                    lineInDB = sncb.getLine(i);
                    nextLineInDB = sncb.getLine(i + 1);
                    if (lineInDB != null && nextLineInDB != null){
                        makeArc(node, lineInDB, nextLineInDB, g, nodeMaker, tripMaker, routeMaker);
                    }
                }
                break;
            case "STIB":
                indexList = stib.getIndexList(nodeId);
                for (int i : indexList){
                    lineInDB = stib.getLine(i);
                    nextLineInDB = stib.getLine(i + 1);
                    if (lineInDB != null && nextLineInDB != null){
                        makeArc(node, lineInDB, nextLineInDB, g, nodeMaker, tripMaker, routeMaker);
                    }
                }
                break;
            case "TEC":
                indexList = tec.getIndexList(nodeId);
                for (int i : indexList){
                    lineInDB = tec.getLine(i);
                    nextLineInDB = tec.getLine(i + 1);
                    if (lineInDB != null && nextLineInDB != null){
                        makeArc(node, lineInDB, nextLineInDB, g, nodeMaker, tripMaker, routeMaker);
                    }
                }
                break;
        }
    }
    
    private void makeArc(Node node, String lineInDB, String nextLineInDB, Graph g, NodeMaker nodeMaker, TripMaker tripMaker, RouteMaker routeMaker){
        String [] tmpA = Split.splitLine(lineInDB, ",", 4);
        String [] tmpB = Split.splitLine(nextLineInDB, ",", 4);
        String tripIdA = tmpA[0];
        String tripIdB = tmpB[0];
        if (tripIdA.equals(tripIdB)){ // alors on un arc 
            Node destinationNode ;
            if (g.getVertex(tmpB[2]) == null){
                destinationNode = nodeMaker.makeNode(tmpB[2]);
                g.addVertex(destinationNode);
            }else{
                destinationNode = g.getVertex(tmpB[2]);
            }
            Trip trip = tripMaker.makeTrip(tmpA[0]);
            Route route = routeMaker.makeRoute(trip.getRouteId());
            int departureTimeA = TimeConvertor.convertTimeToSeconds(tmpA[1]);
            int departureTimeB = TimeConvertor.convertTimeToSeconds(tmpB[1]);
            int weight = departureTimeB - departureTimeA;
            node.addArc(destinationNode, weight, departureTimeA, route);
            
        }
    }

    public int getSize(){
        return deljin.getSize() + sncb.getSize() + stib.getSize() + tec.getSize();
    }
    
    public void sync(){
        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
}