package com.example.starwars_theageofrebellion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    Stage stage;
    User user;

    @FXML
    Rectangle wall;
    MainViewController(Stage stage,User user){
        this.stage=stage;
        this.user=user;
    }
    //
    @FXML
    Button loc;
    @FXML
    Button bet;
    @FXML
    Button bot;
    @FXML
    Button card;
    @FXML
    Button store;
    //
    @FXML
    Rectangle white;
    @FXML
    TextField u;
    @FXML
    PasswordField p;
    @FXML
    Label error;
    @FXML
    Button login;

    String ADMIN_USER="javad";
    String ADMIN_PASS="javadi";
    Boolean adminLog=false;


    Boolean betMode=false;


    @FXML
    Rectangle icon;
    @FXML
    Label level;
    @FXML
    Label hp;
    @FXML
    Label credit;


    //


    @FXML
    public void game(){
        loc.setVisible(true);
        bot.setVisible(true);
        bet.setVisible(true);
        adminLog=false;
    }
    @FXML
    public void history() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("history.fxml"));
        fxmlLoader.setController(new HistoryViewController(stage,user));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void shop(){
        card.setVisible(true);
        store.setVisible(true);
    }
    @FXML
    public void profile(){

    }
    @FXML
    public void admin(){
        setVisibleLogin(true);
        adminLog=true;
    }
    @FXML
    public void logout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        fxmlLoader.setController(new LogInViewController(stage));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loc() throws IOException {
        setVisibleLogin(true);
        betMode=false;
        login();
    }
    @FXML
    public void bet() throws IOException {
        setVisibleLogin(true);
        betMode=true;
        login();
    }
    @FXML
    public void bot() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("level.fxml"));
        fxmlLoader.setController(new LevelViewController(stage,user));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void store() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("shop.fxml"));
        fxmlLoader.setController(new ShopViewController(stage,user,1));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void card() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("shop.fxml"));
        fxmlLoader.setController(new ShopViewController(stage,user,2));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }

    public void setVisibleLogin(boolean bool){
        login.setVisible(bool);
        error.setVisible(bool);
        u.setVisible(bool);
        p.setVisible(bool);
        white.setVisible(bool);
    }

    @FXML
    public void login() throws IOException {
        if (u.getText().isEmpty()){
            error.setText("enter username");
            return;
        }
        if (p.getText().isEmpty()){
            error.setText("enter password");
            return;
        }
        if (!adminLog){
        User user1 = User.getUserByUsername(u.getText());
        if (user1==null){
            error.setText("user with this username not exist");
            return;
        }
        if (!user1.Password.equals(p.getText())){
            error.setText("incorrect password");
            return;
        }
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("character.fxml"));
            fxmlLoader.setController(new CharacterViewController(stage,user,user1,betMode));
            Scene scene = new Scene(fxmlLoader.load(), 980, 620);
            stage.setScene(scene);
            stage.show();
        }
        else {
            if (!u.getText().equals(ADMIN_USER)||!p.getText().equals(ADMIN_PASS)){
                error.setText("incorrect username or password");
                return;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
            fxmlLoader.setController(new AdminViewController(stage,user));
            Scene scene = new Scene(fxmlLoader.load(), 980, 620);
            stage.setScene(scene);
            stage.show();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            FileInputStream fileInputStream = new FileInputStream("images\\vader.gif");
            FileInputStream fileInputStream1 = new FileInputStream("images\\icon.png");
            Image image = new Image(fileInputStream);
            Image image1 = new Image(fileInputStream1);
            wall.setFill(new ImagePattern(image));
            icon.setFill(new ImagePattern(image1));
        }
        catch (Exception e){}
        level.setText(String.valueOf(user.Level));
        hp.setText(String.valueOf(user.HP()));
        credit.setText(String.valueOf(user.Credit));
    }
}
