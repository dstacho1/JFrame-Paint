package controller;

import controller.commands.*;
import model.ShapeDetails;
import model.interfaces.IApplicationState;
import model.persistence.ApplicationState;
import model.persistence.ShapeList;
import model.shapeStrategy.ShapeFactory;
import view.EventName;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final ApplicationState applicationState;
    private ShapeList shapeList;
    private ShapeDetails shapeDetails;
    private PaintCanvasBase paintCanvas;
    private ShapeFactory shapeFactory;


    public JPaintController(IUiModule uiModule, ApplicationState applicationState, ShapeList shapeList, ShapeDetails shapeDetails, PaintCanvasBase paintCanvas, ShapeFactory shapeFactory) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.shapeDetails = shapeDetails;
        this.shapeList = shapeList;
        this.paintCanvas = paintCanvas;
        this.shapeFactory = shapeFactory;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.DELETE, ()-> new DeleteShapeCommand(shapeList).execute());
        uiModule.addEvent(EventName.COPY, ()-> new CopyShapeCommand(shapeList, shapeDetails, paintCanvas, applicationState).execute());
        uiModule.addEvent(EventName.PASTE, ()-> new PasteShapeCommand(shapeList, shapeDetails, paintCanvas, applicationState, (ShapeFactory) shapeFactory).execute());
        uiModule.addEvent(EventName.UNDO, ()-> new UndoActionCommand().execute());
        uiModule.addEvent(EventName.REDO, ()-> new RedoActionCommand().execute());
        uiModule.addEvent(EventName.GROUP, ()-> new GroupActionCommand(shapeList, shapeDetails).execute());
        uiModule.addEvent(EventName.UNGROUP, ()-> new UngroupActionCommand(shapeList, shapeDetails).execute());

    }
}
