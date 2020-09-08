package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.MyMouseListener;
import model.ShapeDetails;
import model.persistence.ShapeList;
import model.interfaces.IFactory;
import model.persistence.ApplicationState;
import model.shapeStrategy.ShapeFactory;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;


public class Main {
    public static void main(String[] args){
        ShapeList shapeList = new ShapeList();
        PaintCanvasBase paintCanvas = new PaintCanvas(shapeList);
        ShapeDetails shapeDetails = new ShapeDetails();

        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);

        IFactory shapeFactory = new ShapeFactory(paintCanvas, shapeList);
        paintCanvas.addMouseListener(new MyMouseListener(paintCanvas, shapeDetails,shapeList, appState, shapeFactory));

        IJPaintController controller = new JPaintController(uiModule, appState, shapeList, shapeDetails, paintCanvas, (ShapeFactory) shapeFactory);
        controller.setup();
    }
}
