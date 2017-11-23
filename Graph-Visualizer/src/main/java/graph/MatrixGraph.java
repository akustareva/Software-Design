package graph;

import api.DrawingApi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MatrixGraph extends Graph {
    private static long R = 30;
    private boolean[][] matrix;
    private double x;
    private double y;

    public MatrixGraph(DrawingApi drawingApi) {
        super(drawingApi);
        x = drawingApi.getDrawingAreaWidth() / 2.;
        y = drawingApi.getDrawingAreaHeight() / 2.;
    }

    /**
     * First line contains n - count of vertices in graph.
     * Id of vertices starts from 0. In the next lines
     * adjacency matrix is presented.
     */
    @Override
    public void readGraph(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(reader.readLine());
        matrix = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String line = reader.readLine();
            String[] vs = line.split(" ");
            if (vs.length != n) {
                throw new IllegalArgumentException("Incorrect format of adjacency matrix: string\'" + line + "\'");
            }
            for (int j = 0; j < n; j++) {
                matrix[i][j] = "1".equals(vs[j]) || "true".equals(vs[j]);
            }
        }
        reader.close();
    }

    @Override
    public void drawGraph() {
        int n = matrix.length;
        double[] xs = new double[n];
        double[] ys = new double[n];
        calculateVerticesPositions(n, x, y, xs, ys, 100);
        for (int i = 0; i < n; i++) {
            drawingApi.drawCircle(xs[i], ys[i], R);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]) {
                    drawingApi.drawLine(xs[i] + R / 2., ys[i] + R / 2., xs[j] + R / 2., ys[j] + R / 2.);
                }
            }
        }
        drawingApi.showGraph();
    }
}
