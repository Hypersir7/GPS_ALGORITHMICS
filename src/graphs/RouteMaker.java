package graphs;

import database.IndexedFileReader;
import tools.Split;

public class RouteMaker {
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

    public RouteMaker(String p1, String p2, String p3, String p4){
        delijnPath = p1;
        sncbPath = p2;
        stibPath = p3;
        tecPath = p4;

        t1 = new Thread(() -> {
            deljin = new IndexedFileReader(delijnPath);
        });
        t2 = new Thread(() -> {
            sncb = new IndexedFileReader(sncbPath);
        });
        t3 = new Thread(() -> {
            stib = new IndexedFileReader(stibPath);
        });
        t4 = new Thread(() -> {
            tec = new IndexedFileReader(tecPath);
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
    }
    public Route makeRoute (String routeId){
        String company = routeId.split("-")[0];
        Route r = null;
        String line = "";
        switch (company) {
            case "DELIJN":
                line = deljin.getLine(deljin.getIdIndex(routeId));
                break;
        
            case "SNCB":
                line = sncb.getLine(sncb.getIdIndex(routeId));
                break;
            case "STIB":
                line = stib.getLine(stib.getIdIndex(routeId));
                break;
            case "TEC":
                line = tec.getLine(tec.getIdIndex(routeId));
                break;
        }
        if (!line.equals("")){
            String [] tmp = Split.splitLine(line, ",", 4);
            r = new Route(tmp[0], tmp[1], tmp[2], tmp[3]);
        }
        return r;
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
