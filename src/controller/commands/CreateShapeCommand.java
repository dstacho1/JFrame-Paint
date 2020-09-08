package controller.commands;

import controller.IUndoable;
import model.ShapeDetails;
import model.shapeStrategy.ShapeFactory;
import model.persistence.ShapeList;
import model.interfaces.IShapeDrawer;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class CreateShapeCommand implements ICommand, IUndoable {

    private final ShapeFactory shapeFactory;
    private ShapeList shapeList;
    private ShapeDetails shapeDetails;
    private IShapeDrawer shape;
    private PaintCanvasBase paintCanvas;
    private ApplicationState applicationState;

    public CreateShapeCommand(ShapeList shapeList, ShapeDetails shapeDetails, PaintCanvasBase paintCanvas, ApplicationState applicationState, ShapeFactory shapeFactory){
        this.applicationState = applicationState;
        this.shapeList = shapeList;
        this.shapeDetails = shapeDetails;
        this.paintCanvas = paintCanvas;
        this.shapeFactory = shapeFactory;
    }

    @Override
    public void execute() {
        System.out.println("CreateShapeCommand is executing correctly.");
        shapeDetails = applicationState.getRunningShapeDetails();

        // shapeFactory returns a shape object that is built using the shapeDetails information
        shapeFactory.create(shapeDetails, paintCanvas);
        shape = shapeFactory.createShape();

        // add the shape to a shapeList to be displayed
        shapeList.add(shape);
        CommandHistory.add(this);
    }

    @Override
    public void redo() {
        // add it back to list to draw the shape again
        shapeList.add(shape);
    }

    @Override
    public void undo() {
        // remove shape to delete it from canvas
        shapeList.remove(shape);
    }
}
