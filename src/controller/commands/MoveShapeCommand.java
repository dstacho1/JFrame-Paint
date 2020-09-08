package controller.commands;

import controller.IUndoable;
import model.*;
import model.interfaces.IShapeDrawer;
import model.persistence.ApplicationState;
import model.persistence.ShapeList;
import model.shapeStrategy.ShapeFactory;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class MoveShapeCommand implements ICommand, IUndoable {
    private ShapeList shapeList;
    private ShapeDetails shapeDetails;
    private IShapeDrawer shape;
    private IShapeDrawer shapeToMove;
    private PaintCanvasBase paintCanvas;
    private ApplicationState applicationState;
    private int deltaX, deltaY;
    private ShapeFactory shapeFactory;

    public MoveShapeCommand(ShapeList shapeList, ShapeDetails shapeDetails, PaintCanvasBase paintCanvas, ApplicationState applicationState, ShapeFactory shapeFactory){
        this.shapeList = shapeList;
        this.shapeDetails = shapeDetails;
        this.applicationState = applicationState;
        this.paintCanvas = paintCanvas;
        this.shapeFactory = shapeFactory;
    }

        @Override
        public void execute() {
            System.out.println("The move shape command is now running!");
            ArrayList<IShapeDrawer> moveShapeList = new ArrayList<IShapeDrawer>();

            // get the new mouse point. Idea taken from D2l discussion board
            deltaX = applicationState.getEndPoint().getX() - applicationState.getStartPoint().getX();
            deltaY = applicationState.getEndPoint().getY() - applicationState.getStartPoint().getY();


            for (IShapeDrawer shapetoMove : shapeList.getSelectedShapeList()) {
                shapeToMove = shapetoMove; // allows me to access this shape in my undo & redo methods below
                moveShapeList.add(shapeToMove);
                // remove so shapes wont get redrawn
                shapeList.remove(shapeToMove);

                for (IShapeDrawer movingShape: moveShapeList) {
                    // change the shapes x and y points
                    movingShape.addDeltaX(deltaX);
                    movingShape.addDeltaY(deltaY);
                    shape = movingShape;
                    // add shape back to main list so it gets redrawn
                    shapeList.add(shape);
                    if (!shape.getSelected()){
                        shape.setSelected(true);
                        ShapeType shapeTypeBuild = shape.getShapeDetails().getShapeType();
                        ShapeBorderSelector shapeBorderSelector = new ShapeBorderSelector(shapeTypeBuild, shape, paintCanvas, shape.getShapeDetails());
                        shapeBorderSelector.drawShapeBorder();
                    }
                }
                moveShapeList.clear();
                // would have to reselect shape before moving it for the second time
                //shapeList.DeleteEverythingSelectedShapeList();
            }
            CommandHistory.add(this);
        }

    @Override
    public void redo() {
        for (IShapeDrawer selectedShape : shapeList.getSelectedShapeList()){
            shapeList.remove(selectedShape);
            selectedShape.addDeltaX(deltaX);
            selectedShape.addDeltaY(deltaY);
            shapeList.add(selectedShape);
        }
    }

    @Override
    public void undo() {
        // get shape
        for (IShapeDrawer selectedShape : shapeList.getSelectedShapeList()){
            shapeList.remove(selectedShape);
            //edit shape's details
            selectedShape.addDeltaX(-deltaX);
            selectedShape.addDeltaY(-deltaY);
            // add changed shape back to main list
            shapeList.add(selectedShape);
        }
    }
}
