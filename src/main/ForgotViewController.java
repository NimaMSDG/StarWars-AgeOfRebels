package com.example.starwars_theageofrebellion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForgotViewController implements Initializable {
    Stage stage;
    User user;
    ForgotViewController(Stage stage,User user){
        this.stage=stage;
        this.user=user;
    }

    @FXML
    Rectangle wall;
    @FXML
    Label q;
    @FXML
    TextField answer;
    @FXML
    TextField pass;
    @FXML
    TextField copass;

    @FXML
    Label error;
    @FXML
    public void change() throws IOException {
        if (!user.recoveryQ.checkAnswer(answer.getText())){
            error.setText("incorrect answer to security question");
            return;
        }
        if (!pass.getText().equals(copass.getText())){
            error.setText("passwords don't match");
            return;
        }
        user.Password=pass.getText();
        new DataBase().updateUser(user);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        fxmlLoader.setController(new LogInViewController(stage));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        q.setText(user.recoveryQ.Question);
        try{
            FileInputStream fileInputStream = new FileInputStream("images\\logBack.jpg");
            Image image = new Image(fileInputStream);
            wall.setFill(new ImagePattern(image));}
        catch (Exception e){}
    }
}
