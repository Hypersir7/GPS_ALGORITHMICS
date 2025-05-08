package tools;

public class Split {
    public static String [] splitLine(String line, String del, int elementsNumber){
        String [] res = new String[elementsNumber];
        String word = "";
        boolean splitOn = true;
        int j = 0;
        for (int i = 0; i < line.length(); i++){
            if (line.charAt(i) == '\"'){
                splitOn = !splitOn;
                continue;
            }
            if (splitOn && line.charAt(i) == del.charAt(0)){
                res[j] = word;
                word = "";
                j ++;
                if (j >= elementsNumber){
                    break;
                }
                continue;
            }
            word += line.charAt(i);
        }
        if (!word.isEmpty())
            res[j] = word;
        return res;
    }
}