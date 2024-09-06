package com.example.starwars_theageofrebellion;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameViewController implements Initializable {
    //
    Stage stage;
    User user;
    ArrayList<Card> existedCards = new DataBase().existedCards;

    DataBase dataBase = new DataBase();
    Date date;
    boolean aiDepended;
    boolean bossFight;
    boolean levelMode;
    AI ai = new AI();

    int bet;

    GameViewController(Stage stage,User mainUser,Player player1,Player player2,boolean aiDepended,boolean bosFight,boolean LevelMode,int bet){
        try {
            FileInputStream fileInputStream1 = new FileInputStream("images\\empty.png");
            FileInputStream fileInputStream2 = new FileInputStream("images\\filled.png");
            FileInputStream fileInputStream3 = new FileInputStream("images\\IMG_7480.jpg");
            FileInputStream fileInputStream4 = new FileInputStream("images\\he.PNG");
            FileInputStream fileInputStream5 = new FileInputStream("images\\she.PNG");
            empty = new ImagePattern(new Image(fileInputStream1));
            filled = new ImagePattern(new Image(fileInputStream2));
            destroyed = new ImagePattern(new Image(fileInputStream3));
            shield = new ImagePattern(new Image(fileInputStream4));
            heal = new ImagePattern(new Image(fileInputStream5));
        }
        catch (Exception e){}
        this.stage=stage;
        this.user=user;
        this.aiDepended=aiDepended;
        this.bossFight=bosFight;
        this.levelMode=LevelMode;
        this.player1=player1;
        this.player2=player2;
        this.bet=bet;
        int ip=0;
        for (int i=0;i<21;i++){
            if (ip==15) ip=5;
            bossPlate[0][i]=dataBase.existedCards.get(ip);
            ip++;
        }
    }


    int nobat=1;
    //
    private static final double FRAMES_PER_SECOND = 0.5;
    double[] cardNumber = new double[2];
    double[] inBlock = new double[2];
    Rectangle[][] blocks = new Rectangle[2][21];
    double ANCHOR_HEIGHT=310;
    double BLOCK_WIDTH=42;
    double BLOCK_HEIGHT=108;
    double BLOCK_START_X=50;
    double BLOCK_START_Y_UP=ANCHOR_HEIGHT-BLOCK_HEIGHT-3;
    double BLOCK_START_Y_DOWN=ANCHOR_HEIGHT+3;
    double BLOCK_SPACE=2;
    ImagePattern empty;
    ImagePattern filled;
    ImagePattern destroyed;
    ImagePattern shield;
    ImagePattern heal;
    //
    Label[][] cardLabels1 = new Label[6][2];
    Label[][] cardLabels2 = new Label[6][2];
    Rectangle[][] cardRects = new Rectangle[2][6];
    Label[][] blockLevel = new Label[2][21];
    Label[][] segmentLabel = new Label[2][21];
    double CARD_WIDTH=80;
    double CARD_HEIGHT=120;
    double CARD_START_XUP=94;
    double CARD_START_XDOWN=294;
    double CARD_START_YUP=50;
    double CARD_START_YDOWN=450;
    double CARD_SPACE=100-CARD_WIDTH;
    //
    @FXML
    Label hp1;
    @FXML
    Label hp2;
    @FXML
    Rectangle hpl1;
    @FXML
    Rectangle hpl2;
    int P1_HP;
    int P2_HP;
    //
    @FXML
    Label r1;
    @FXML
    Label r2;


    @FXML
    AnchorPane anchorPane;

    @FXML
    Rectangle p11;
    @FXML
    Rectangle p12;
    @FXML
    Rectangle p13;
    @FXML
    Rectangle p14;
    @FXML
    Rectangle p15;
    @FXML
    Rectangle p16;
    @FXML
    Rectangle p21;
    @FXML
    Rectangle p22;
    @FXML
    Rectangle p23;
    @FXML
    Rectangle p24;
    @FXML
    Rectangle p25;
    @FXML
    Rectangle p26;
    //
    @FXML
    Line timeline;
    int speed=1;
    boolean timelineEnded = true;

    //
    @FXML
    ProgressBar bar;
    @FXML
    Button click;
    double step=0.02;
    boolean clicked=false;

    //

    ////end game
    @FXML
            Rectangle grey;
    @FXML
            Rectangle iw;
    @FXML
            Rectangle il;
    @FXML
            Label w;
    @FXML
            Label l;
    @FXML
            Label rw;
    @FXML
            Label rl;
    @FXML
            Label luw;
    @FXML
            Label lul;
    @FXML
            Button back;


    @FXML
            Rectangle mid;



    Dragger dragger=new Dragger();



    //////////
    Player player1 = new Player();
    Player player2 = new Player();
    ArrayList<Card> playercards1=new ArrayList<Card>();
    ArrayList<Card> playercards2=new ArrayList<Card>();
    ArrayList<Card> cardsingame1=new ArrayList<Card>();
    ArrayList<Card> cardsingame2=new ArrayList<Card>();
    Card[][] plate=new Card[2][21];
    Card[][] bossPlate=new Card[2][21];
    ////
    int[][] plateuse = new int[2][21];
    int[][] MAIN_PLATE = new int[2][21];
    Random rand = new Random();
    int nonblock1,nonblock2;

    ////makhfi konndeh
    int nobatPlus;

    //
    @FXML
    Rectangle wall;
    @FXML
    Rectangle pic1;
    @FXML
    Rectangle pic2;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
        wall.setFill(new ImagePattern(new Image(new FileInputStream("images\\download.jpeg"))));
            ImagePattern vader = new ImagePattern(new Image(new FileInputStream("images\\vader.jpg")));
            ImagePattern luke = new ImagePattern(new Image(new FileInputStream("images\\luke.jpg")));
            ImagePattern boba = new ImagePattern(new Image(new FileInputStream("images\\boba.jpg")));
            ImagePattern han = new ImagePattern(new Image(new FileInputStream("images\\han.jpg")));

            if (player1.character==Character.DarthVader) pic1.setFill(vader);
            if (player1.character==Character.Luke) pic1.setFill(luke);
            if (player1.character==Character.BobaFett) pic1.setFill(boba);
            if (player1.character==Character.Mandalorian) pic1.setFill(han);
            if (player2.character==Character.DarthVader) pic2.setFill(vader);
            if (player2.character==Character.Luke) pic2.setFill(luke);
            if (player2.character==Character.BobaFett) pic2.setFill(boba);
            if (player2.character==Character.Mandalorian) pic2.setFill(han);






        }
        catch (Exception e){}

        date = new Date();

        if (bossFight){
            plateBackUp2(bossPlate,plate);
            for (int i=0;i<21;i++){
                plateuse[0][i]=1;
            }
            plateBackUp(plateuse,MAIN_PLATE);
        }


        P1_HP=player1.health;
        P2_HP=player2.health;
        startplate();
        startcardgiven();
        makeMapView();
        makeCardsView();
        updateCardsView();
        makeCardsDraggable(true);
        updateHPBar();
        mid.toFront();
        timeline.toFront();
        bar.toFront();
        click.toFront();
        bar.setVisible(false);
        click.setVisible(false);
        click.setDisable(true);
        this.startTimer();
    }

    Timer timer;
    private void startTimer() {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {


                        //for stay hide
                        if (nobat>=nobatPlus+2){
                            for (int i = 0; i < 2; i++) {
                                for (Rectangle rectangle:cardRects[i]){
                                    rectangle.setVisible(true);
                                }
                            }
                        }

                        String regex = "-Placing card number (.+) in block (.+)";

                        if (nobat!=1 && nobat%8==1 && timelineIndex()!=23 && !timelineEnded){
                            makeCardsDraggable(false);
                            movingTimeline(speed);
                            if (timelineIndex()!=-1 && timelineIndex()!=21){
                                timelinePoint(timelineIndex());
                            }
                            if (timelineIndex()!=-1 && timelineIndex()==21 && !bossFight && !aiDepended){
                                bar.setVisible(true);
                                click.setVisible(true);
                                click.setDisable(false);
                                speed=0;
                                updatePowerBar();
                                if (clicked){
                                    powerBarOnAction(bar.getProgress());
                                    speed=1;
                                    bar.setVisible(false);
                                    click.setVisible(false);
                                    click.setDisable(true);
                                    clicked=false;
                                }
                            }
                            updateHPBar();
                        }
                        if (timelineIndex()==22){
                            timeline.setLayoutX(0);
                            timelineEnded=true;
                            makeCardsDraggable(true);
                            plateBackUp(MAIN_PLATE,plateuse);
                            plate = new Card[2][29];
                            if (bossFight) plateBackUp2(bossPlate,plate);
                            updateMapView();
                        }

                        if (gameOver()){
                            makeCardsDraggable(false);
                            gameOverMode();
                            timer.cancel();
                        }



                        if (nobat%2==1){

                            if (bossFight && timelineEnded){
                                nobat++;
                                bossRandomBuff();
                                makeCardsDraggable(true);
                            }


                            int number = number(CARD_START_XUP,CARD_START_YUP,CARD_WIDTH,CARD_HEIGHT,CARD_SPACE,cardNumber[0],cardNumber[1]);
                            int index = index(BLOCK_START_X,BLOCK_START_Y_UP,BLOCK_WIDTH,BLOCK_HEIGHT,BLOCK_SPACE,inBlock[0],inBlock[1]);
                            if (aiDepended && timelineEnded){
                                String aid = ai.AIDeciotion(plateuse,plate,playercards1,playercards2,player1.health);
                                number = aiOrderToCard(aid);
                                index = aiOrderToPlace(aid);
                                number--;
                                index--;
                                //System.out.println((number+1)+"--"+(index+1));
                                //System.out.println();
                                //card.setWidth(88);
                                //card.setHeight(132);
                                //System.out.println(card.getWidth());
                                //card.setWidth(card.getWidth()/1.1);
                                //card.setHeight(card.getHeight()/1.1);
                            }
                            if (number!=-2 && index!=-2){
                                String input = convertToInput(number,index);
                                System.out.println(input);
                                if (playCard1(getmatcher(regex,input))){
                                    nobat++;
                                    refreshcard1();
                                    updateMap();
                                    updateCardsView();
                                    updateMapView();
                                    inBlock = new double[]{0.0,0.0};
                                    cardNumber = new double[]{0.0,0.0};
                                    makeCardsDraggable(true);
                                }
                            }
                        }
                        if (nobat%2==0){
                            int number = number(CARD_START_XDOWN,CARD_START_YDOWN,CARD_WIDTH,CARD_HEIGHT,CARD_SPACE,cardNumber[0],cardNumber[1]);
                            int index = index(BLOCK_START_X,BLOCK_START_Y_DOWN,BLOCK_WIDTH,BLOCK_HEIGHT,BLOCK_SPACE,inBlock[0],inBlock[1]);
                            if (number!=-2 && index!=-2){
                                String input = convertToInput(number,index);
                                System.out.println(input);
                                if (playCard2(getmatcher(regex,input))){
                                    nobat++;
                                    refreshcard2();
                                    updateMap();
                                    updateCardsView();
                                    updateMapView();
                                    inBlock = new double[]{0.0,0.0};
                                    cardNumber = new double[]{0.0,0.0};
                                    if (nobat%8==1 && nobat!=1){
                                        timelineEnded=false;
                                    }
                                    makeCardsDraggable(true);
                                }
                            }
                        }

                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long)(10.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }







    ///////////Controller

    ///plate makers
    public void startplate(){
        if (bossFight) return;
        int r1= rand.nextInt(21);
        int r2= rand.nextInt(21);
        for(int i=0;i<21;i++){
            plateuse[0][i]=0;
            plateuse[1][i]=0;

        }
        plateuse[0][r1]=-1;
        plateuse[1][r2]=-1;             // 1 ---> 2
        nonblock1=r1;
        nonblock2=r2;

        plateBackUp(plateuse,MAIN_PLATE);
    }
    public void plateBackUp(int[][] original ,int[][] copy){
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
    }
    public void plateBackUp2(Card[][] original ,Card[][] copy){
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
    }
    ///proses input
    static public Matcher getmatcher(String reg, String input){//
        Pattern p=Pattern.compile(reg);
        Matcher m = p.matcher(input);
        m.find();
        return m;
    }
    ///giving cards
    public void startcardgiven(){
        for(int i=0;i<5;i++){
            if (!bossFight){
            int r1= rand.nextInt(player1.cards.size());
            playercards1.add(player1.cards.get(r1));
            player1.cards.remove(r1);}

            int r2= rand.nextInt(player2.cards.size());
            playercards2.add(player2.cards.get(r2));
            player2.cards.remove(r2);
        }
    }
    public void refreshcard1(){//
        if (playercards1.size()>=5) return;
        int r1= rand.nextInt(player1.cards.size());
        playercards1.add(player1.cards.get(r1));
        player1.cards.remove(r1);
        refreshcard1();
    }
    public void refreshcard2(){//
        if (playercards2.size()>=5) return;
        int r2= rand.nextInt(player2.cards.size());
        playercards2.add(player2.cards.get(r2));
        player2.cards.remove(r2);
        refreshcard2();
    }

    ///playing card
    public boolean playCard1(Matcher matcher){//
        int number = Integer.parseInt(matcher.group(1));
        int index = Integer.parseInt(matcher.group(2));
        number--;index--;

        int type=1;
        if (index==-1){
            type = 2;
        }

        Card card = playercards1.get(number);
        if (isspecial(card)&&type==1){
            System.out.println("This card is spell");
            return false;
        }
        if (!isspecial(card)&&type==2){
            System.out.println("This card isn't spell");
            return false;
        }

        if (number>playercards1.size()-1 || number<0){
            System.out.println("Card number out of range");
            return false;
        }
        if (index>21-1 || index<-1){
            System.out.println("Block index out of range");
            return false;
        }

        if (type==1){
            index++;
            if(playcardaccess1(card,index)){
                //playercards1.remove(x);
                playingcard1(card,index);
                System.out.println("Card "+card.name+" played");
                return true;
            }
            else{
                System.out.println("The input place is not empty");
                return false;
            }
        } else {

            playingcard1(card,-1);
            specialeffects(card,1);
            //playercards1.remove(x);
            System.out.println("Card "+card.name+" played");

            return true;
        }
    }

    public boolean playCard2(Matcher matcher){//
        int number = Integer.parseInt(matcher.group(1));
        int index = Integer.parseInt(matcher.group(2));
        number--;index--;

        int type=1;
        if (index==-1){
            type = 2;
        }

        Card card = playercards2.get(number);
        if (isspecial(card)&&type==1){
            System.out.println("This card is spell");
            return false;
        }
        if (!isspecial(card)&&type==2){
            System.out.println("This card isn't spell");
            return false;
        }

        if (number>playercards2.size()-1 || number<0){
            System.out.println("Card number out of range");
            return false;
        }
        if (index>21-1 || index<-1){
            System.out.println("Block index out of range");
            return false;
        }

        if (type==1){
            index++;
            if(playcardaccess2(card,index)){
                //playercards1.remove(x);
                playingcard2(card,index);
                System.out.println("Card "+card.name+" played");
                return true;
            }
            else{
                System.out.println("The input place is not empty");
                return false;
            }
        } else {
            playingcard2(card,-1);
            specialeffects(card,2);
            //playercards1.remove(x);

            System.out.println("Card "+card.name+" played");

            return true;
        }
    }
    public boolean playcardaccess1(Card c,int n){//
        boolean access=true;
        if(n>=0){
            for(int i=0;i<c.duration;i++){
                if(plateuse[0][i+n-1]!=0){
                    access=false;
                }
            }
            if(!access){
                System.out.println("The input place is not empty");
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return true;
        }

    }
    public boolean playcardaccess2(Card c,int n){//
        boolean access=true;
        if(n>=0){
            for(int i=0;i<c.duration;i++){
                if(plateuse[1][i+n-1]!=0){
                    access=false;
                }
            }
            if(!access){
                System.out.println("The input place is not empty");
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return true;
        }

    }
    public void playingcard1(Card c,int n){//
        if(n>=0){
            if(!isspecial(c) || c.number==20 || c.number==22){
                if (c.character==player1.character){
                    c.damage+=c.duration*2;
                }
                if (c.number==31){
                    c.damage=rand.nextInt(50)+15;
                    c.level=rand.nextInt(30)+20;
                }
                for(int i=0;i<c.duration;i++){

                    plate[0][i+n-1]=c.clone();
                    plateuse[0][i+n-1]=1;

                }
            }
        }
        playercards1.remove(c);

        if(!isspecial(c)){
            cardsingame1.add(c);
        }
    }
    public void playingcard2(Card c,int n){//
        if(n>=0){
            if(!isspecial(c) || c.number==20 || c.number==22){
                if (c.character==player2.character){
                    c.damage+=c.duration*2;
                }
                if (c.number==31){
                    c.damage=rand.nextInt(50)+15;
                    c.level=rand.nextInt(30)+20;
                }
                for(int i=0;i<c.duration;i++){
                    plate[1][i+n-1]=c.clone();
                    plateuse[1][i+n-1]=1;
                }}
        }
        playercards2.remove(c);

        if(!isspecial(c)){
            cardsingame2.add(c);
        }
    }
    static boolean isspecial(Card c){
        int n=c.number;
        if(n==7 || n==10 || n==11 || n==16 || n==18 || n==23 || n==28 || n==30 ){
            return true;
        }
        else{
            return false;
        }
    }
    public void specialeffects(Card c,int n){//
        //تظعیف کنندع حریف
        if(c.number==7){
            if(n==2){
                int r1=rand.nextInt(playercards1.size());
                int r2=(rand.nextInt(playercards1.size()));
                playercards1.get(r1).level-=2;
                playercards1.get(r2).damage-=2*playercards1.get(r2).duration;
                System.out.println("Card "+playercards1.get(r1).name+"'s level decreasd");
                System.out.println("Card "+playercards1.get(r2).name+"'s damage decreasd");}
            else if(n==1){
                int r1=rand.nextInt(playercards2.size());
                int r2=(rand.nextInt(playercards2.size()));
                playercards2.get(r1).level-=2;
                playercards2.get(r2).damage-=2*playercards2.get(r2).duration;
                System.out.println("Card "+playercards2.get(r1).name+"'s level decreasd");
                System.out.println("Card "+playercards2.get(r2).name+"'s damage decreasd");
            }
        }
        //تغییر دهنده مکان
        else if(c.number==18){

            ArrayList<Integer> freeblocks1=new ArrayList<Integer>();
            for(int i=0;i<21;i++){
                if(plateuse[0][i]==0){
                    freeblocks1.add(i);
                }
            }
            int r1= freeblocks1.get(rand.nextInt(freeblocks1.size()));
            plateuse[0][nonblock1]=0;
            plateuse[0][r1]=-1;
            nonblock1=r1;
            ArrayList<Integer> freeblocks2=new ArrayList<Integer>();
            for(int i=0;i<21;i++){
                if(plateuse[1][i]==0){
                    freeblocks2.add(i);
                }
            }
            int r2= freeblocks2.get(rand.nextInt(freeblocks2.size()));
            plateuse[1][nonblock2]=0;
            plateuse[1][r2]=-1;
            nonblock2=r2;
        }
        //tamir
        else if(c.number==23){
            if(n==1){
                plateuse[0][nonblock1]=0;
                nonblock1=-1;
            }
            else if(n==2){
                plateuse[1][nonblock2]=0;
                nonblock2=-1;
            }
        }
        //hazf card//
        else if(c.number==10){
            if(n==1){
                refreshcard1();
                int r1= rand.nextInt(playercards2.size());
                playercards1.add(playercards2.get(r1));
                playercards2.remove(r1);
            }
            else if(n==2){
                refreshcard2();
                int r1= rand.nextInt(playercards1.size());
                playercards2.add(playercards1.get(r1));
                playercards1.remove(r1);
            }
        }
        //ghavi kardan
        else if(c.number==16){
            if(n==1){
                if (cardsingame1.isEmpty()) return;
                int r=rand.nextInt(cardsingame1.size());
                String name = cardsingame1.get(r).name;
                for (Card card : cardsingame1){
                    if (card.name.equals(name)) card.level+=3;
                }
                System.out.println("Card "+cardsingame1.get(r).name+" for player 1 buffed");
            }
            else if(n==2){
                if (cardsingame2.isEmpty()) return;
                int r=rand.nextInt(cardsingame2.size());
                String name = cardsingame2.get(r).name;
                for (Card card : cardsingame2){
                    if (card.name.equals(name)) card.level+=3;
                }
                System.out.println("Card "+cardsingame2.get(r).name+" for player 2 buffed");
            }
        }
        //کم کننده راند
        else if (c.number==11) {
            nobat+=2;
        }
        //مخفی کننده
        else if (c.number==30) {
            nobatPlus=nobat;
            if (n==1){
                for (int i = 0; i < playercards1.size(); i++) {
                    cardRects[0][i].setVisible(false);
                }

            }
            if (n==2){
                for (int i = 0; i < playercards2.size(); i++) {
                    cardRects[1][i].setVisible(false);
                }
            }
        }
    }

    /// after playing calculations
    public void updateMap(){
        for (int i = 0; i < 21; i++) {
            if (plateuse[0][i]!=0 && plateuse[1][i]!=0){
                if (plateuse[0][i]!=-1 && plateuse[1][i]!=-1){
                    if (plateuse[0][i]!=-2 || plateuse[1][i]!=-2){
                        Card card1 = plate[0][i];
                        Card card2 = plate[1][i];
                        if ((card1.number==20 || card1.number==22) && (card2.number!=22 && card2.number!=20)){
                            plate[1][i].damage = 0;
                            continue;
                        }
                        if ((card1.number!=20 && card1.number!=22) && (card2.number==22 || card2.number==20)){
                            plate[1][i].damage = 0;
                            continue;
                        }
                        if ((card1.number==20 || card1.number==22) && (card2.number==22 || card2.number==20)){
                            continue;
                        }
                        if (card1.level>card2.level){
                            plate[1][i].damage = 0;
                        }else if (card1.level<card2.level){
                            plate[0][i].damage = 0;
                        }
                        else {plate[0][i].damage = 0;plate[1][i].damage=0;}
                    }
                }
            }
        }
    }
    /// timeline controller
    public int timelineIndex(){
        for (int i = 1; i <= 23; i++) {
            if (timeline.getLayoutX()==BLOCK_START_X+(i-1)*(BLOCK_WIDTH+BLOCK_SPACE)){
                return i-1;
            }
        }
        return -1;
    }
    public void movingTimeline(int speed){
        timeline.setLayoutX(timeline.getLayoutX()+speed);
    }
    public void timelinePoint(int i){
        if (i>=21) return;
        boolean c1 = plateuse[0][i]==1;
        boolean c2 = plateuse[1][i]==1;
        if (c1) player2.health-=plate[0][i].segmentDamage();
        if (c2) player1.health-=plate[1][i].segmentDamage();
        if (c1){
            if (plate[0][i].number==22) player1.health+=25;
        }
        if (c2){
            if (plate[1][i].number==22) player2.health+=25;
        }
    }
    /// power bar controller
    public void powerBarOnAction(double percent){
        if (player1.health<player2.health){
            player2.health-=(int)(20.0*percent);
        }
        else {
            player1.health-=(int)(20.0*percent);
        }
    }
    ///game over
    boolean gameOver() {return player1.health*player2.health<=0;}

    ////ai
    public int aiOrderToPlace(String aiD){
        String[] split = aiD.split("_");
        return Integer.parseInt(split[1])+1;
    }
    public int aiOrderToCard(String aiD){
        String[] split = aiD.split("_");
        return Integer.parseInt(split[0])+1;
    }













    ////////////// View

    /// gathering input
    public int index(double startX,double startY,double width,double height,double fasele,double x,double y){
        if (x>startX+(-1)*(width+fasele) && x<startX+(-1)*(width+fasele)+width){
            if (y>startY&&y<startY+height){
                return -1;
            }
        }
        for (int i = 0; i < 21; i++) {
            if (x>startX+i*(width+fasele) && x<startX+i*(width+fasele)+width){
                if (y>startY&&y<startY+height){
                    return i;
                }
            }
        }
        return -2;
    }
    public int number(double startX,double startY,double width,double height,double fasele,double x,double y){
        for (int i = 0; i < 6; i++) {
            if (x>startX+i*(width+fasele) && x<startX+i*(width+fasele)+width){
                if (y>startY&&y<startY+height){
                    return i;
                }
            }
        }
        return -2;
    }
    public String convertToInput(int number,int block){
        return "-Placing card number "+(number+1)+" in block "+(block+1);
    }
    ///make view
    public void makeMapView(){
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 21; j++) {
                Rectangle block;
                if (i==0) block=new Rectangle(BLOCK_START_X+j*(BLOCK_WIDTH+BLOCK_SPACE),BLOCK_START_Y_UP,BLOCK_WIDTH,BLOCK_HEIGHT);
                else block=new Rectangle(BLOCK_START_X+j*(BLOCK_WIDTH+BLOCK_SPACE),BLOCK_START_Y_DOWN,BLOCK_WIDTH,BLOCK_HEIGHT);
                block.setFill(empty);
                if (plateuse[i][j]==-1) block.setFill(Color.BLACK);
                block.setStroke(Color.BLACK);
                block.setStrokeWidth(5);
                block.setStrokeType(StrokeType.OUTSIDE);
                blocks[i][j]=block;
                anchorPane.getChildren().add(block);
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 21; j++) {
                Label level = new Label();
                level.setLayoutX(BLOCK_START_X+j*(BLOCK_WIDTH+BLOCK_SPACE)+BLOCK_WIDTH/2.0-8);
                if (i==0) level.setLayoutY(BLOCK_START_Y_UP+20);
                else level.setLayoutY(BLOCK_START_Y_DOWN+20);
                level.setTextFill(Color.CYAN);
                blockLevel[i][j]=level;
                anchorPane.getChildren().add(level);

                Label damage = new Label();
                damage.setLayoutX(level.getLayoutX()+2);
                if (i==0) damage.setLayoutY(BLOCK_START_Y_UP+70);
                else damage.setLayoutY(BLOCK_START_Y_DOWN+70);
                damage.setTextFill(Color.YELLOW);
                segmentLabel[i][j]=damage;
                anchorPane.getChildren().add(damage);
            }
        }
        int x2 = 4-((nobat%8)/2-1);
        if (nobat%2==1) x2 = 4-((nobat%8 + 1)/2 -1);
        r2.setText(String.valueOf(x2));
        r1.setText(String.valueOf(9-x2-(nobat%8)));
    }
    public void makeCardsView(){
        if (bossFight){
            p11.setVisible(false);
            p12.setVisible(false);
            p13.setVisible(false);
            p14.setVisible(false);
            p15.setVisible(false);
            p16.setVisible(false);
        }
        cardRects[0][0]=p11;cardRects[0][1]=p12;
        cardRects[0][2]=p13;cardRects[0][3]=p14;
        cardRects[0][4]=p15;cardRects[0][5]=p16;
        cardRects[1][0]=p21;cardRects[1][1]=p22;
        cardRects[1][2]=p23;cardRects[1][3]=p24;
        cardRects[1][4]=p25;cardRects[1][5]=p26;
        for (int i=0;i<6 && !bossFight;i++) {
            cardRects[0][i].toFront();
            Label level = new Label();
            level.setLayoutX(cardRects[0][i].getLayoutX()+3);
            level.setLayoutY(cardRects[0][i].getLayoutY()+11);
            level.setTextFill(Color.WHITE);
            //level.setText("45");//
            cardLabels1[i][0]=level;
            Label damage = new Label();
            damage.setLayoutX(level.getLayoutX()+60);
            damage.setLayoutY(level.getLayoutY());
            damage.setTextFill(Color.WHITE);

            //damage.setText("32");//
            cardLabels1[i][1]=damage;
            anchorPane.getChildren().add(level);
            anchorPane.getChildren().add(damage);
        }
        for (int i=0;i<6;i++) {
            cardRects[1][i].toFront();
            Label level = new Label();
            level.setLayoutX(cardRects[1][i].getLayoutX()+3);
            level.setLayoutY(cardRects[1][i].getLayoutY()+11);
            level.setTextFill(Color.WHITE);
            //level.setText("45");//
            cardLabels2[i][0]=level;
            Label damage = new Label();
            damage.setLayoutX(level.getLayoutX()+60);
            damage.setLayoutY(level.getLayoutY());
            damage.setTextFill(Color.WHITE);

            //damage.setText("32");//
            cardLabels2[i][1]=damage;
            anchorPane.getChildren().add(level);
            anchorPane.getChildren().add(damage);
        }
    }
    public void makeCardsDraggable(boolean canDrag){
        for (int i = 0; i < 6; i++) {
            new Dragger().makeDragger(cardRects[0][i],cardLabels1[i][0],cardLabels1[i][1],cardNumber,inBlock,nobat,canDrag);
        }
        for (int i = 0; i < 6; i++) {
            new Dragger().makeDragger(cardRects[1][i],cardLabels2[i][0],cardLabels2[i][1],cardNumber,inBlock,nobat,canDrag);
        }
    }

    ///update view
    public void updateCardsView(){
        for (int i=0;i<6 && !bossFight;i++){
            if (i<playercards1.size()){
                Card card = playercards1.get(i);
                cardRects[0][i].setFill(card.image);

                cardLabels1[i][0].setText(String.valueOf(card.level));
                cardLabels1[i][1].setText(String.valueOf(card.damage));

                cardRects[0][i].setWidth(CARD_WIDTH);
                cardRects[0][i].setHeight(CARD_HEIGHT);
            }
            else {
                cardRects[0][i].setWidth(0);
                cardRects[0][i].setHeight(0);
                cardLabels1[i][0].setText("");
                cardLabels1[i][1].setText("");
            }
        }
        for (int i=0;i<6;i++){
            if (i<playercards2.size()){
                Card card = playercards2.get(i);
                cardRects[1][i].setFill(card.image);

                cardLabels2[i][0].setText(String.valueOf(card.level));
                cardLabels2[i][1].setText(String.valueOf(card.damage));
                cardRects[1][i].setWidth(CARD_WIDTH);
                cardRects[1][i].setHeight(CARD_HEIGHT);
            }
            else {
                cardRects[1][i].setWidth(0);
                cardRects[1][i].setHeight(0);
                cardLabels2[i][0].setText("");
                cardLabels2[i][1].setText("");
            }
        }
    }
    public void updateMapView(){
        //level
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 21; j++) {
                Card myCard = plate[i][j];
                if (myCard==null) {
                    blockLevel[i][j].setText("");
                    segmentLabel[i][j].setText("");
                    continue;
                }
                if (myCard.number!=20 && myCard.number!=22){
                    if(myCard.segmentDamage()!=0) blockLevel[i][j].setText(String.valueOf(myCard.level));
                    else blockLevel[i][j].setText("");
                }
            }
            int x2 = 4-((nobat%8)/2-1);
            if (nobat%2==1) x2 = 4-((nobat%8 + 1)/2 -1);
            r2.setText(String.valueOf(x2));
            r1.setText(String.valueOf(9-x2-(nobat%8)));
        }
        //segment damage
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 21; j++) {
                Card myCard = plate[i][j];
                if (myCard==null) continue;
                if (myCard.number!=20 && myCard.number!=22 && !(bossFight && i==0)){
                    if(myCard.segmentDamage()!=0) segmentLabel[i][j].setText(String.valueOf(myCard.segmentDamage()));
                    else segmentLabel[i][j].setText("");
                }
            }
        }
        //block image
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 21; j++) {
                Card myCard = plate[i][j];
                Rectangle block = blocks[i][j];
                if (plateuse[i][j]==-1) block.setFill(Color.BLACK);
                else if (myCard==null) block.setFill(empty);
                else if (myCard.damage!=0 && myCard.number!=20 && myCard.number!=22){
                    block.setFill(filled);
                }
                else if (myCard.number==20){
                    block.setFill(shield);
                }
                else if (myCard.number==22){
                    block.setFill(heal);
                }
                else if (myCard.damage==0 && myCard.number!=20 && myCard.number!=22){
                    block.setFill(destroyed);
                }
            }
        }

        updateHPBar();
    }
    public void updateHPBar(){
        hp1.setText(String.valueOf(player1.health));
        hp2.setText(String.valueOf(player2.health));
        hpl1.setWidth(((double)player1.health/(double) P1_HP )* 207.0);
        hpl1.setLayoutX(714.0+207.0-((double)player1.health/(double) P1_HP )* 207.0);
        hpl2.setWidth(((double)player2.health/(double) P2_HP )* 207.0);
    }
    public void updatePowerBar(){
        if (bar.getProgress()<0) step=-step;
        if (bar.getProgress()>1) step=-step;
        bar.setProgress(bar.getProgress()+step);
    }
    ///game over view
    int bonus1=0,bonus2=0;
    public void gameOverMode(){
        grey.toFront();
        w.toFront();
        l.toFront();
        il.toFront();
        iw.toFront();
        rw.toFront();
        rl.toFront();
        back.toFront();
        if (player1.health>player2.health){
            try{
                ImagePattern vader = new ImagePattern(new Image(new FileInputStream("images\\vader.jpg")));
                ImagePattern luke = new ImagePattern(new Image(new FileInputStream("images\\luke.jpg")));
                ImagePattern boba = new ImagePattern(new Image(new FileInputStream("images\\boba.jpg")));
                ImagePattern han = new ImagePattern(new Image(new FileInputStream("images\\han.jpg")));
            if (player1.character==Character.DarthVader) iw.setFill(vader);
            if (player1.character==Character.Luke) iw.setFill(luke);
            if (player1.character==Character.BobaFett) iw.setFill(boba);
            if (player1.character==Character.Mandalorian) iw.setFill(han);
            if (player2.character==Character.DarthVader) il.setFill(vader);
            if (player2.character==Character.Luke) il.setFill(luke);
            if (player2.character==Character.BobaFett) il.setFill(boba);
            if (player2.character==Character.Mandalorian) il.setFill(han);}
            catch (Exception e){}
        }
        else {
            try{
                ImagePattern vader = new ImagePattern(new Image(new FileInputStream("images\\vader.jpg")));
                ImagePattern luke = new ImagePattern(new Image(new FileInputStream("images\\luke.jpg")));
                ImagePattern boba = new ImagePattern(new Image(new FileInputStream("images\\boba.jpg")));
                ImagePattern han = new ImagePattern(new Image(new FileInputStream("images\\han.jpg")));
                if (player2.character==Character.DarthVader) iw.setFill(vader);
                if (player2.character==Character.Luke) iw.setFill(luke);
                if (player2.character==Character.BobaFett) iw.setFill(boba);
                if (player2.character==Character.Mandalorian) iw.setFill(han);
                if (player1.character==Character.DarthVader) il.setFill(vader);
                if (player1.character==Character.Luke) il.setFill(luke);
                if (player1.character==Character.BobaFett) il.setFill(boba);
                if (player1.character==Character.Mandalorian) il.setFill(han);}
            catch (Exception e){}
        }
        int h1=player1.health,h2=player2.health;
        int b1=bonus1,b2=bonus2;
        User user1=dataBase.getUserByUsername(player1.username),user2=dataBase.getUserByUsername(player2.username);
        User winner=user1,loser=user2;
        if (player1.health<=0){
            h1=player2.health;
            h2=player1.health;
            b1=bonus2;
            b2=bonus1;
            winner=user2;
            loser=user1;
        }
        int wXP=0,lXP=0;
        if (!levelMode){
        wXP=(loser.HP()-h2)*10+bonus1;
        lXP=(winner.HP()-h1)*10+bonus2;

        winner.getXP(wXP);
        loser.getXP(lXP);
        winner.getCredit(wXP/2+bet);
        loser.getCredit(lXP/2);}
        else {
            wXP=2000;
        }

        String wReward="+"+wXP+"XP / +"+(wXP/2)+"Credit";
        String lReward="+"+lXP+"XP / +"+(lXP/2)+"Credit";

        if (bet>0){
            wReward="+"+wXP+"XP / +"+(wXP/2)+"Credit / +"+bet+"Credit for bet";
        }

        if (!levelMode){
        dataBase.addMatchHistory(loser.Username,loser.Level,"WIN",String.valueOf(date),wReward,winner);
        dataBase.addMatchHistory(winner.Username,winner.Level,"LOSE",String.valueOf(date),lReward,loser);}
        grey.setVisible(true);
        iw.setVisible(true);
        il.setVisible(true);
        rw.setVisible(true);
        rl.setVisible(true);
        w.setVisible(true);
        l.setVisible(true);
        back.setVisible(true);


        rw.setText(wReward);
        rl.setText(lReward);


        if (levelMode && player2.username.equals(winner.Username)){
            int level=0;
            if (player1.character==Character.Mandalorian) level=1;
            if (player1.character==Character.BobaFett) level=2;
            if (player1.character==Character.Luke) level=3;
            if (player1.character==Character.DarthVader) level=4;
            if (winner.lvl==level) winner.lvl++;
        }
    }






    /////////////////////////
    @FXML
    public void click(){
        clicked=true;
    }
    @FXML
    public void back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        fxmlLoader.setController(new MainViewController(stage,user));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        stage.setScene(scene);
        stage.show();
    }


    public void bossRandomBuff(){
        int r1 = rand.nextInt(21);
        int r2 = rand.nextInt(21);
        if (r2==r1) r2 = rand.nextInt(21);
        plate[0][r1].level+=2;
        plate[0][r2].level+=2;
        System.out.println("card number "+(r1+1)+" and "+(r2+1)+" buffed");
    }




}
