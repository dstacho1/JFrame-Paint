package controller.commands;

import model.*;
import model.interfaces.IShapeDrawer;
import model.persistence.ApplicationState;
import model.persistence.ShapeList;
import view.interfaces.PaintCanvasBase;

public class SelectShapeCommand implements ICommand {

    private ShapeList shapeList;
    private ShapeDetails shapeDetails;
    private IShapeDrawer shape;
    private PaintCanvasBase paintCanvas;
    private ApplicationState applicationState;
    Boolean clearSelectedShape = false;


    public SelectShapeCommand(ShapeList shapeList, ShapeDetails shapeDetails, PaintCanvasBase paintCanvas, ApplicationState applicationState){
        this.shapeList = shapeList;
        this.shapeDetails = shapeDetails;
        this.applicationState = applicationState;
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void execute() {
        System.out.println("The select shape command is now running!");

        // a box to select multiple shapes
        SelectionBox selectionBox = new SelectionBox(applicationState.getStartPoint(), applicationState.getEndPoint());

        for (IShapeDrawer selectedShape: shapeList.getShapeList()) {
            boolean contains = selectedShape.contains(applicationState.getStartPoint());
            boolean selectionBoxIntersects = selectedShape.intersects(selectionBox);
            System.out.println("INTERSECTS IS " + selectionBoxIntersects);

            if (contains || selectionBoxIntersects ) {
                System.out.println(selectedShape + " has been selected");
                shape = selectedShape;
                // add to selectedShapeList for move command
                shapeList.addToSelectedShapeList(shape);

                if (!shape.getSelected()){
                    shape.setSelected(true);
                    ShapeType shapeTypeBuild = shape.getShapeDetails().getShapeType();
                    ShapeBorderSelector shapeBorderSelector = new ShapeBorderSelector(shapeTypeBuild, shape, paintCanvas, shape.getShapeDetails());
                    shapeBorderSelector.drawShapeBorder();
                }
                clearSelectedShape = true;
                System.out.println("Current size of selectedShapeList: " + shapeList.getSelectedShapeList().size());
            } else {
                System.out.println("no shape was selected");
            }
        }
        if (!clearSelectedShape){
            shapeList.DeleteEverythingSelectedShapeList();
            System.out.println("SelectedShapeList cleared!");
        }
    }

    public IShapeDrawer getSelectShape(){
        return shape;
    }
}
