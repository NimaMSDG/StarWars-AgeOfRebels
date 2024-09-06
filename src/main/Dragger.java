package com.example.starwars_theageofrebellion;


import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Dragger {
    double x;
    double y;
    double sx=0;
    double sy=0;
    int index=-1;

    public void makeDragger(Rectangle node, Label level,Label damage,double[] cardNumber, double[] inBlock,int nobat,boolean canDrag){
        sx = node.getLayoutX();
        sy = node.getLayoutY();
        node.setOnMousePressed(MouseEvent -> {
            x=MouseEvent.getX();
            y=MouseEvent.getY();
            cardNumber[0]=MouseEvent.getSceneX();
            cardNumber[1]=MouseEvent.getSceneY();
            node.setOpacity(0.5);
        });
        node.setOnMouseDragged(MouseEvent -> {
            if (nobat%2==1 && MouseEvent.getSceneY()>310){
                return;
            }
            if (nobat%2==0 && MouseEvent.getSceneY()<310){
                return;
            }
            if(!canDrag) return;
            node.setLayoutX(MouseEvent.getSceneX()-x);
            node.setLayoutY(MouseEvent.getSceneY()-y);
            level.setLayoutX(node.getLayoutX()+3);
            level.setLayoutY(node.getLayoutY()+11);
            damage.setLayoutX(level.getLayoutX()+60);
            damage.setLayoutY(level.getLayoutY());
        });
        node.setOnMouseReleased(MouseEvent -> {
            inBlock[0] = node.getLayoutX()+node.getWidth()/2.0;
            inBlock[1] = node.getLayoutY()+node.getHeight()/2.0;
            node.setLayoutX(sx);
            node.setLayoutY(sy);
            level.setLayoutX(node.getLayoutX()+3);
            level.setLayoutY(node.getLayoutY()+11);
            damage.setLayoutX(level.getLayoutX()+60);
            damage.setLayoutY(level.getLayoutY());
            node.setOpacity(1);
        });

    }




}
