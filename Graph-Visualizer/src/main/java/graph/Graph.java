package graph;

import api.DrawingApi;

import java.io.IOException;

public abstract class Graph {
    protected DrawingApi drawingApi;

    protected Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
    }

    public abstract void readGraph(String file) throws IOException;
    public abstract void drawGraph();

    protected void calculateVerticesPositions(int n, double x, double y, double[] xs, double[] ys, int d) {
        for (int i = 0; i < n; i++) {
            double c = (2 * Math.PI / n) * i;
            xs[i] = x + (Math.cos(c) * d);
            ys[i] = y + (Math.sin(c) * d);
        }
    }
}
