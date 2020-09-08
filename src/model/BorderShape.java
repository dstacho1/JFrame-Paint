package model;

import model.interfaces.IShapeDrawer;
import view.interfaces.PaintCanvasBase;

public abstract class BorderShape implements IShapeDrawer {

    private ShapeDetails shapeDetails;
    protected IShapeDrawer borderShape;
    private PaintCanvasBase paintCanvas;

        public BorderShape(IShapeDrawer shapeToDecorate, PaintCanvasBase paintCanvas, ShapeDetails shapeDetails){
            this.borderShape = shapeToDecorate;
            this.shapeDetails = shapeToDecorate.getShapeDetails();
            this.paintCanvas = paintCanvas;
        }

        //@Override
        public void draw() {
            borderShape.draw();
        }
    }
