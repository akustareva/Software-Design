import api.AwtDrawingApi;
import api.DrawingApi;
import api.JavaFxDrawingApi;
import graph.EdgeListGraph;
import graph.Graph;
import graph.MatrixGraph;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println(getMessage());
        }
        String apiLabel = args[0];
        String formatLabel = args[1];
        String filename = args[2];
        DrawingApi api;
        if ("awt".equals(apiLabel)) {
            api = new AwtDrawingApi();
        } else if ("fx".equals(apiLabel)) {
            api = new JavaFxDrawingApi();
        } else {
            throw new IllegalArgumentException("Unknown drawing api.");
        }
        Graph graph;
        if ("edl".equals(formatLabel)) {
            graph = new EdgeListGraph(api);
        } else if ("mtx".equals(formatLabel)) {
            graph = new MatrixGraph(api);
        } else {
            throw new IllegalArgumentException("Unknown format.");
        }
        graph.readGraph(filename);
        graph.drawGraph();
    }

    private static String getMessage() {
        return "Please specify drawing api, way to represent graph and input file name.\n" +
                "Available drawing apis: \n" +
                    "\tawt - java.awt\n" +
                    "\tfx  - javafx\n" +
                "Available formats: \n" +
                    "\tedl - Edge lists\n" +
                    "\tmtx - Adjacency matrices\n";
    }
}
