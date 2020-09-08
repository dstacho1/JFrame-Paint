package model;

public class SelectionBox {
    public Point startPoint;
    public Point endPoint;
    public int height;
    public int width;
    public int x;
    public int y;
    public int maxX;
    public int maxY;
    public int midX;
    public int midY;


    public SelectionBox(Point startPoint, Point endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.x = Math.min(startPoint.getX(), endPoint.getX());
        this.y = Math.min(startPoint.getY(), endPoint.getY());
        this.height = Math.abs(startPoint.getY() - endPoint.getY());
        this.width = Math.abs(startPoint.getX() - endPoint.getX());

        this.maxX = Math.max(startPoint.getX(), endPoint.getX());
        this.maxY = Math.max(startPoint.getY(), endPoint.getY());
        this.midX = this.x + ((this.maxX-this.x)/2);
        this.midY = this.y + ((this.maxY-this.y)/2);
    }
}
