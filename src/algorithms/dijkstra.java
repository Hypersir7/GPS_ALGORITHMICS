package algorithms;

import graphs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;


public class dijkstra {

    public class BestArc {
        public double bestTime;
        public Arc bestArc;
        public BestArc(double newBestTime, Arc newBestArc){
            this.bestTime = newBestTime;
            this.bestArc = newBestArc;
        }
    }

    public ArrayList<String> getShortestPath(Graph graph, Node source, Node destination, double departureTime){
        ArrayList<String> res = new ArrayList<>();

        // tout d'abord on trouve le plus court chemin et apres on track back pour donner le plus court chemin

        ArrayList<Node> vertices = graph.getVertices();
        int sourceIndex = graph.getVertexIndex(source);
        int destinationIndex = graph.getVertexIndex(destination);

        boolean [] isVisited = new boolean[vertices.size()];
        double [] distanceTo = new double[vertices.size()];
        double [] currentTime = new double[vertices.size()];
        int [] previusVertex = new int[vertices.size()];
        Arrays.fill(distanceTo, Integer.MAX_VALUE);
        Arrays.fill(currentTime, departureTime);
        Arrays.fill(previusVertex, -1);

        distanceTo[sourceIndex] = 0 ;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingDouble(i -> distanceTo[i]));
        pq.add(sourceIndex);

        while (!pq.isEmpty()) {
            int idx = pq.poll();
            if (idx == destinationIndex){break;}
            for (Arc arc : graph.getVertex(idx).getNeighbours()){
                int v = graph.getVertexIndex(arc.getDestination());
                if (isVisited[v]){continue;}
                double waitingTimeTilDeparture = arc.getDepartureTimeInSeconds() - currentTime[v];
                if (waitingTimeTilDeparture < 0){continue;}
                double weight = waitingTimeTilDeparture + arc.getWeight();
                if (distanceTo[idx] + weight < distanceTo[v]){
                    distanceTo[v] = distanceTo[idx] + weight;
                    currentTime[v] += currentTime[idx] + weight;
                    previusVertex[v] = idx;
                    arc.select(); // debug
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
        int currentNode = destinationIndex;
        while (previusVertex[currentNode] != -1) {
            res.add(", " + graph.getVertex(currentNode).getNodeName() + "(" + convertSecondsToHours(currentTime[currentNode]) + ")");
            currentNode = previusVertex[currentNode];
        }
        res.add(source.getNodeName() + "(" + convertSecondsToHours(currentTime[currentNode]) + ")");
        Collections.reverse(res);
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
