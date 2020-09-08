package model.shapeStrategy;

import model.ShapeDetails;
import model.ShapeType;
import model.interfaces.IFactory;
import model.interfaces.IShapeDrawer;
import model.persistence.ShapeList;
import view.interfaces.PaintCanvasBase;

public class ShapeFactory implements IFactory {
    private PaintCanvasBase paintCanvas;
    private IShapeDrawer shape;
    private ShapeList shapeList;

    public ShapeFactory(PaintCanvasBase paintCanvas, ShapeList shapeList){
        this.paintCanvas = paintCanvas;
        this.shapeList = shapeList;
    }


    public void create(ShapeDetails shapeDetails, PaintCanvasBase paintCanvas){

        // use the ShapeType to find which shape to make
        ShapeType shapeTypeBuild = shapeDetails.getShapeType();
        
        //IShapeDrawer shape;
        
        switch (shapeTypeBuild) {
            case ELLIPSE:
                System.out.println("Shape factory is making an ellipse!");
                this.shape = new DrawElipseStrategy(shapeDetails, paintCanvas);
                break;
            case RECTANGLE:
                System.out.println("Shape factory is making a rectangle!");
                this.shape = new DrawRectangleStrategy(shapeDetails, paintCanvas);
                break;
            case TRIANGLE:
                System.out.println("Shape factory is making a rectangle!");
                this.shape = new DrawTriangleStrategy(shapeDetails, paintCanvas);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + shapeTypeBuild);
        }


        //return shape;
        shapeList.notifyObserver();

    }

    public IShapeDrawer createShape(){
        return this.shape;
    }


}
