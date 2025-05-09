package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortStopTimes {

    Path path;

    public SortStopTimes(String FilePath){
        path = Paths.get(FilePath);
    }

    public void sort(){
        ArrayList<String> lines = read();
        String header = lines.get(0);
        lines.remove(0);

        Comparator<String> byTripId = new Comparator<String>() {
            public int compare(String a, String b) {
                String [] tmpA = Split.splitLine(a, ",", 4);
                String [] tmpB = Split.splitLine(b, ",", 4);


                int cmp = tmpA[0].compareTo(tmpB[0]);
                if (cmp != 0) {
                    return cmp;
                }

                return Integer.compare(Integer.parseInt(tmpA[3]), Integer.parseInt(tmpB[3]));
            }
        };
        Collections.sort(lines, byTripId);

        write(header, lines);
    }

    private ArrayList<String> read(){
        ArrayList<String> res = new ArrayList<>();
        String line ;
        try (BufferedReader reader = Files.newBufferedReader(path)){
            while ((line = reader.readLine()) != null) {
                res.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    private void write(String header, ArrayList<String> lines){
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)){
            writer.write(header);
            writer.newLine();
            for (String line : lines){
                writer.write(line);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}