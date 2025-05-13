
import java.util.ArrayList;
import java.util.Scanner;

import graphs.Graph;
import graphs.Node;
import graphs.SimpleGraph;
import tools.SortStopTimes;
import algorithms.dijkstra;

import tools.CLI;
import tools.TimeConvertor;
public class MainApp {

    static String DELIJNStopTimesFilePath = "src/database/GTFS/DELIJN/stop_times.csv";
    static String SNCBStopTimesFilePath = "src/database/GTFS/SNCB/stop_times.csv";
    static String STIBStopTimesFilePath = "src/database/GTFS/STIB/stop_times.csv";
    static String TECStopTimesFilePath = "src/database/GTFS/TEC/stop_times.csv";

    
    public void sortStopTimes(){
        System.out.println("Sorting ...");
        long start = System.currentTimeMillis();
        SortStopTimes sstDelijn = new SortStopTimes(DELIJNStopTimesFilePath); sstDelijn.sort();
        SortStopTimes sstSncb = new SortStopTimes(SNCBStopTimesFilePath); sstSncb.sort();
        SortStopTimes sstStib = new SortStopTimes(STIBStopTimesFilePath); sstStib.sort();
        SortStopTimes sstTec = new SortStopTimes(TECStopTimesFilePath); sstTec.sort();
        long end = System.currentTimeMillis();
        long spentTime = end - start;
        System.out.println("Sorted in " + spentTime + " ms");
    }

    public static void main(String[] args) {
        // faire une fois avant de traiter les donnees
        // MainApp mainApp = new MainApp();
        // mainApp.sortStopTimes();

        CLI.showLogo();


        CLI.displayTypeWriter("       Menu Principal\n");
        CLI.displayTypeWriter("[1] Trouver un itinéraire 🛂 \n");
        CLI.displayTypeWriter("[2] Quitter l'appliation 👋\n");
       
        while (true) {
            Scanner scanner = new Scanner(System.in);

            CLI.displayTypeWriter("\n\n  VOTRE CHOIX(tapez 1 ou 2): \n");
            System.out.print("> ");
            String choice = scanner.nextLine().trim();

            if((choice.equals("2"))){
                CLI.displayTypeWriter("Au revoir 👋\n");
                break;
            }else if(!(choice.equals("1"))){
                CLI.displayTypeWriter("❌ Choix invalide!");
                continue;
            }else{
                CLI.displayTypeWriter(" 🚏 Veuillez entrer l'identifiant de départ: ");
                String srcStop_ID = scanner.nextLine();

                CLI.displayTypeWriter(" 🚏 Veuillez entrer l'identifiant de destination: ");
                String destStop_ID = scanner.nextLine();

                CLI.displayTypeWriter(" ⏱️ Veuillez entrer le temps de départ: ");
                String departureTime = scanner.nextLine();
                int timeInSeconds = TimeConvertor.convertTimeToSeconds(departureTime);
                if(timeInSeconds == 0){
                    System.out.println("❌ Format d'heure invalide!");
                    continue;
                }
                Graph g = new Graph();


                Node source = g.makeVertex(srcStop_ID); // Montgo
                Node destination = g.makeVertex(destStop_ID); // ulb STIB-8081

                CLI.displayTypeWriter("ℹ️  Source : " + source.getNodeName());System.out.println();
                CLI.displayTypeWriter("📍Destination : " + destination.getNodeName());System.out.println();

                System.out.println();
                CLI.showSearchingStart();

                long start = System.currentTimeMillis();

                

                dijkstra dij = new dijkstra();
                ArrayList<String> chemin =  dij.getShortestPath(g, source, destination, 15 * 3600 + 32 * 60);

                long end = System.currentTimeMillis();
                long duration = end - start;
                
                System.out.println();
                CLI.showSearchingEnd(duration);;
                
                System.out.println();
                CLI.showResultTitle();
                
                System.out.println();
                for (String s : chemin){
                    CLI.displayTypeWriter(s);System.out.println();
                }

                SimpleGraph.setGraph(g);
                SimpleGraph.draw();
            }
            
            }

        }

}