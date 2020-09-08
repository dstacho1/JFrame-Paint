package model.interfaces;

import view.gui.PaintCanvas;
import java.util.ArrayList;

public interface ShapeListSubject {
    void register(PaintCanvas paintCanvas);
    void notifyObserver();

    ArrayList<IShapeDrawer> getShapeList();
    ArrayList<IShapeDrawer> getSelectedShapeList();

    void add(IShapeDrawer shape);
    void remove(IShapeDrawer shape);
}
