package graphs;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SimpleGraph extends Application {
    private static Graph graph;
    private double initialX, initialY;
    //private double offsetX = 0, offsetY = 0;

    public static void setGraph(Graph g) {
        graph = g;
    }

    public static void draw(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        ArrayList<Node> vertices = graph.getVertices();
        ArrayList<Circle> circles = new ArrayList<>();
        ArrayList<Line> lines = new ArrayList<>();
        ArrayList<Text> texts = new ArrayList<>();
        //boolean[] isDrawn = new boolean[vertices.size()];

        for (int i = 0; i < vertices.size(); i++){
            double x = vertices.get(i).getX();
            double y = vertices.get(i).getY();
            circles.add(new Circle(x, y, 10, Color.LIGHTBLUE));
            texts.add(new Text(x, y, vertices.get(i).getNodeName()));
            //isDrawn[i] = true;
            ArrayList<Arc> neighbours = vertices.get(i).getNeighbours();

            for (int j = 0; j < neighbours.size(); j++){
                Node neighbour = neighbours.get(j).getDestination();

                double n_x = neighbour.getX();
                double n_y = neighbour.getY();
                lines.add(new Line(x, y, n_x, n_y));
                
            }
            
        }
        

        // Create nodes and edges for the graph
        // Circle node1 = new Circle(100, 100, 20, Color.LIGHTBLUE);
        // Circle node2 = new Circle(200, 150, 20, Color.LIGHTGREEN);
        // Circle node3 = new Circle(150, 250, 20, Color.LIGHTPINK);

        // Line edge1 = new Line(node1.getCenterX(), node1.getCenterY(), node2.getCenterX(), node2.getCenterY());
        // Line edge2 = new Line(node2.getCenterX(), node2.getCenterY(), node3.getCenterX(), node3.getCenterY());
        // Line edge3 = new Line(node3.getCenterX(), node3.getCenterY(), node1.getCenterX(), node1.getCenterY());

        // Add all nodes and edges to the pane
        //pane.getChildren().addAll(edge1, edge2, edge3, node1, node2, node3);
        pane.getChildren().addAll(lines);
        pane.getChildren().addAll(circles);
        pane.getChildren().addAll(texts);
        

        // Event handling for dragging
        pane.setOnMousePressed(event -> {
            // Store the initial position of the mouse press
            initialX = event.getSceneX();
            initialY = event.getSceneY();
        });

        pane.setOnMouseDragged(event -> {
            // Calculate how far the mouse has moved
            double deltaX = event.getSceneX() - initialX;
            double deltaY = event.getSceneY() - initialY;

            // Move all nodes and edges
            for (javafx.scene.Node node : pane.getChildren()) {
                node.setTranslateX(node.getTranslateX() + deltaX);
                node.setTranslateY(node.getTranslateY() + deltaY);
            }

            // Update the initial positions for the next drag event
            initialX = event.getSceneX();
            initialY = event.getSceneY();
        });

        // Create the scene and add it to the stage
        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setTitle("Draggable Graph");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
