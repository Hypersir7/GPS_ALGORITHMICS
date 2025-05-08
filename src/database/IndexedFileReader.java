package database;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import tools.Split;

import java.util.HashMap;

public class IndexedFileReader {
    // la classe va donner un indice a chaque ligne du fichier afin d'en avoir accees rapidement plus tard
    private RandomAccessFile raf;
    private List<Long> lineOffsets; // a la position 0 on a le header

    private int hashMapKeyIndex;
    private HashMap <String, Integer> idIndex ;

    private boolean isCalcMaxMinLongLat;

    double maxLongitude = Double.MIN_VALUE;
    double minLongitude = Double.MAX_VALUE;
    double maxLatitude = Double.MIN_VALUE;
    double minLatitude = Double.MAX_VALUE;

    public IndexedFileReader(String filePath, int newHashMapKeyIndex, boolean calcMaxMinLongLat) { // calcMaxMinLongLat n'est pas tres bien place ici ms pour une question de 
    // performance, on va calculer la longitude et latitude ici afin de lire une et une seule fois la base de donnees
        hashMapKeyIndex = newHashMapKeyIndex;
        isCalcMaxMinLongLat = calcMaxMinLongLat;
        idIndex = new HashMap<>();
        lineOffsets = new ArrayList<>();
        try {
            raf = new RandomAccessFile(filePath, "r");
        } catch(IOException e){e.printStackTrace();}
        
        buildIndex();
    }

    public IndexedFileReader(String filePath) {
        hashMapKeyIndex = 0;
        isCalcMaxMinLongLat = false;
        idIndex = new HashMap<>();
        lineOffsets = new ArrayList<>();
        try {
            raf = new RandomAccessFile(filePath, "r");
        } catch(IOException e){e.printStackTrace();}
        
        buildIndex();
    }

    private void buildIndex() {
        String line;
        int i = 0 ;
        try {
            lineOffsets.add(raf.getFilePointer());
            while ((line = raf.readLine()) != null) {
                if (line.equals("")){
                    continue;
                }
                lineOffsets.add(raf.getFilePointer());
                if (i == 0){
                    i++;
                    continue;
                }
                String [] tmp = Split.splitLine(line, ",", 4);
                idIndex.put(tmp[hashMapKeyIndex], i);
                if (isCalcMaxMinLongLat){ // ce calcul est ici pour la performence 
                    double longitude = Double.parseDouble(tmp[2]);
                    double latitude = Double.parseDouble(tmp[3]);
                    if(longitude < minLongitude) minLongitude = longitude;
                    if(longitude > maxLongitude) maxLongitude = longitude;
                    if(latitude < minLatitude) minLatitude = latitude;
                    if(latitude > maxLatitude) maxLatitude = latitude;
                }
                i ++;
            }
        } catch(IOException e){e.printStackTrace();}
        
    }

    public String getLine(int j) {
        String res = null ;
        if (j >= lineOffsets.size() - 1) return null;
        try {
            raf.seek(lineOffsets.get(j));
            res = raf.readLine();
        } catch(IOException e) {e.printStackTrace();}
        
        return res;
    }

    public int getHashMapKeyIndex(){
        return hashMapKeyIndex;
    }

    public int getIdIndex(String Id){
        return idIndex.get(Id);
    }

    public String getId (int index){
        String line = getLine(index);
        String [] tmp = Split.splitLine(line, ",", 4);
        return tmp[hashMapKeyIndex];
    }

    public int getSize(){
        return idIndex.size();
    }

    public double getMaxLongitude(){
        if (isCalcMaxMinLongLat){
            return maxLongitude;
        }else{
            return 0;
        }
    }
    
    public double getMinLongitude(){
        if (isCalcMaxMinLongLat){
            return minLongitude;
        }else{
            return 0;
        }
    }
    
    
    public double getMaxLatitude(){
        if (isCalcMaxMinLongLat){
            return maxLatitude;
        }else{
            return 0;
        }
    }

    
    public double getMinLatitude(){
        if (isCalcMaxMinLongLat){
            return minLatitude;
        }else{
            return 0;
        }
    }
}
