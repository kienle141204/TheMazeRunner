package org.example.themazerunner;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Block extends Pane{
    private Rectangle wall;
    public Block(double x,double y, MazeDrawer mazeDrawer){
        wall = new Rectangle(x, y, mazeDrawer.getCellSizeWidth(), mazeDrawer.getCellSizeHeight());
        getChildren().add(wall);
        Main2.walls.add(this);
    }

    public Rectangle getwall() {
        return wall;
    }
}
