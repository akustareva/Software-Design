package api;

public interface DrawingApi {
    long getDrawingAreaWidth();
    long getDrawingAreaHeight();
    void drawCircle(double x, double y, long r);
    void drawLine(double x1, double y1, double x2, double y2);
    void showGraph();
}
