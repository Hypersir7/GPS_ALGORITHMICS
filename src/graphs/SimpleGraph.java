package graphs;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Point2D;

public class SimpleGraph extends Application {
    private static Graph graph;
    private double initialX, initialY;

    public static void setGraph(Graph g) {
        graph = g;
    }

    public static void draw(){
        launch();
    }

    private Point2D calculateArrowEndPoint(double x1, double y1, double x2, double y2, double radius){
        if (x1 == x2){
            double y = y2 - radius;
            double second_y = y2 + radius;
            double distance_1 = Math.sqrt(Math.pow(y - y1, 2));
            double distance_2 = Math.sqrt(Math.pow(second_y - y1, 2));
            if (distance_1 <= distance_2){
                return new Point2D(x1, y);
            }else{
                return new Point2D(x1, second_y);
            }
        }
        if (y1 == y2) {
            double x = x2 - radius;
            double second_x = x2 + radius;
            double distance_1 = Math.sqrt(Math.pow(x - x1, 2));
            double distance_2 = Math.sqrt(Math.pow(second_x - x1, 2));
            if (distance_1 <= distance_2){
                return new Point2D(x, y1);
            }else{
                return new Point2D(second_x, y1);
            }
        }

        double alpha = (y2 - y1) / (x2 - x1);
        double beta = y1 - alpha * x1;
        double a = alpha * alpha + 1;
        double b = 2 * alpha * (beta - y2) - 2 * x2;
        double c = x2 * x2 + (beta - y2) * (beta - y2) - radius * radius;

        double delta = b * b - 4 * a * c;
        if (delta < 0){return new Point2D(0, 0);}
        else if (delta == 0){
            double x = -b / (2 * a);
            double y = alpha * x + beta;
            return new Point2D(x, y);
        }else {
            double x = (-b + Math.sqrt(delta)) / (2 * a);
            double y = alpha * x + beta;

            double second_x = (-b - Math.sqrt(delta)) / (2 * a);
            double second_y = alpha * second_x + beta;

            double distance_1 = Math.sqrt((Math.pow(x - x1, 2) + Math.pow(y - y1, 2)));
            double distance_2 = Math.sqrt((Math.pow(second_x - x1, 2) + Math.pow(second_y - y1, 2)));
            if (distance_1 <= distance_2){
                return new Point2D(x, y);
            }else{
                return new Point2D(second_x, second_y);
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Group graphGroup = new Group();

        ArrayList<Node> vertices = graph.getVertices();
        ArrayList<Circle> circles = new ArrayList<>();
        ArrayList<Arrow> arrows = new ArrayList<>();
        ArrayList<Text> texts = new ArrayList<>();

        double circlesRadius = 10;
        
        for (int i = 0; i < vertices.size(); i++){
            double x = vertices.get(i).getX();
            double y = vertices.get(i).getY();

            Circle node = new Circle(x, y, circlesRadius, Color.LIGHTGREEN);
            circles.add(node);
            Text txt = new Text(x + circlesRadius, y - circlesRadius, vertices.get(i).getNodeName());
            texts.add(txt);
            txt.setOpacity(0.2);
            txt.setMouseTransparent(true);
            

            node.setOnMouseEntered(event -> {
                node.setFill(Color.LIGHTCYAN); // quand la souris passe au dessus
                txt.setOpacity(1);
                txt.setFont(new Font(16));
                Glow glow = new Glow(0.8);
                txt.setEffect(glow);

            });
            node.setOnMouseExited(event -> {
                node.setFill(Color.LIGHTGREEN);
                txt.setOpacity(0.2);
                txt.setFont(new Font(12));
                txt.setEffect(null);
            });

            //
            ArrayList<Arc> neighbours = vertices.get(i).getNeighbours();

            for (int j = 0; j < neighbours.size(); j++){
                if (neighbours.get(j).isSelected()){
                    Node neighbour = neighbours.get(j).getDestination();

                    double n_x = neighbour.getX();
                    double n_y = neighbour.getY();
                    Point2D arrowEndPoint = calculateArrowEndPoint(x, y, n_x, n_y, circlesRadius);
                    arrows.add(new Arrow(x, y, arrowEndPoint.getX(), arrowEndPoint.getY(), Color.RED));
                }
                
            }
            
        }
        
        graphGroup.getChildren().addAll(arrows);
        graphGroup.getChildren().addAll(circles);
        graphGroup.getChildren().addAll(texts);
        pane.getChildren().add(graphGroup);
        

        // Event handling for dragging
        pane.setOnMousePressed(event -> {
            // Store the initial position of the mouse press
            initialX = event.getSceneX();
            initialY = event.getSceneY();
        });

        pane.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - initialX;
            double deltaY = event.getSceneY() - initialY;

            graphGroup.setTranslateX(graphGroup.getTranslateX() + deltaX);
            graphGroup.setTranslateY(graphGroup.getTranslateY() + deltaY);

            initialX = event.getSceneX();
            initialY = event.getSceneY();
        });

        // zoom in and out

        final double SCALE_DELTA = 1.1;
        final double MIN_SCALE = 0.15;
        final double MAX_SCALE = 4.0;

        pane.setOnScroll(event -> {
            double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;
            double newScale = graphGroup.getScaleX() * scaleFactor;

            if (newScale < MIN_SCALE || newScale > MAX_SCALE) return;

            // Zoom toward pointer
            double mouseX = event.getX();
            double mouseY = event.getY();
            double dx = mouseX - graphGroup.getBoundsInParent().getMinX();
            double dy = mouseY - graphGroup.getBoundsInParent().getMinY();

            graphGroup.setScaleX(newScale);
            graphGroup.setScaleY(newScale);

            graphGroup.setTranslateX(graphGroup.getTranslateX() - dx * (scaleFactor - 1));
            graphGroup.setTranslateY(graphGroup.getTranslateY() - dy * (scaleFactor - 1));
            event.consume();
        });

        // Create the scene and add it to the stage
        Scene scene = new Scene(pane, 900, 700);
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case R -> {
                    graphGroup.setScaleX(1.0);
                    graphGroup.setScaleY(1.0);
                    graphGroup.setTranslateX(0);
                    graphGroup.setTranslateY(0);
                }
            }
        });

        primaryStage.setTitle("Draggable Graph");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
