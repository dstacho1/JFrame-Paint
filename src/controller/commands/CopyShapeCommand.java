package controller.commands;

import model.ShapeDetails;
import model.interfaces.IShapeDrawer;
import model.persistence.ApplicationState;
import model.persistence.ShapeList;
import view.interfaces.PaintCanvasBase;

public class CopyShapeCommand implements ICommand  {

    private ShapeList shapeList;
    private ShapeDetails shapeDetails;
    private PaintCanvasBase paintCanvas;
    private ApplicationState applicationState;


    public CopyShapeCommand(ShapeList shapeList, ShapeDetails shapeDetails, PaintCanvasBase paintCanvas, ApplicationState applicationState){
        this.shapeList = shapeList;
        this.shapeDetails = shapeDetails;
        this.paintCanvas = paintCanvas;
        this.applicationState = applicationState;
    }

    @Override
    public void execute() {
        for (IShapeDrawer selectedShape : shapeList.getSelectedShapeList()) {
            shapeList.addToCopiedList(selectedShape);
            System.out.println(selectedShape + " has been added to copiedList");
        }
    }
}
