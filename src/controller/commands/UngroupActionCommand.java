package controller.commands;

import controller.IUndoable;
import model.Group;
import model.ShapeDetails;
import model.interfaces.IShapeDrawer;
import model.persistence.ShapeList;

import java.util.ArrayList;

public class UngroupActionCommand implements ICommand, IUndoable {
    private ShapeList shapeList;
    private ShapeDetails shapeDetails;

    public UngroupActionCommand(ShapeList shapeList, ShapeDetails shapeDetails){
        this.shapeList = shapeList;
        this.shapeDetails = shapeDetails;
    }


    @Override
    public void execute() {
        for (IShapeDrawer selectedShape : shapeList.getSelectedShapeList()) {
            if (selectedShape.isGroupObj()){
                Group group = (Group) selectedShape;
                for (IShapeDrawer shape: group.getGroupList()) {
                    shapeList.add(shape);
                }
                shapeList.remove(group);
            }
            shapeList.remove(selectedShape);
            System.out.println("SIZE OF SHAPELIST AFTER UNGROUP COMMAND: " + shapeList.getShapeList().size());
            break;
        }
        CommandHistory.add(this);
    }


    @Override
    public void redo() {
        new UngroupActionCommand(shapeList, shapeDetails).execute();
    }

    @Override
    public void undo() {
        //new GroupActionCommand(shapeList, shapeDetails).execute();
        Group group = new Group(shapeDetails);
        ArrayList<IShapeDrawer> redoList = new ArrayList<IShapeDrawer>();

        for (IShapeDrawer selectedShape : shapeList.getSelectedShapeList()) {
            shapeList.remove(selectedShape);
            group.addShape(selectedShape);
            redoList.add(selectedShape);
        }
        //IShapeDrawer groupShape = (IShapeDrawer) group;
        shapeList.add(group);
        shapeList.getSelectedShapeList().clear();
        shapeList.addToSelectedShapeList(group);
        CommandHistory.add(this);
    }
}
