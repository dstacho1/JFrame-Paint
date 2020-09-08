package model;

import model.interfaces.IShapeDrawer;
import view.interfaces.PaintCanvasBase;
import static model.ShapeType.*;

public class ShapeBorderSelector {
    private ShapeType shapeTypeBuild;
    private IShapeDrawer shape;
    private PaintCanvasBase paintCanvas;
    private ShapeDetails shapeDetails;

    public ShapeBorderSelector(ShapeType shapeTypeBuild, IShapeDrawer shape, PaintCanvasBase paintCanvas, ShapeDetails shapeDetails){
        this.shapeTypeBuild = shapeTypeBuild;
        this.shape = shape;
        this.paintCanvas = paintCanvas;
        this.shapeDetails = shapeDetails;
    }

    public void drawShapeBorder(){
        if (shapeTypeBuild.equals(ELLIPSE)){
            IShapeDrawer elipseWithBorder = new DrawElipseBorder(shape, paintCanvas, shape.getShapeDetails());
            elipseWithBorder.draw();
            shape.setSelected(false); // allows the shape to be reselected and draw a border again
        }
        else if (shapeTypeBuild.equals(RECTANGLE)){
            IShapeDrawer rectangleWithBorder = new DrawRectangleBorder(shape, paintCanvas, shape.getShapeDetails());
            rectangleWithBorder.draw();
            shape.setSelected(false);
        }
        else if (shapeTypeBuild.equals(TRIANGLE)){
            IShapeDrawer triangleWithBorder = new DrawTriangleBorder(shape, paintCanvas, shape.getShapeDetails());
            triangleWithBorder.draw();
            shape.setSelected(false);
        }
    }
}