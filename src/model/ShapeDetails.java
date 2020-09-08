package model;

// this class provides setters and getters for the fields needed to draw a shape

public class ShapeDetails {
    private Point startPoint;
    private Point endPoint;
    private ShapeShadingType shapeShadingType;
    private ShapeType shapeType;
    private ShapeColor primaryShapeColor;
    private ShapeColor secondaryShapeColor;

    // select which shape
    public void setShapeType(ShapeType shapeType){
        this.shapeType = shapeType;
    }
    public ShapeType getShapeType(){
        return shapeType;
    }


    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
    public Point getEndPoint() {
        return endPoint;
    }


    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
    public Point getStartPoint() {
        return startPoint;
    }


    public void setStartX(int startX) {
        startPoint.setX(startX);
    }
    public int getStartX() {
        return startPoint.getX();
    }


    public void setStartY(int startY) {
        startPoint.setY(startY);
    }
    public int getStartY() {
        return startPoint.getY();
    }


    public void setEndX(int endX) {
        endPoint.setX(endX);
    }
    public int getEndX() {
        return endPoint.getX();
    }


    public void setEndY(int endY) {
        endPoint.setY(endY);
    }
    public int getEndY() {
        return endPoint.getY();
    }


    public void setShapeShadingType(ShapeShadingType shapeShadingType){
        this.shapeShadingType = shapeShadingType;
    }
    public ShapeShadingType getShapeShadingType(){
        return shapeShadingType;
    }


    public void setPrimaryShapeColor(ShapeColor primaryShapeColor){
        this.primaryShapeColor = primaryShapeColor;
    }
    public ShapeColor getPrimaryShapeColor(){
        return primaryShapeColor;
    }


    public void setSecondaryShapeColor(ShapeColor secondaryShapeColor){
        this.secondaryShapeColor = secondaryShapeColor;
    }
    public ShapeColor getSecondaryShapeColor(){
        return secondaryShapeColor;
    }


    public int getX(){
        int x = Math.min(startPoint.getX(), endPoint.getX());
        return x;
    }
    public int getY(){
        int y = Math.min(startPoint.getY(), endPoint.getY());
        return y;
    }


    public int getWidth(){
        int width = Math.abs(startPoint.getX() - endPoint.getX());
        return width;
    }
    public int getHeight(){
        int height = Math.abs(startPoint.getY() - endPoint.getY());
        return height;
    }


    public int getMaxX(){
        int maxX = Math.max(startPoint.getX(), endPoint.getX());
        return maxX;
    }
    public int getMaxY(){
        int maxY = Math.max(startPoint.getY(), endPoint.getY());
        return maxY;
    }

    // idea to find mid points taken from https://www.geeksforgeeks.org/program-find-mid-point-line/
    public int getMidX(){
        int midX = getX() + ((getMaxX()-getX())/2);
        return midX;
    }
    public int getMidY(){
        int midY = getY() + ((getMaxY()-getY())/2);
        return midY;
    }
}


