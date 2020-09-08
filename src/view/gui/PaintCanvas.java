package view.gui;

import model.interfaces.IShapeDrawer;
import model.interfaces.ShapeListObserver;
import model.interfaces.ShapeListSubject;
import view.interfaces.PaintCanvasBase;
import java.awt.*;
import java.util.ArrayList;

public class PaintCanvas extends PaintCanvasBase implements ShapeListObserver {
    private ShapeListSubject shapeList;

    public PaintCanvas(ShapeListSubject shapeList){
        this.shapeList = shapeList;
        // register as an observer to get notified when changes occur
        shapeList.register(this);
    }

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }

    @Override
    public void update(ArrayList<IShapeDrawer> shapes) {
        // iterate through the list anytime its updated to redraw shapes on the canvas
        for (IShapeDrawer shape: shapes){
            shape.draw();
        }
        System.out.println("Size of shapeList: " + shapes.size());
    }
}
