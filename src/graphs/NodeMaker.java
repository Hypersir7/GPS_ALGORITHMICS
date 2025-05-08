package graphs;

import database.IndexedFileReader;
import tools.Split;

public class NodeMaker {
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

    double maxLongitude = Double.MIN_VALUE;
    double minLongitude = Double.MAX_VALUE;
    double maxLatitude = Double.MIN_VALUE;
    double minLatitude = Double.MAX_VALUE;

    double screenWidth = 1000; // 50000
    double screenHeight = 1000; // 50000
    double scaleX = 1;
    double scaleY = 1;
    double offsetX = - (screenWidth / 2);
    double offsetY = - (screenHeight / 2);

    public NodeMaker (String p1, String p2, String p3, String p4){
        delijnPath = p1;
        sncbPath = p2;
        stibPath = p3;
        tecPath = p4;

        t1 = new Thread(() -> {
            deljin = new IndexedFileReader(delijnPath, 0, true);
        });
        t2 = new Thread(() -> {
            sncb = new IndexedFileReader(sncbPath, 0, true);
        });
        t3 = new Thread(() -> {
            stib = new IndexedFileReader(stibPath, 0, true);
        });
        t4 = new Thread(() -> {
            tec = new IndexedFileReader(tecPath,  0, true);
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
    }

    public Node makeNode (String nodeId){
        String company = nodeId.split("-")[0];
        Node n = null;
        String line = "";
        switch (company) {
            case "DELIJN":
                line = deljin.getLine(deljin.getIdIndex(nodeId));
                break;
        
            case "SNCB":
                line = sncb.getLine(sncb.getIdIndex(nodeId));
                break;
            case "STIB":
                line = stib.getLine(stib.getIdIndex(nodeId));
                break;
            case "TEC":
                line = tec.getLine(tec.getIdIndex(nodeId));
                break;
        }
        if (!line.equals("")){
            String [] tmp = Split.splitLine(line, ",", 4);

            double[] coordinatesXY = Position.convertGPSToXY(Double.parseDouble(tmp[2]), Double.parseDouble(tmp[3]),
             minLongitude, maxLongitude, minLatitude, maxLatitude, screenWidth, screenHeight);
            double x = coordinatesXY[0] * scaleX + offsetX; // x CONVERTI A PARTIR DE LA LONGITUDE
            double y = coordinatesXY[1] * scaleY + offsetY; // y CONVERTI A PARTIR DE LA LATITUDE

            n = new Node(nodeId, tmp[1], x, y);
        }
        return n;
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

    public void calcMaxMinLongLat(){
        maxLongitude = Math.max(Math.max(deljin.getMaxLongitude(), sncb.getMaxLongitude()), Math.max(stib.getMaxLongitude(), tec.getMaxLongitude()));
        maxLatitude = Math.max(Math.max(deljin.getMaxLatitude(), sncb.getMaxLatitude()), Math.max(stib.getMaxLatitude(), tec.getMaxLatitude()));

        minLongitude = Math.min(Math.min(deljin.getMinLongitude(), sncb.getMinLongitude()), Math.min(stib.getMinLongitude(), tec.getMinLongitude()));
        minLatitude = Math.min(Math.min(deljin.getMinLatitude(), sncb.getMinLatitude()), Math.min(stib.getMinLatitude(), tec.getMinLatitude()));
    
    }
}
