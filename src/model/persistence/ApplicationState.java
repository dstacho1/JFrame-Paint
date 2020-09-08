package model.persistence;

import model.*;
import model.dialogs.DialogProvider;
import model.interfaces.IApplicationState;
import model.interfaces.IDialogProvider;
import view.interfaces.IUiModule;

import java.io.Serializable;

public class ApplicationState implements IApplicationState, Serializable {
    private static final long serialVersionUID = -5545483996576839009L;
    private final IUiModule uiModule;
    private final IDialogProvider dialogProvider;

    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndEndPointMode activeStartAndEndPointMode;

    private Point startPoint;
    private Point endPoint;


    public ApplicationState(IUiModule uiModule) {
        this.uiModule = uiModule;
        this.dialogProvider = new DialogProvider(this);
        setDefaults();
    }

    // added to set shapeDetails at run-time during shape creation
    public ShapeDetails getRunningShapeDetails(){
        ShapeDetails shapeDetails = new ShapeDetails();
        shapeDetails.setShapeType(activeShapeType);
        shapeDetails.setShapeShadingType(activeShapeShadingType);
        shapeDetails.setPrimaryShapeColor(activePrimaryColor);
        shapeDetails.setSecondaryShapeColor(activeSecondaryColor);
        shapeDetails.setEndPoint(endPoint);
        shapeDetails.setStartPoint(startPoint);
        shapeDetails.setEndX(endPoint.getX());
        shapeDetails.setEndY(endPoint.getY());
        shapeDetails.setStartX(startPoint.getX());
        shapeDetails.setStartY(startPoint.getY());
        return shapeDetails;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
    public Point getStartPoint() { return startPoint;}

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
    public Point getEndPoint() {return endPoint;}


    @Override
    public void setActiveShape() {
        activeShapeType = uiModule.getDialogResponse(dialogProvider.getChooseShapeDialog());
    }

    @Override
    public void setActivePrimaryColor() {
        activePrimaryColor = uiModule.getDialogResponse(dialogProvider.getChoosePrimaryColorDialog());
    }

    @Override
    public void setActiveSecondaryColor() {
        activeSecondaryColor = uiModule.getDialogResponse(dialogProvider.getChooseSecondaryColorDialog());
    }

    @Override
    public void setActiveShadingType() {
        activeShapeShadingType = uiModule.getDialogResponse(dialogProvider.getChooseShadingTypeDialog());
    }

    @Override
    public void setActiveStartAndEndPointMode() {
        activeStartAndEndPointMode = uiModule.getDialogResponse(dialogProvider.getChooseStartAndEndPointModeDialog());
    }

    @Override
    public ShapeType getActiveShapeType() {
        return activeShapeType;
    }

    @Override
    public ShapeColor getActivePrimaryColor() {
        return activePrimaryColor;
    }

    @Override
    public ShapeColor getActiveSecondaryColor() {
        return activeSecondaryColor;
    }

    @Override
    public ShapeShadingType getActiveShapeShadingType() {
        return activeShapeShadingType;
    }

    @Override
    public StartAndEndPointMode getActiveStartAndEndPointMode() {
        return activeStartAndEndPointMode;
    }

    private void setDefaults() {
        activeShapeType = ShapeType.RECTANGLE;
        activePrimaryColor = ShapeColor.RED;
        activeSecondaryColor = ShapeColor.ORANGE;
        activeShapeShadingType = ShapeShadingType.OUTLINE_AND_FILLED_IN;
        activeStartAndEndPointMode = StartAndEndPointMode.DRAW;
    }
}
