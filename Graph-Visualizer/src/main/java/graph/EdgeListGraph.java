package graph;

import api.DrawingApi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EdgeListGraph extends Graph {
    private static long R = 30;
    private List<Integer>[] graph;
    private double x;
    private double y;

    public EdgeListGraph(DrawingApi drawingApi) {
        super(drawingApi);
        x = drawingApi.getDrawingAreaWidth() / 2.;
        y = drawingApi.getDrawingAreaHeight() / 2.;
    }

    /**
     * First line contains n - count of vertices in graph.
     * Id of vertices starts from 0. Each of next lines
     * describes one edge.
     */
    @Override
    public void readGraph(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(reader.readLine());
        graph = (List<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            String[] vs = line.split(" ");
            if (vs.length != 2) {
                throw new IllegalArgumentException("Incorrect format of edge list: string\'" + line + "\'");
            }
            int u = Integer.parseInt(vs[0]);
            int v = Integer.parseInt(vs[1]);
            graph[u].add(v);
            graph[v].add(u);
        }
        reader.close();
    }

    @Override
    public void drawGraph() {
        int n = graph.length;
        double[] xs = new double[n];
        double[] ys = new double[n];
        calculateVerticesPositions(n, x, y, xs, ys, 100);
        for (int i = 0; i < n; i++) {
            drawingApi.drawCircle(xs[i], ys[i], R);
        }
        for (int u = 0; u < n; u++) {
            for (int v : graph[u]) {
                drawingApi.drawLine(xs[u] + R / 2., ys[u] + R / 2., xs[v] + R / 2., ys[v] + R / 2.);
            }
        }
        drawingApi.showGraph();
    }
}
