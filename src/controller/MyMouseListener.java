package controller;

import controller.commands.CreateShapeCommand;
import controller.commands.MoveShapeCommand;
import controller.commands.SelectShapeCommand;
import model.Point;
import model.ShapeDetails;
import model.StartAndEndPointMode;
import model.persistence.ShapeList;
import model.interfaces.IFactory;
import model.persistence.ApplicationState;
import model.shapeStrategy.ShapeFactory;
import view.interfaces.PaintCanvasBase;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MyMouseListener extends MouseAdapter {
    private final ShapeDetails shapeDetails;
    private final IFactory shapeFactory;
    private Point startPoint;
    private PaintCanvasBase paintCanvas;
    private ShapeList shapeList;
    private ApplicationState applicationState;
    

    public MyMouseListener(PaintCanvasBase paintCanvas, ShapeDetails shapeDetails, ShapeList shapeList, ApplicationState applicationState, IFactory shapeFactory){
        this.paintCanvas = paintCanvas;
        this.shapeList = shapeList;
        this.shapeDetails = shapeDetails;
        this.applicationState = applicationState;
        this.shapeFactory = shapeFactory;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());
        applicationState.setStartPoint(startPoint);
        //System.out.println("Start point: " + startPoint.getX() + ", " + startPoint.getY() );
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        StartAndEndPointMode mode = applicationState.getActiveStartAndEndPointMode();
        Point endPoint = new Point(e.getX(), e.getY());
        applicationState.setEndPoint(endPoint);
        //System.out.println("End point: " + endPoint.getX() + ", " + endPoint.getY() );

        switch (mode) {
            case DRAW:
                CreateShapeCommand createShape = new CreateShapeCommand(shapeList, shapeDetails, paintCanvas, applicationState, (ShapeFactory) shapeFactory);
                createShape.execute();
                break;
            case SELECT:
                SelectShapeCommand selectShape = new SelectShapeCommand(shapeList, shapeDetails, paintCanvas, applicationState);
                selectShape.execute();
                break;
            case MOVE:
                MoveShapeCommand moveShape = new MoveShapeCommand(shapeList, shapeDetails, paintCanvas, applicationState, (ShapeFactory) shapeFactory);
                moveShape.execute();
                break;
        }
    }
}
