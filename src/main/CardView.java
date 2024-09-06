package com.example.starwars_theageofrebellion;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class CardView {
    Rectangle rectangle = new Rectangle();
    Label level = new Label();
    Label damage = new Label();

    CardView(ImagePattern imagePattern, int level, int damage){
        this.rectangle.setFill(imagePattern);
        rectangle.setWidth(80);
        rectangle.setHeight(120);
        this.level.setTextFill(Color.WHITE);
        this.damage.setTextFill(Color.WHITE);
        this.level.setText(String.valueOf(level));
        this.damage.setText(String.valueOf(damage));
        if (level==99){
            this.level.setText("");
            this.damage.setText("");
        }
    }

    public void setLayout(double x,double y){
        rectangle.setLayoutX(x);
        rectangle.setLayoutY(y);
        level.setLayoutX(rectangle.getLayoutX()+3);
        level.setLayoutY(rectangle.getLayoutY()+11);
        damage.setLayoutX(level.getLayoutX()+60);
        damage.setLayoutY(level.getLayoutY());
    }
}
