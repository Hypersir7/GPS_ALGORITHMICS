package algorithms;

import graphs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;


public class dijkstra {

    public ArrayList<String> getShortestPath(Graph graph, Node source, Node destination, double departureTime){
        ArrayList<String> res = new ArrayList<>();

        // tout d'abord on trouve le plus court chemin et apres on track back pour donner le plus court chemin

        int sourceIndex = graph.getVertexIndex(source);
        int destinationIndex = graph.getVertexIndex(destination);

        boolean [] isVisited = new boolean[graph.getVertexCount()];
        double [] distanceTo = new double[graph.getVertexCount()];
        double [] currentTime = new double[graph.getVertexCount()];
        int [] previousVertex = new int[graph.getVertexCount()];
        Arc [] previousArc = new Arc[graph.getVertexCount()];

        Arrays.fill(distanceTo, Integer.MAX_VALUE);
        Arrays.fill(currentTime, -1);
        Arrays.fill(previousVertex, -1);

        distanceTo[sourceIndex] = 0 ;
        currentTime[sourceIndex] = departureTime;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingDouble(i -> distanceTo[i]));
        pq.add(sourceIndex);

        while (!pq.isEmpty()) {
            int idx = pq.poll();
            if (idx == destinationIndex){break;}
            if (graph.getVertex(idx).getNeighbours().isEmpty()){
                graph.connectVertex(graph.getVertex(idx)); // charger les sommets et arrets du disk dur avant de calculer
            }
            for (Arc arc : graph.getVertex(idx).getNeighbours()){
                int v = graph.getVertexIndex(arc.getDestination());
                if (isVisited[v]){continue;}
                double waitingTimeTilDeparture = arc.getDepartureTimeInSeconds() - currentTime[idx];
                
                if (waitingTimeTilDeparture < 0){continue;}
                double weight = waitingTimeTilDeparture + arc.getWeight();
                if (distanceTo[idx] + weight < distanceTo[v]){
                    distanceTo[v] = distanceTo[idx] + weight;
                    currentTime[v] = currentTime[idx] + weight;
                    previousVertex[v] = idx;
                    previousArc[v] = arc; // on veut savoir les arcs qu'on a utilise 

                    pq.add(v);
                }
            }
            isVisited[idx] = true;
            
        }

        // on track back 
        if (distanceTo[destinationIndex] == Integer.MAX_VALUE){
            res.add("No path was found");
            return res;
        }
        ArrayList<Integer> stopsOfPath = new ArrayList<>();
        ArrayList<Arc> importantArcs = new ArrayList<>();

        int currentNode = destinationIndex;
        while (currentNode != -1) {
            stopsOfPath.add(currentNode);
            if (previousArc[currentNode] != null){
                importantArcs.add(previousArc[currentNode]);
                previousArc[currentNode].select(); // slectionne
            }
            currentNode = previousVertex[currentNode];
        }
        Collections.reverse(stopsOfPath);
        Collections.reverse(importantArcs);

        int tripLength = 0;
        String transportShortName = importantArcs.get(0).getRoute().getShortName();
        int i = 0;
        for (Arc arc : importantArcs){
            Node vertexSource = arc.getSource();
            Node vertexDestination = arc.getDestination();
            tripLength ++;
            if ((!arc.getRoute().getShortName().equals(transportShortName))){ // changement de transport
                res.add(vertexSource.getNodeName() + " " + convertSecondsToHours(currentTime[graph.getVertexIndex(vertexSource)]) + "\n");
                tripLength = 1;
                transportShortName = arc.getRoute().getShortName();
                
            }

            if (tripLength == 1){ //  && i != importantArcs.size() - 1
                res.add("Take " + arc.getRoute().getRouteID().substring(0, 4) + " " +
                arc.getRoute().getTransportType() + " " + arc.getRoute().getShortName() + " from " + 
                vertexSource.getNodeName() + " " + convertSecondsToHours(currentTime[graph.getVertexIndex(vertexSource)]) + " to ");
            }

            if (i == importantArcs.size() - 1){ // arrive vers la destination
                res.add(vertexDestination.getNodeName() + " " + convertSecondsToHours(currentTime[graph.getVertexIndex(vertexDestination)]) + "\n");
                break;
            }

            i ++;
        }

        return res;
    }

    private String convertSecondsToHours(double seconds){
        int totalSeconds = (int) Math.round(seconds);
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;

        return hours + ":" + minutes + ":" + secs;
    }
}
