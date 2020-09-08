package model;

import model.interfaces.IShapeDrawer;
import view.interfaces.PaintCanvasBase;
import java.awt.*;

public class DrawTriangleBorder extends BorderShape {
    private PaintCanvasBase paintCanvas;
    private ShapeDetails shapeDetails;
    private int[] xPoints;
    private int[] yPoints;

    public DrawTriangleBorder(IShapeDrawer borderShape, PaintCanvasBase paintCanvas, ShapeDetails shapeDetails) {
        super(borderShape, paintCanvas, shapeDetails);
        this.borderShape = borderShape;
        this.paintCanvas = paintCanvas;
        this.shapeDetails = shapeDetails;
        this.xPoints = new int[]{shapeDetails.getStartX()-5, shapeDetails.getEndX()+5, shapeDetails.getStartX()-5};
        this.yPoints = new int[]{shapeDetails.getStartY()-5, shapeDetails.getEndY()+5, shapeDetails.getEndY()+5};
    }


    @Override
    public void draw(){
        borderShape.draw();

        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawPolygon(xPoints, yPoints, 3);
        graphics2d.dispose();
    }

    @Override
    public boolean contains(Point selectedPoint) {
        return false;
    }

    @Override
    public boolean intersects(SelectionBox selectionBox) {
        return false;
    }

    @Override
    public void addDeltaX(int deltaX) {

    }

    @Override
    public void addDeltaY(int deltaY) {

    }

    @Override
    public ShapeDetails getShapeDetails() {
        return null;
    }

    @Override
    public boolean getSelected() {
        return false;
    }

    @Override
    public void setSelected(boolean status) {

    }

    @Override
    public boolean isGroupObj() {
        return false;
    }
}
