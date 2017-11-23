package api;

import graph.detail.Circle;
import graph.detail.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class AwtDrawingApi extends JFrame implements DrawingApi {
    private static List<Line> lines = new ArrayList<>();
    private static List<Circle> circles = new ArrayList<>();
    private static int WIDTH = 600;
    private static int HEIGHT = 400;

    public AwtDrawingApi() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(false);
        setSize(WIDTH, HEIGHT);
        setTitle("Drawing graph with Awt");
    }

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
        repaint();
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Circle circle : circles) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLACK);
            g2.fill(new Ellipse2D.Double(circle.getX(), circle.getY(), circle.getR(), circle.getR()));
        }
        for (Line line : lines) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLACK);
            g2.draw(new Line2D.Double(line.getX1(), line.getY1(), line.getX2(), line.getY2()));
        }
    }
}
