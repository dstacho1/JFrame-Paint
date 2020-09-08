package model.interfaces;

import model.ShapeDetails;
import view.interfaces.PaintCanvasBase;

public interface IFactory {
    void create(ShapeDetails shapeDetails, PaintCanvasBase paintCanvas);
}
