package com.example.starwars_theageofrebellion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HistoryViewController implements Initializable {

    Stage stage;
    User user;
    DataBase dataBase = new DataBase();
    ArrayList<String[]> board = new ArrayList<>();
    int PAGE_NUMBER;
    String orderBy="";

    @FXML
    AnchorPane anchorPane;

    HistoryViewController(Stage stage,User user){
        this.stage=stage;
        this.user=user;
    }

    int jStart = 210;
    int jSpace = 64;
    int i1 = 175;
    int i2 = 324;
    int i3 = 424;
    int i4 = 502;
    int i5 = 662;

    Label[][] labels = new Label[5][5];

    @FXML
    Rectangle wall;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            wall.setFill(new ImagePattern(new Image(new FileInputStream("images\\histo.jpg"))));
        }
        catch (Exception e){}
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Label label = new Label();
                label.setLayoutY(jStart+i*jSpace);
                label.setTextFill(Color.WHITE);
                if (j==0) label.setLayoutX(i1);
                if (j==1) label.setLayoutX(i2);
                if (j==2) label.setLayoutX(i3);
                if (j==3) label.setLayoutX(i4);
                if (j==4) label.setLayoutX(i5);
                anchorPane.getChildren().add(label);
                labels[i][j]=label;
            }
        }
        board = dataBase.getHistory(user,"");
        for (int i = 0; i < 5 && i<board.size(); i++) {
            for (int j = 0; j < 5; j++) {
                labels[i][j].setText(board.get(i)[j]);
            }
        }

    }


    @FXML
    public void next(){
        updateHistory(PAGE_NUMBER+1,orderBy);
    }
    @FXML
    public void pre(){
        updateHistory(PAGE_NUMBER-1,orderBy);
    }
    @FXML
    public void name(){
        orderBy="opponent";
        updateHistory(PAGE_NUMBER,orderBy);
    }
    @FXML
    public void level(){
        orderBy="level";
        updateHistory(PAGE_NUMBER,orderBy);
    }
    @FXML
    public void state(){
        orderBy="state";
        updateHistory(PAGE_NUMBER,orderBy);
    }
    @FXML
    public void date(){
        orderBy="date";
        updateHistory(PAGE_NUMBER,orderBy);
    }
    @FXML
    public void reward(){
        orderBy="reward";
        updateHistory(PAGE_NUMBER,orderBy);
    }
    @FXML
    public void back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        fxmlLoader.setController(new MainViewController(stage,user));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }

    public void updateHistory(int pageNo,String orderBy){
        if ((pageNo-1)*5+1>board.size() || pageNo==0){
            return;
        }
        board = dataBase.getHistory(user,orderBy);
        for (int i = (pageNo-1)*5; i < (pageNo-1)*5+5 && i<board.size(); i++) {
            for (int j = 0; j < 5; j++) {
                labels[i%5][j].setText(board.get(i)[j]);
            }
        }
        PAGE_NUMBER = pageNo;
        this.orderBy = orderBy;
    }

}
