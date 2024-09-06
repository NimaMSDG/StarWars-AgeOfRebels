package com.example.starwars_theageofrebellion;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.FileInputStream;

public class AdminCards extends Card{

    AdminCards(String name, int level, int damage,int duration, int price, int upgradeLevel, int upgradeCost, Character character,String img) {
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.duration = duration;
        this.price = price;
        this.upgradeLevel = upgradeLevel;
        this.upgradeCost = upgradeCost;
        this.character = character;
        this.type = Type.normal;
        number=1;
        try{
            imgPath=img;
        FileInputStream fileInputStream = new FileInputStream(img);
        Image image1 = new Image(fileInputStream);
        image = new ImagePattern(image1);
        cardView = new CardView(image,level,damage)

        ;}
        catch (Exception e){System.out.println("man injam"+img);}
    }


    @Override
    public String properties() {
        return null;
    }



}
