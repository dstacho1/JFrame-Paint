package model.interfaces;

import model.Point;
import model.SelectionBox;
import model.ShapeDetails;

// The IShapeDrawer interface specifies what shapes must do, not how
public interface IShapeDrawer {

    void draw();
    // used to check if the selectedPoint is within the shape
    boolean contains(Point selectedPoint);
    // used to check if a selectionBox collides with a shape
    boolean intersects(SelectionBox selectionBox);
    // used to re-draw the shapes with changed points
    void addDeltaX(int deltaX);
    void addDeltaY(int deltaY);

    ShapeDetails getShapeDetails();

    boolean getSelected();

    void setSelected(boolean status);

    boolean isGroupObj();
}
