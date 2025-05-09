package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import tools.Split;

import java.util.HashMap;

public class IndexedFileReader {
    // la classe va donner un indice a chaque ligne du fichier afin d'en avoir accees rapidement plus tard
    private RandomAccessFile raf;
    private List<Long> lineOffsets; // a la position 0 on a le header

    private int hashMapKeyIndex;
    private HashMap <Integer, String> indexId ;
    private HashMap <String, ArrayList<Integer>> idIndexList ;

    private boolean isCalcMaxMinLongLat;

    double maxLongitude = Double.MIN_VALUE;
    double minLongitude = Double.MAX_VALUE;
    double maxLatitude = Double.MIN_VALUE;
    double minLatitude = Double.MAX_VALUE;

    public IndexedFileReader(String filePath, int newHashMapKeyIndex, boolean calcMaxMinLongLat) { // calcMaxMinLongLat n'est pas tres bien place ici ms pour une question de 
    // performance, on va calculer la longitude et latitude ici afin de lire une et une seule fois la base de donnees
        hashMapKeyIndex = newHashMapKeyIndex;
        isCalcMaxMinLongLat = calcMaxMinLongLat;
        indexId = new HashMap<>();
        idIndexList = new HashMap<>();
        lineOffsets = new ArrayList<>();
        try {
            raf = new RandomAccessFile(filePath, "r");
        } catch(IOException e){e.printStackTrace();}
        
        String indexedFilePath = filePath.replace(".csv", "Indexed.csv");
        Path path = Paths.get(indexedFilePath);
        if (Files.exists(path)){
            readIndexedFile(path);
        }else{
            buildIndex();
            writeIndexedFile(path);
        }

        
    }

    public IndexedFileReader(String filePath) {
        hashMapKeyIndex = 0;
        isCalcMaxMinLongLat = false;
        indexId = new HashMap<>();
        idIndexList = new HashMap<>();
        lineOffsets = new ArrayList<>();
        try {
            raf = new RandomAccessFile(filePath, "r");
        } catch(IOException e){e.printStackTrace();}
        
        String indexedFilePath = filePath.replace(".csv", "Indexed.csv");
        Path path = Paths.get(indexedFilePath);
        if (Files.exists(path)){
            readIndexedFile(path);
        }else{
            buildIndex();
            writeIndexedFile(path);
        }
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

                String [] tmp = Split.splitLine(line, ",", 4);
                indexId.put(i, tmp[hashMapKeyIndex]);
                if (idIndexList.containsKey(tmp[hashMapKeyIndex])){
                    idIndexList.get(tmp[hashMapKeyIndex]).add(i);
                }else {
                    ArrayList<Integer> tmpArrayList = new ArrayList<>();
                    tmpArrayList.add(i);
                    idIndexList.put(tmp[hashMapKeyIndex], tmpArrayList);
                }
                if (isCalcMaxMinLongLat && i != 0){ // ce calcul est ici pour la performence 
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

    private void writeIndexedFile(Path path){
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                                                             StandardOpenOption.CREATE,
                                                             StandardOpenOption.APPEND)) {
            if (isCalcMaxMinLongLat){
                writer.write(String.valueOf(maxLongitude) + "," + String.valueOf(minLongitude) + "," +
                String.valueOf(maxLatitude) + "," + String.valueOf(minLatitude));
                writer.newLine();
            }
            for (int i = 0; i < lineOffsets.size(); i ++){
                writer.write(String.valueOf(lineOffsets.get(i)) + "," + indexId.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readIndexedFile (Path path){

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line ;
            if (isCalcMaxMinLongLat){
                line = reader.readLine();
                String [] tmp = Split.splitLine(line, ",", 4);
                maxLongitude = Double.parseDouble(tmp[0]);
                minLongitude = Double.parseDouble(tmp[1]);
                maxLatitude = Double.parseDouble(tmp[2]);
                minLatitude = Double.parseDouble(tmp[3]);
            }
            int i = 0;
            while ((line = reader.readLine()) != null) {
                String [] tmp = Split.splitLine(line, ",", 2);
                lineOffsets.add(Long.parseLong(tmp[0]));

                if (idIndexList.containsKey(tmp[1])){
                    idIndexList.get(tmp[1]).add(i);
                }else {
                    ArrayList<Integer> tmpArrayList = new ArrayList<>();
                    tmpArrayList.add(i);
                    idIndexList.put(tmp[1], tmpArrayList);
                }

                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        return idIndexList.get(Id).get(0);
    }

    public String getId (int index){
        String line = getLine(index);
        String [] tmp = Split.splitLine(line, ",", 4);
        return tmp[hashMapKeyIndex];
    }

    public int getSize(){
        int count = 0 ;
        for (ArrayList<Integer> lis : idIndexList.values()){
            count += lis.size();
        }
        return count;
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

    public ArrayList<Integer> getIndexList(String id){
        return idIndexList.get(id);
    }
}
