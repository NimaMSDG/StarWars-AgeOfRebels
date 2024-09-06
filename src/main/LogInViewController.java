package com.example.starwars_theageofrebellion;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
import java.util.Timer;
import java.util.TimerTask;

public class LogInViewController implements Initializable {

    Stage stage;
    LogInViewController(Stage stage){
        this.stage=stage;
    }

    double FRAMES_PER_SECOND=0.1;
    static int numberOfErrors=0;
    static long Time;
    Timer timer;
    @FXML
    TextField username;
    @FXML
    PasswordField password;

    @FXML
    Label error;

    @FXML
    Rectangle wall;

    @FXML
    public void login() throws IOException {
        if (timeError(System.currentTimeMillis()/1000-Time)){
            return;
        }

        String username = this.username.getText();
        String password = this.password.getText();

        User user = User.getUserByUsername(username);
        if (user==null){
            error.setText(String.valueOf("Username doesn’t exist!"));
            numberOfErrors++;
            Time =System.currentTimeMillis()/1000;
            return ;
        }
        if (!user.Password.equals(password)){
            error.setText(String.valueOf("Password and Username don’t match!"));
            numberOfErrors++;
            Time = System.currentTimeMillis()/1000;
            return ;
        }
        System.out.println("Logged in successfully");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        fxmlLoader.setController(new MainViewController(stage,user));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void fmp() throws IOException {

        User user = User.getUserByUsername(username.getText());
        if (user==null){
            error.setText(String.valueOf("Username doesn’t exist!"));
            numberOfErrors++;
            Time =System.currentTimeMillis()/1000;
            return ;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("forgot.fxml"));
        fxmlLoader.setController(new ForgotViewController(stage,user));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void signup() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signup.fxml"));
        fxmlLoader.setController(new SignUpViewController(stage));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }



    public boolean timeError(long deltaT){
        if ((long)numberOfErrors*5-deltaT>0){
            error.setText("Try again in "+(int)((long)numberOfErrors*5-deltaT) +" seconds");
            return true;
        }
        error.setText("");
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Time = System.currentTimeMillis()/1000;
        try{
        FileInputStream fileInputStream = new FileInputStream("images\\logBack.jpg");
        Image image = new Image(fileInputStream);
        wall.setFill(new ImagePattern(image));}
        catch (Exception e){}
        startTimer();
    }

    public void startTimer(){
        this.timer=new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        timeError(System.currentTimeMillis()/1000-Time);
                    }
                });
            }
        };
        long frameTimeInMilliseconds = (long)(10.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }
}
