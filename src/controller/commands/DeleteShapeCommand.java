package controller.commands;

import controller.IUndoable;
import model.interfaces.IShapeDrawer;
import model.persistence.ShapeList;

import java.util.ArrayList;

public class DeleteShapeCommand implements ICommand, IUndoable {
    private ShapeList shapeList;
    ArrayList<IShapeDrawer> selectedShapeList;

    public DeleteShapeCommand(ShapeList shapeList) {
        this.shapeList = shapeList;
    }


    @Override
    public void execute() {
        selectedShapeList = shapeList.getSelectedShapeList();
        for (IShapeDrawer selectedShape : selectedShapeList) {
            shapeList.remove(selectedShape);
            // notify the paintCanvas to redraw all shapes in the main shape list
            shapeList.notifyObserver();
        }
        CommandHistory.add(this);
    }

    @Override
    public void redo() {
        // redo just re-deletes the shapes again after being undone
        for (IShapeDrawer selectedShape : selectedShapeList) {
            shapeList.remove(selectedShape);
        }
    }

    @Override
    public void undo() {
        // put the selected shapes back into the list to be redrawn after a deleteCommand
        for (IShapeDrawer selectedShape : selectedShapeList) {
            shapeList.add(selectedShape);
        }
    }
}
