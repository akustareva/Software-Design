package api;

import graph.detail.Circle;
import graph.detail.Line;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class JavaFxDrawingApi extends Application implements DrawingApi {
    private static List<Line> lines = new ArrayList<>();
    private static List<Circle> circles = new ArrayList<>();
    private static long WIDTH = 600;
    private static long HEIGHT = 400;

    public JavaFxDrawingApi() {}

    @Override
    public long getDrawingAreaWidth() {
        return WIDTH;
    }

    @Override
    public long getDrawingAreaHeight() {
        return HEIGHT;
    }

    @Override
    public void drawCircle(double x, double y, long r) {
        circles.add(new Circle(x, y, r));
    }

    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        lines.add(new Line(x1, y1, x2, y2));
    }

    @Override
    public void showGraph() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Drawing graph with JavaFx");
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        for (Circle circle : circles) {
            gc.fillOval(circle.getX(), circle.getY(), circle.getR(), circle.getR());
        }
        for (Line line : lines) {
            gc.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
        }
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
