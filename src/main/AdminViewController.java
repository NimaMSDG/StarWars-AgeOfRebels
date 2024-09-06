package com.example.starwars_theageofrebellion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    Stage stage;
    User user;

    DataBase dataBase = new DataBase();
    AdminViewController(Stage stage,User user){
        this.stage=stage;
        this.user=user;
    }

    @FXML
    Rectangle wall;

    @FXML
    TextField name;
    @FXML
    TextField damage;
    @FXML
    TextField level;
    @FXML
    TextField duration;
    @FXML
    TextField price;
    @FXML
    TextField character;
    @FXML
    TextField ul;
    @FXML
    TextField uc;
    @FXML
    Rectangle card;

    String imgPath;
    @FXML
    public void pic() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        imgPath=file.getAbsolutePath();
        selectPic();
    }

    public void selectPic() throws FileNotFoundException {
        card.setFill(new ImagePattern(new Image(new FileInputStream(imgPath))));
    }

    @FXML
    public void make(){
        String name = this.name.getText();
        int level = Integer.parseInt(this.level.getText());
        int damage = Integer.parseInt(this.damage.getText());
        int duration = Integer.parseInt(this.duration.getText());
        int price = Integer.parseInt(this.price.getText());
        String characterSTR = (this.character.getText());
        int ul = Integer.parseInt(this.ul.getText());
        int uc = Integer.parseInt(this.uc.getText());

        Character character = Character.Mandalorian;
        Character[] characters = {Character.None,Character.Luke,
                Character.Mandalorian,Character.BobaFett,Character.DarthVader};
        for (Character character1:characters){
            if (character1.toString().equals(characterSTR)){
                character = character1;
                break;
            }
        }

        Card card = new AdminCards(name,level,damage,duration,price,ul,uc,character,imgPath);
        System.out.println("complited1");
        dataBase.addCard(card,null);
        System.out.println("complited");
    }

    @FXML
    public void back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        fxmlLoader.setController(new MainViewController(stage,user));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            wall.setFill(new ImagePattern(new Image(new FileInputStream("images\\download.jpeg"))));
        }
        catch (Exception e){}
    }
}
