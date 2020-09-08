package model.shapeStrategy;

import model.*;
import model.Point;
import model.interfaces.IShapeDrawer;
import view.interfaces.PaintCanvasBase;
import java.awt.*;

public class DrawElipseStrategy implements IShapeDrawer {

    private PaintCanvasBase paintCanvas;
    private ShapeDetails shapeDetails;
    private int x;
    private int y;
    private int width;
    private int height;
    private int midX;
    private int midY;

    private ShapeShadingType shapeShadingType;
    private Color primaryShapeColor;
    private Color secondaryShapeColor;
    private model.Point startPoint;
    private model.Point endPoint;

    public boolean selected;
    private boolean isGroupObj;


    public DrawElipseStrategy(ShapeDetails shapeDetails, PaintCanvasBase paintCanvas){
        this.shapeDetails = shapeDetails;
        this.x = shapeDetails.getX();
        this.y = shapeDetails.getY();

        this.width = shapeDetails.getWidth();
        this.height = shapeDetails.getHeight();
        this.startPoint = shapeDetails.getStartPoint();
        this.endPoint = shapeDetails.getEndPoint();
        this.midX = shapeDetails.getMidX();
        this.midY = shapeDetails.getMidY();

        this.shapeShadingType = shapeDetails.getShapeShadingType();
        this.primaryShapeColor = ColorChooser.getShapeColor(shapeDetails.getPrimaryShapeColor());
        this.secondaryShapeColor = ColorChooser.getShapeColor(shapeDetails.getSecondaryShapeColor());
        this.paintCanvas = paintCanvas;

        this.selected = false;
        this.isGroupObj = false;
    }

    @Override
    public void draw() {
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setStroke(new BasicStroke(3));
        if (shapeShadingType.equals(ShapeShadingType.FILLED_IN)){
            graphics2d.setColor(secondaryShapeColor);
            graphics2d.fillOval(x, y, width, height);
        }
        else if (shapeShadingType.equals(ShapeShadingType.OUTLINE)){
            graphics2d.setColor(primaryShapeColor);
            graphics2d.drawOval(x, y, width, height);
        }
        else{
            // outline and filled in shape
            graphics2d.setColor(primaryShapeColor);
            graphics2d.drawOval(x, y, width, height);
            graphics2d.setColor(secondaryShapeColor);
            graphics2d.fillOval(x, y, width, height);
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

    @Override
    public boolean contains(Point selectedPoint) {
        if (startPoint.getX() < selectedPoint.getX() && startPoint.getY() < selectedPoint.getY()
                && startPoint.getX() + width > selectedPoint.getX() && startPoint.getY() + height > selectedPoint.getY()){
            System.out.println("Contains is true");
            return true;
        }
        else {
            System.out.println("Contains is false");
            return false;
        }
    }

    @Override
    public void addDeltaX(int deltaX) {
        startPoint.setX(startPoint.getX() + deltaX);
        endPoint.setX(endPoint.getX() + deltaX);
        this.x = shapeDetails.getX();
    }

    @Override
    public void addDeltaY(int deltaY) {
        startPoint.setY(startPoint.getY() + deltaY);
        endPoint.setY(endPoint.getY() + deltaY);
        this.y = shapeDetails.getY();
    }

    @Override
    public ShapeDetails getShapeDetails() {
        return shapeDetails;
    }

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
