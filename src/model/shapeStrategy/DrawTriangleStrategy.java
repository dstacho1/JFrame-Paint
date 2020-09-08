package model.shapeStrategy;

import model.*;
import model.Point;
import model.interfaces.IShapeDrawer;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class DrawTriangleStrategy implements IShapeDrawer {
    private PaintCanvasBase paintCanvas;
    private ShapeDetails shapeDetails;
    private ShapeShadingType shapeShadingType;
    private Color primaryShapeColor;
    private Color secondaryShapeColor;

    private int[] xPoints;
    private int[] yPoints;
    private int nPoints;
    private Point startPoint;
    private Point endPoint;
    private int midX;
    private int midY;
    private int width;
    private int height;

    public boolean selected;
    private boolean isGroupObj;

    public DrawTriangleStrategy(ShapeDetails shapeDetails, PaintCanvasBase paintCanvas){
        this.shapeDetails = shapeDetails;
        nPoints = 3;
        this.xPoints = new int[]{shapeDetails.getStartX(), shapeDetails.getEndX(), shapeDetails.getStartX()};
        this.yPoints = new int[]{shapeDetails.getStartY(), shapeDetails.getEndY(), shapeDetails.getEndY()};

        // same as other shapes
        this.startPoint = shapeDetails.getStartPoint();
        this.endPoint = shapeDetails.getEndPoint();
        this.shapeShadingType = shapeDetails.getShapeShadingType();
        this.primaryShapeColor = ColorChooser.getShapeColor(shapeDetails.getPrimaryShapeColor());
        this.secondaryShapeColor = ColorChooser.getShapeColor(shapeDetails.getSecondaryShapeColor());
        this.paintCanvas = paintCanvas;

        this.midX = shapeDetails.getMidX();
        this.midY = shapeDetails.getMidY();
        this.width = shapeDetails.getWidth();
        this.height = shapeDetails.getHeight();

        this.selected = false;
        this.isGroupObj = false;
    }

    @Override
    public void draw() {
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setStroke(new BasicStroke(3));
        if (shapeShadingType.equals(ShapeShadingType.FILLED_IN)){
            graphics2d.setColor(secondaryShapeColor);
            graphics2d.fillPolygon(xPoints, yPoints, nPoints);
        }
        else if (shapeShadingType.equals(ShapeShadingType.OUTLINE)){
            graphics2d.setColor(primaryShapeColor);
            graphics2d.drawPolygon(xPoints, yPoints, nPoints);
        }
        else{
            // outline and filled in
            graphics2d.setColor(primaryShapeColor);
            graphics2d.drawPolygon(xPoints, yPoints, nPoints);
            graphics2d.setColor(secondaryShapeColor);
            graphics2d.fillPolygon(xPoints, yPoints, nPoints);
        }
    }


    @Override
    public boolean getSelected(){
        return this.selected;
    }

    @Override
    public void setSelected(boolean status){
        this.selected = status;
    }

    /*
        Functions to check whether my selectedPoint lies inside the triangle
        Found on https://www.geeksforgeeks.org/check-whether-a-given-point-lies-inside-a-triangle-or-not/
     */
    static double area(int x1, int y1, int x2, int y2,
                       int x3, int y3) {
        return Math.abs((x1*(y2-y3) + x2*(y3-y1)+
                x3*(y1-y2))/2.0);
    }
    static boolean isInside(int x1, int y1, int x2,
                            int y2, int x3, int y3, int x, int y)
    {
        /* Calculate area of triangle ABC */
        double A = area (x1, y1, x2, y2, x3, y3);

        /* Calculate area of triangle PBC */
        double A1 = area (x, y, x2, y2, x3, y3);

        /* Calculate area of triangle PAC */
        double A2 = area (x1, y1, x, y, x3, y3);

        /* Calculate area of triangle PAB */
        double A3 = area (x1, y1, x2, y2, x, y);

        /* Check if sum of A1, A2 and A3 is same as A */
        return (A == A1 + A2 + A3);
    }

    @Override
    public boolean contains(Point selectedPoint) {
        if (isInside(xPoints[0], yPoints[0], xPoints[1], yPoints[1], xPoints[2], yPoints[2], selectedPoint.getX(), selectedPoint.getY())){
            System.out.println("Contains is true");
            return true;
        }
        else{
            System.out.println("Contains is false");
            return false;
        }
    }

    @Override
    public void addDeltaX(int deltaX) {
        this.xPoints[0] = startPoint.getX() + deltaX;
        this.xPoints[1] = endPoint.getX() + deltaX;
        this.xPoints[2] = startPoint.getX() + deltaX;
    }

    @Override
    public void addDeltaY(int deltaY) {
        this.yPoints[0] = startPoint.getY() + deltaY;
        this.yPoints[1] = endPoint.getY() + deltaY;
        this.yPoints[2] = endPoint.getY() + deltaY;
    }

    @Override
    public ShapeDetails getShapeDetails() {
        return shapeDetails;
    }

    // helped in understanding how mid points work for triangle shapes https://stackoverflow.com/questions/524755/finding-center-of-2d-triangle
    @Override
    public boolean intersects(SelectionBox selectionBox) {
        return Math.abs(this.midX - selectionBox.midX) < (Math.abs(this.width + selectionBox.width) / 2)
                && (Math.abs(this.midY - selectionBox.midY) < (Math.abs(this.height + selectionBox.height) / 2));
    }

    @Override
    public boolean isGroupObj(){
        return isGroupObj;
    }
}
