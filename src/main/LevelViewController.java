package com.example.starwars_theageofrebellion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LevelViewController implements Initializable {
    Stage stage;
    User user;
    int level;
    Player player;
    Player player2;
    LevelViewController(Stage stage,User user){
        this.user=user;
        this.stage=stage;
        level=user.lvl;
        player2 = new Player(user);
    }

    @FXML
    Rectangle wall;

    @FXML
    public void back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        fxmlLoader.setController(new MainViewController(stage,user));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Circle c1;
    @FXML
    Circle c2;
    @FXML
    Circle c3;
    @FXML
    Circle c4;
    @FXML
    Circle c5;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (level>0) c1.setFill(Color.CYAN);
        if (level>1) c2.setFill(Color.CYAN);
        if (level>2) c3.setFill(Color.CYAN);
        if (level>3) c4.setFill(Color.CYAN);
        if (level>4) c5.setFill(Color.CYAN);
        try {
            wall.setFill(new ImagePattern(new Image(new FileInputStream("images\\level.jpg"))));
        }
        catch (Exception e){}
    }

    @FXML
    public void c1() throws IOException {
        if (isOpen(1)){
            player =new Player(Character.Mandalorian);
            goToGame(false,player);
        }
    }
    @FXML
    public void c2() throws IOException {
        if (isOpen(2)){
            player = new Player(Character.BobaFett);
            goToGame(false,player);
        }
    }
    @FXML
    public void c3() throws IOException {
        if (isOpen(3)){
            player = new Player(Character.Luke);
            goToGame(false,player);
        }
    }
    @FXML
    public void c4() throws IOException {
        if (isOpen(4)){
            player = new Player(Character.DarthVader);
            goToGame(false,player);
        }
    }
    @FXML
    public void c5() throws IOException {
        if (isOpen(5)){
            player = new Player(Character.None);
            goToGame(true,player);
        }
    }

    public boolean isOpen(int level){
        return this.level >= level;
    }

    public void goToGame(boolean boss,Player player) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("character.fxml"));
        fxmlLoader.setController(new CharacterViewController(stage,user,player,boss));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }
}
