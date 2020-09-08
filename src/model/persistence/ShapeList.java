package model.persistence;

import model.interfaces.IShapeDrawer;
import model.interfaces.ShapeListSubject;
import view.gui.PaintCanvas;
import java.awt.*;
import java.util.ArrayList;

public class ShapeList implements ShapeListSubject {
    private ArrayList<IShapeDrawer> shapeList;
    private ArrayList<IShapeDrawer> selectedShapeList;
    private ArrayList<IShapeDrawer> copiedList;
    private PaintCanvas paintCanvas;

    public ShapeList(){
        shapeList = new ArrayList<IShapeDrawer>();
        selectedShapeList = new ArrayList<IShapeDrawer>();
        copiedList = new ArrayList<IShapeDrawer>();

    }

    public void add(IShapeDrawer shape){
        System.out.println("ShapeList is adding: " + shape);
        shapeList.add(shape);
        notifyObserver();
    }

    @Override
    public void register(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    // update the list anytime a new shape is added or removed to/from that list
    @Override
    public void notifyObserver() {
        // idea taken from Tyler Reed's post on the discussion board.
        paintCanvas.getGraphics2D().setColor(Color.white);
        paintCanvas.getGraphics2D().clearRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());
        paintCanvas.update(shapeList);
    }

    public void remove(IShapeDrawer shape){
        shapeList.remove(shape);
        notifyObserver();
    }


    public ArrayList<IShapeDrawer> getShapeList(){
        return shapeList;
    }

    public IShapeDrawer getLastSelectedShape(){
        return shapeList.get(shapeList.size() - 1);
    }

    public ArrayList<IShapeDrawer> getSelectedShapeList(){return selectedShapeList;}

    public void addToSelectedShapeList(IShapeDrawer shape){
        selectedShapeList.add(shape);
    }

    public void DeleteEverythingSelectedShapeList(){selectedShapeList.clear();}

    public void addToCopiedList(IShapeDrawer selectedShape) {
        copiedList.add(selectedShape);
    }

    public ArrayList<IShapeDrawer> getCopiedList(){return copiedList;}
}
