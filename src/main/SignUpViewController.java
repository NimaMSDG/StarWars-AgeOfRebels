package com.example.starwars_theageofrebellion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class SignUpViewController implements Initializable {

    Stage stage;
    User user;
    SignUpViewController(Stage stage){
        this.stage=stage;
    }
    SignUpViewController(Stage stage,User user){
        this.stage=stage;
        this.user=user;

    }


    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    PasswordField copassword;
    @FXML
    TextField email;
    @FXML
    TextField nickname;
    @FXML
    Label error;

    ////

    @FXML
    RadioButton q1;
    @FXML
    RadioButton q2;
    @FXML
    RadioButton q3;
    @FXML
    TextField answer;
    @FXML
    Label l1;
    @FXML
    Label l2;
    @FXML
    Label l3;
    @FXML
    Label l4;
    @FXML
    Label l5;
    @FXML
    Label l6;
    @FXML
    Label l7;
    @FXML
    Label l8;
    @FXML
    TextField captchaA;
    @FXML
    Label error2;
    @FXML
    Rectangle wall;


    String[] capStr = new String[8];
    String captchaa;


    @FXML
    public void next() throws IOException {
        String username = this.username.getText();
        String password = this.password.getText();
        String confirmPass = this.copassword.getText();
        String email = this.email.getText();
        String nickname = this.nickname.getText();
        if (username.isEmpty()) {error.setText("Please enter your Username");return ;}
        if (password.isEmpty()) {error.setText("Please enter your Password");return ;}
        if (confirmPass.isEmpty()) {error.setText("Please enter your Confirmation Password");return ;}
        if (email.isEmpty()) {error.setText("Please enter your Email");return ;}
        if (nickname.isEmpty()) {error.setText("Please enter your Nickname");return ;}


        if (!username.matches("^[0-9A-Za-z_]+$")){
            error.setText("Incorrect format for username!");
            return ;
        }
        /*if (User.getUserByUsername(username)!=null){
            error.setText("Username already exists!");
            return ;
        }*/
        if (password.length()<8){
            error.setText("Password is too short!");
            return;
        }
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])" +
                "(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]+$")){
            error.setText("Password is too weak!");
            return ;
        }
        if (!email.matches("([A-Za-z0-9_-]+)@([A-Za-z0-9_-]+)\\.com")){
            error.setText("Invalid email format");
            return ;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("confirmation.fxml"));
        fxmlLoader.setController(new SignUpViewController(stage,new User(username,password,nickname,email)));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void random(){
        String randPass = randomPasswordGenerator();
        password.setText(randPass);
        error.setText("Your random password is "+randPass);
    }
    @FXML
    public void login() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        fxmlLoader.setController(new LogInViewController(stage));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void signup() throws IOException {
        if (l1.getText().length()<10){
        captchaa=Captcha.randomCaptcha(capStr);
        makeCap(capStr);}

        String Question;
        String Answer;
        if (!q1.isSelected()&&!q2.isSelected()&&!q3.isSelected()){
            error2.setText("Please choose a question");
            return;
        }
        if (answer.getText().isEmpty()){
            error2.setText("Please write your answer");
            return;
        }
        if (captchaA.getText().isEmpty()){
            error2.setText("Please fill the captcha field");
        }
        int Qnumber;
        if (q1.isSelected()){
            Qnumber=1;
        }
        else if (q2.isSelected()){
            Qnumber=2;
        }
        else {
            Qnumber=3;
        }
        if (Qnumber==1){
            Question="What is your father’s name ?";
        }
        else if (Qnumber==2){
            Question="What is your favourite color ?";
        }
        else{
            Question="What was the name of your first pet ?";
        }
        Answer = answer.getText();
        if (!Objects.equals(captchaA.getText(), captchaa)){
            error2.setText("Wrong captcha");
            captchaa=Captcha.randomCaptcha(capStr);
            makeCap(capStr);
            return;
        }
        user.setRecoveryQ(new RecoveryQ(Question,Answer));
        User.addUser(user);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        fxmlLoader.setController(new MainViewController(stage,user));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }

    public void makeCap(String[] capStr){
        l1.setText(capStr[0]);
        l2.setText(capStr[1]);
        l3.setText(capStr[2]);
        l4.setText(capStr[3]);
        l5.setText(capStr[4]);
        l6.setText(capStr[5]);
        l7.setText(capStr[6]);
        l8.setText(capStr[7]);
    }





    ////////////////Controller
    public static String randomPasswordGenerator(){//
        Random random = new Random();
        char a = (char)('a' + random.nextInt(26));
        char A = (char)('A' + random.nextInt(26));
        String signs = "!@#$%^&*";
        char shape = signs.charAt(random.nextInt(signs.length()));
        int length = 8+random.nextInt(13);
        char[] password = new char[length];
        Arrays.fill(password, '|');
        password[random.nextInt(password.length)]=a;
        int i = random.nextInt(password.length);
        while (password[i]!='|'){
            i = random.nextInt(password.length);
        }
        password[i] = A;
        i = random.nextInt(password.length);
        while (password[i]!='|'){
            i = random.nextInt(password.length);
        }
        password[i] = shape;
        for (int j = 0; j < password.length; j++) {
            if (password[j]=='|'){
                String chars = "abcdefghijklmnopqrstuvwxyz" +
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                        "!@#$%^&*";
                password[j]=chars.charAt(random.nextInt(chars.length()));
            }
        }
        return new String(password);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            FileInputStream fileInputStream = new FileInputStream("images\\logBack.jpg");
            Image image = new Image(fileInputStream);
            wall.setFill(new ImagePattern(image));}
        catch (Exception e){}
    }
}