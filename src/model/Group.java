package model;

import model.interfaces.IShapeDrawer;
import java.util.ArrayList;


public class Group implements IShapeDrawer {

    private ArrayList<IShapeDrawer> groupList = new ArrayList<IShapeDrawer>();
    private ShapeDetails shapeDetails;
    boolean isGroupObj;


    public Group(ShapeDetails shapeDetails){
        this.shapeDetails = shapeDetails;
        isGroupObj = true;
    }

    public void addShape(IShapeDrawer shape){
        groupList.add(shape);
    }

    public void addGroup(IShapeDrawer shapeGroup){
        groupList.add(shapeGroup);
    }


    public ArrayList<IShapeDrawer> getGroupList(){
        return this.groupList;
    }

    public void remove(IShapeDrawer shape){
        groupList.remove(shape);
    }

    public void createShapeDetails(){
        int y = 0, x = 0;
        int shapeX, shapeY;
        for (IShapeDrawer shape : groupList) {
            shapeX = shape.getShapeDetails().getX();
            shapeY = shape.getShapeDetails().getY();
            x = Math.min(x,shapeX);
            y = Math.min(y,shapeY);
        }
    }

    @Override
    public void draw() {
        for (IShapeDrawer shapeItem: groupList){
            shapeItem.draw();
        }
        System.out.println("GROUPS OBJECTS DRAW METHOD");
    }


    @Override
    public boolean contains(Point selectedPoint) {
        return false;
    }

    @Override
    public boolean intersects(SelectionBox selectionBox) {
        return false;
    }

    @Override
    public void addDeltaX(int deltaX) {

    }

    @Override
    public void addDeltaY(int deltaY) {

    }

    @Override
    public ShapeDetails getShapeDetails() {
        return null;
    }

    @Override
    public boolean getSelected() {
        return false;
    }

    @Override
    public void setSelected(boolean status) {

    }

    @Override
    public boolean isGroupObj() {
        return isGroupObj;
    }
}
