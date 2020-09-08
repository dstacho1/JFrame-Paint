package controller.commands;

import controller.IUndoable;
import model.Group;
import model.ShapeDetails;
import model.interfaces.IShapeDrawer;
import model.persistence.ShapeList;

import java.util.ArrayList;


public class GroupActionCommand implements IUndoable, ICommand {
    private ShapeList shapeList;
    private Group group;
    private ArrayList<IShapeDrawer> redoList;

    public GroupActionCommand(ShapeList shapeList, ShapeDetails shapeDetails, IShapeDrawer... groupShapes){
        this.shapeList = shapeList;
        group = new Group(shapeDetails);
        redoList = new ArrayList<IShapeDrawer>();
    }


    @Override
    public void execute() {
        // get selected shapes, remove them from main list, add them to group obj
        for (IShapeDrawer selectedShape : shapeList.getSelectedShapeList()) {
            shapeList.remove(selectedShape);
            group.addShape(selectedShape);
            redoList.add(selectedShape);
        }
        shapeList.add(group);
        shapeList.getSelectedShapeList().clear();
        shapeList.addToSelectedShapeList(group);
        CommandHistory.add(this);
    }



    @Override
    public void redo() {
        //new GroupActionCommand(shapeList, shapeDetails).execute();

        for (IShapeDrawer selectedShape : redoList) {
            shapeList.remove(selectedShape);
            group.addShape(selectedShape);
        }
        shapeList.add(group);
        shapeList.getSelectedShapeList().clear();
        shapeList.addToSelectedShapeList(group);
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        //new UngroupActionCommand(shapeList, shapeDetails).execute();
        for (IShapeDrawer selectedShape : shapeList.getSelectedShapeList()) {
            if (selectedShape.isGroupObj()){
                Group group = (Group) selectedShape;
                for (IShapeDrawer shape: group.getGroupList()) {
                    shapeList.add(shape);
                }
                shapeList.remove(group);
            }
            shapeList.remove(selectedShape);
            break;
        }
    }
}
