package model;

import java.awt.*;
import java.util.EnumMap;

public class ColorChooser {
    private ColorChooser(){}

    public static EnumMap<ShapeColor, Color> colorEMap = new EnumMap<>(ShapeColor.class);
    static {
        // colors used from ShapeColor
//                BLACK,
//                BLUE,
//                CYAN,
//                DARK_GRAY,
//                GRAY,
//                GREEN,
//                LIGHT_GRAY,
//                MAGENTA,
//                ORANGE,
//                PINK,
//                RED,
//                WHITE,
//                YELLOW
        colorEMap.put(ShapeColor.BLACK, Color.black);
        colorEMap.put(ShapeColor.BLUE, Color.blue);
        colorEMap.put(ShapeColor.CYAN, Color.cyan);
        colorEMap.put(ShapeColor.DARK_GRAY, Color.darkGray);
        colorEMap.put(ShapeColor.GRAY, Color.gray);
        colorEMap.put(ShapeColor.GREEN, Color.green);
        colorEMap.put(ShapeColor.LIGHT_GRAY, Color.lightGray);
        colorEMap.put(ShapeColor.MAGENTA, Color.magenta);
        colorEMap.put(ShapeColor.ORANGE, Color.orange);
        colorEMap.put(ShapeColor.PINK, Color.pink);
        colorEMap.put(ShapeColor.RED, Color.red);
        colorEMap.put(ShapeColor.WHITE, Color.white);
        colorEMap.put(ShapeColor.YELLOW, Color.yellow);

    }

    public static Color getShapeColor(ShapeColor color){
        return colorEMap.get(color);
    }
}
