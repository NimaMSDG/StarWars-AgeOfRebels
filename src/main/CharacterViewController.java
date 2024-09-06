package com.example.starwars_theageofrebellion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class CharacterViewController implements Initializable {
    Stage stage;
    User user1,user2;
    boolean betMode;
    boolean BossFight;
    boolean levelMode;
    Player player;

    int bet1=0,bet2=0;

    Random random = new Random();

    CharacterViewController(Stage stage,User user1,User user2,boolean betMode){
        this.stage=stage;
        this.user1=user1;
        this.user2=user2;
        this.betMode=betMode;
        BossFight=false;
        levelMode=false;
    }
    CharacterViewController(Stage stage,User user1,Player player,Boolean bossFight){
        this.stage=stage;
        this.user1=user1;
        this.player=player;
        BossFight = bossFight;
        character1=player.character;
        betMode=false;
        levelMode=true;
    }
    @FXML
    Rectangle vader;
    @FXML
    Rectangle luke;
    @FXML
    Rectangle boba;
    @FXML
    Rectangle mando;
    @FXML
    Rectangle p1;
    @FXML
    Rectangle p2;

    @FXML
    Label pyb;
    @FXML
    TextField b1;
    @FXML
    TextField b2;
    @FXML
    Label error;
    Character character1=Character.None,character2=Character.None;



    @FXML
    public void v(){
        if (character1==Character.None){
            character1=Character.DarthVader;
            p1.setLayoutX(vader.getLayoutX());
            p1.setLayoutY(vader.getLayoutY());
            return;
        }
        if (character2==Character.None){
            character2=Character.DarthVader;
            p2.setLayoutX(vader.getLayoutX());
            p2.setLayoutY(vader.getLayoutY());
            return;
        }
    }
    @FXML
    public void l(){
        if (character1==Character.None){
            character1=Character.Luke;
            p1.setLayoutX(luke.getLayoutX());
            p1.setLayoutY(luke.getLayoutY());
            return;
        }
        if (character2==Character.None){
            character2=Character.Luke;
            p2.setLayoutX(luke.getLayoutX());
            p2.setLayoutY(luke.getLayoutY());
            return;
        }
    }
    @FXML
    public void b(){
        if (character1==Character.None){
            character1=Character.BobaFett;
            p1.setLayoutX(boba.getLayoutX());
            p1.setLayoutY(boba.getLayoutY());
            return;
        }
        if (character2==Character.None){
            character2=Character.BobaFett;
            p2.setLayoutX(boba.getLayoutX());
            p2.setLayoutY(boba.getLayoutY());
            return;
        }
    }
    @FXML
    public void m(){
        if (character1==Character.None){
            character1=Character.Mandalorian;
            p1.setLayoutX(mando.getLayoutX());
            p1.setLayoutY(mando.getLayoutY());
            return;
        }
        if (character2==Character.None){
            character2=Character.Mandalorian;
            p2.setLayoutX(mando.getLayoutX());
            p2.setLayoutY(mando.getLayoutY());
            return;
        }
    }

    @FXML
    Rectangle wall;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (betMode){
            pyb.setVisible(true);
            b1.setVisible(true);
            b2.setVisible(true);
        }
        try {
            vader.setFill(new ImagePattern(new Image(new FileInputStream("images\\Battlefront_Vader.jpg"))));
            luke.setFill(new ImagePattern(new Image(new FileInputStream("images\\Battlefront_Luke.jpg"))));
            boba.setFill(new ImagePattern(new Image(new FileInputStream("images\\Battlefront_Boba.jpg"))));
            mando.setFill(new ImagePattern(new Image(new FileInputStream("images\\Battlefront_Han.jpg"))));
            wall.setFill(new ImagePattern(new Image(new FileInputStream("images\\char.jpg"))));
        }
        catch (Exception e){}
    }

    @FXML
    public void start() throws IOException {
        if (character2==Character.None){
            error.setText("* select your character");
            return;
        }
        else if (!levelMode){
            if (betMode){
                if (b1.getText().isEmpty()||b2.getText().isEmpty()){
                    error.setText("* place your bets");
                    return;
                }
                bet1=Integer.parseInt(b1.getText());
                bet2=Integer.parseInt(b2.getText());
                if (bet1>user1.Credit){
                    error.setText(user1.Username+" your bet is too high");
                    return;
                }
                if (bet2>user2.Credit){
                    error.setText(user2.Username+" your bet is too high");
                    return;
                }
                user1.getCredit(-bet1);
                user2.getCredit(-bet2);
            }
            int i = random.nextInt(2);
            Player p1 = new Player(user1);
            p1.setCharacter(character1);
            Player p2 = new Player(user2);
            p2.setCharacter(character2);
            if (i==1){
                p1 = new Player(user2);
                p1.setCharacter(character2);
                p2 = new Player(user1);
                p2.setCharacter(character1);
            }
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game.fxml"));
            fxmlLoader.setController(new GameViewController(stage,user1,p1,p2,false,false,false,bet1+bet2));
            Scene scene = new Scene(fxmlLoader.load(), 980, 620);
            stage.setScene(scene);
            stage.show();

        }
        else {
            Player player1 = new Player(user1);
            player1.setCharacter(character2);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game.fxml"));
            fxmlLoader.setController(new GameViewController(stage,user1,player,player1,true,BossFight,true,0));
            Scene scene = new Scene(fxmlLoader.load(), 980, 620);
            stage.setScene(scene);
            stage.show();
        }

    }

}
