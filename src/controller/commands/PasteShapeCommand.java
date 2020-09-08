package controller.commands;

import controller.IUndoable;
import model.ShapeDetails;
import model.interfaces.IShapeDrawer;
import model.persistence.ApplicationState;
import model.persistence.ShapeList;
import model.shapeStrategy.ShapeFactory;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class PasteShapeCommand implements ICommand, IUndoable {
    private ShapeList shapeList;
    private ShapeDetails shapeDetails;
    private PaintCanvasBase paintCanvas;
    private ApplicationState applicationState;
    private ShapeFactory shapeFactory;
    private IShapeDrawer newShape;
    private ArrayList<IShapeDrawer> pastedShapeList = new ArrayList<IShapeDrawer>();

    public PasteShapeCommand(ShapeList shapeList, ShapeDetails shapeDetails, PaintCanvasBase paintCanvas, ApplicationState applicationState, ShapeFactory shapeFactory) {
        this.shapeList = shapeList;
        this.shapeDetails = shapeDetails;
        this.paintCanvas = paintCanvas;
        this.applicationState = applicationState;
        this.shapeFactory = shapeFactory;
    }

    public void copyOldShape(IShapeDrawer oldShape){
        shapeFactory.create(oldShape.getShapeDetails(), paintCanvas);
        newShape = shapeFactory.createShape();
    }


    @Override
    public void execute() {
        for (IShapeDrawer copiedShape : shapeList.getCopiedList()) {
            // make new shape using the original shape's details
            copyOldShape(copiedShape);
            // new shape just changes position slightly
            newShape.addDeltaX(200);
            // by adding, the new shape gets drawn
            shapeList.add(newShape);
            // save the shape for undo and redo
            pastedShapeList.add(newShape);
        }
        CommandHistory.add(this);
    }

    @Override
    public void redo() {
        // adds the selected shape that was just undone back to the canvas
        for (IShapeDrawer pastedShape : pastedShapeList){
            shapeList.add(pastedShape);
        }
    }

    @Override
    public void undo() {
        // newly pasted shape gets removed from canvas
        for (IShapeDrawer newShape : pastedShapeList){
            shapeList.remove(newShape);
        }
    }
}
