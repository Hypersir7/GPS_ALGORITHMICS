package graphs;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Arrow extends Group {
    private final Line line;
    private final Polygon arrowHead;

    public Arrow(double startX, double startY, double endX, double endY) {
        line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.BLACK);

        double arrowLength = 10;
        double arrowWidth = 4;

        double dx = endX - startX;
        double dy = endY - startY;
        double angle = Math.atan2(dy, dx);

        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        // The points of the arrowhead
        double x1 = endX - arrowLength * cos + arrowWidth * sin;
        double y1 = endY - arrowLength * sin - arrowWidth * cos;

        double x2 = endX - arrowLength * cos - arrowWidth * sin;
        double y2 = endY - arrowLength * sin + arrowWidth * cos;

        arrowHead = new Polygon(endX, endY, x1, y1, x2, y2);
        arrowHead.setFill(Color.BLACK);

        getChildren().addAll(line, arrowHead);
    }
}
