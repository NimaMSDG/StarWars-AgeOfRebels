package com.example.starwars_theageofrebellion;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.stream.StreamSupport;

public class DataBase {

    String url;
    DataBase(String url){
        this.url=url;
        existedCards.add(card);
        existedCards.add(card1);
        existedCards.add(card2);
        existedCards.add(card3);
        existedCards.add(card4);
        existedCards.add(card5);
        existedCards.add(card6);
        existedCards.add(card7);
        existedCards.add(card8);
        existedCards.add(card9);
        existedCards.add(card10);
        existedCards.add(card11);
        existedCards.add(card12);
        existedCards.add(card13);
        existedCards.add(card14);
        existedCards.add(card15);
        existedCards.add(card16);
        existedCards.add(card17);
        existedCards.add(card18);
        existedCards.add(card19);
        existedCards.add(card20);
        existedCards.add(card21);
        existedCards.add(card22);
        existedCards.add(card23);
        existedCards.add(card24);
        existedCards.add(card25);
        existedCards.add(card26);
    }
    DataBase(){
        url = "jdbc:sqlite:GameDB.db";
        existedCards.add(card);
        existedCards.add(card1);
        existedCards.add(card2);
        existedCards.add(card3);
        existedCards.add(card4);
        existedCards.add(card5);
        existedCards.add(card6);
        existedCards.add(card7);
        existedCards.add(card8);
        existedCards.add(card9);
        existedCards.add(card10);
        existedCards.add(card11);
        existedCards.add(card12);
        existedCards.add(card13);
        existedCards.add(card14);
        existedCards.add(card15);
        existedCards.add(card16);
        existedCards.add(card17);
        existedCards.add(card18);
        existedCards.add(card19);
        existedCards.add(card20);
        existedCards.add(card21);
        existedCards.add(card22);
        existedCards.add(card23);
        existedCards.add(card24);
        existedCards.add(card25);
        existedCards.add(card26);
        existedCards.add(card27);
        existedCards.add(card28);

    }
    //n==7 || n==10 || n==11 || n==16 || n==18 || n==20 || n==22 || n==23 || n==28 || n==30
    Card card = new NormalCards("Agility",29,30,1,5000,5,100,Character.Luke,"images\\agility.JPG");
    Card card1 = new NormalCards("Attraction",28,30,1,5000,4,100,Character.Mandalorian,"images\\attraction.JPG");
    Card card2 = new NormalCards("Blind Spot",26,20,2,4000,5,300,Character.BobaFett,"images\\blindSpot.JPG");
    Card card3 = new NormalCards("Bodyguard",24,50,5,5000,6,200,Character.DarthVader,"images\\bodyGuard.JPG");
    Card card4 = new NormalCards("Born To Fly",20,15,1,5000,3,500,Character.BobaFett,"images\\bornToFly.JPG");
    Card card5 = new NormalCards("Fear",25,30,2,2000,4,200,Character.DarthVader,"images\\fear.JPG");
    Card card6 = new NormalCards("Savage",25,30,3,5000,6,300,Character.DarthVader,"images\\savage.JPG");
    Card card7 = new NormalCards("Focalize",18,40,4,5000,10,200,Character.BobaFett,"images\\focalize.JPG");
    Card card8 = new NormalCards("Steamroll",25,40,2,5000,6,300,Character.Mandalorian,"images\\steamroll.JPG");
    Card card9 = new NormalCards("Dark Lord",30,30,1,5000,8,200,Character.DarthVader,"images\\darkLord.JPG");
    Card card10 = new NormalCards("Growing Darkness",27,20,1,5000,6,400,Character.DarthVader,"images\\growingDarkness.JPG");
    Card card11 = new NormalCards("Jedi Killer",20,30,3,5000,5,300,Character.BobaFett,"images\\jediKiller.JPG");
    Card card12 = new NormalCards("Momentum",22,20,4,5000,6,200,Character.BobaFett,"images\\momentum.JPG");
    Card card13 = new NormalCards("Solid Freeze",25,30,2,5000,6,500,Character.DarthVader,"images\\solidFreeze.JPG");
    Card card14 = new NormalCards("Forward",26,30,2,5000,6,200,Character.Mandalorian,"images\\forward.JPG");
    Card card15 = new NormalCards("Tuned Lasers",15,25,5,5000,6,500,Character.Luke,"images\\tunedLasers.JPG");
    Card card16 = new NormalCards("Epicenter",25,30,2,5000,6,300,Character.Luke,"images\\epicenter.JPG");
    Card card17 = new NormalCards("Jedi Fighter",30,25,1,5000,6,200,Character.Luke,"images\\jediFighter.JPG");
    //
    Card card18 = new NormalCards("Stranger",99,99,1,3000,6,200,Character.DarthVader , Card.Type.spell,31,"images\\stranger.JPG");
    //
    Card card19 = new NormalCards("Marksman",99,0,0,5000,6,200,Character.Luke, Card.Type.spell,7,"images\\marksman.JPG");
    Card card20 = new NormalCards("Last gasp",99,0,0,3000,6,200,Character.DarthVader, Card.Type.spell,10,"images\\lastGasp.JPG");
    Card card21 = new NormalCards("Show No Mercy",99,0,0,5000,6,200,Character.BobaFett, Card.Type.spell,11,"images\\showNoMercy.JPG");
    Card card22 = new NormalCards("Power of DarkSide",99,0,0,5000,6,200,Character.DarthVader, Card.Type.spell,16,"images\\powerOfDarkness.JPG");
    Card card23 = new NormalCards("Sith Control",99,0,0,3000,6,200,Character.DarthVader, Card.Type.spell,18,"images\\sithControl.JPG");
    Card card24 = new NormalCards("Engine Upgrade",99,0,0,5000,6,200,Character.Luke, Card.Type.spell,23,"images\\engineUpgrade.JPG");
    //
    Card card25 = new NormalCards("Perfect Defence",99,0,3,7000,100,200,Character.Mandalorian, Card.Type.spell,20,"images\\perfectDefense.JPG");
    Card card26 = new NormalCards("Feel The Force",99,0,2,5000,6,200,Character.Luke, Card.Type.spell,22,"images\\feelTheForce.JPG");
    //
    Card card27 = new NormalCards("Slammed",25,30,5,5000,6,200,Character.Mandalorian,"images\\slammed.JPG");
    Card card28 = new NormalCards("Fearless",20,27,3,3500,4,250,Character.Luke,"images\\fearless.JPG");










    ArrayList<Card> existedCards = new ArrayList<>();

    ArrayList<Card> allCards(){
        ArrayList<Card> allCards = new ArrayList<>();
        for (Card card: existedCards) allCards.add(card);
        for (Card card: adminCards()) allCards.add(card);
        return allCards;
    }

    public Card getCardByName(String name){
        for (Card card : allCards()){
            if (name.equals(card.name))
                return card;
        }
        return null;
    }


    public void addUser(User user) {
        String[] strings = {user.Username, user.Password, user.Nickname,
                user.Email,user.recoveryQ.Question,user.recoveryQ.Answer};
        int[] ints = {user.Level, user.XP, user.Credit};
        String sql = "INSERT INTO userDB(username, password, nickname,email, recoveryQ, recoveryA, Level, Xp, Credit)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection connection = DriverManager.getConnection(url);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < strings.length; i++) {
                preparedStatement.setString(i+1,strings[i]);
            }
            for (int i = 0; i < ints.length; i++) {
                preparedStatement.setInt(i+1+strings.length,ints[i]);
            }

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();}
        catch (Exception e){

        }



    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        String sql = "SELECT * FROM userDB";

        try {
            Connection connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String username = resultSet.getString(1);
                String password = resultSet.getString(2);
                String nickname = resultSet.getString(3);
                String email = resultSet.getString(4);
                String recoveryQ = resultSet.getString(5);
                String recoveryA = resultSet.getString(6);
                int level = resultSet.getInt(7);
                int xp = resultSet.getInt(8);
                int credit = resultSet.getInt(9);
                RecoveryQ recoveryQ1 = new RecoveryQ(recoveryQ,recoveryA);
                User user = new User(username,password,nickname,email,recoveryQ1,level,xp,credit);
                users.add(user);
            }

            resultSet.close();
            connection.close();}
        catch (Exception e){

        }

        return users;
    }

    public int getUserID(String username) {
        ArrayList<User> users = new ArrayList<>();

        int ID=0;

        String sql = "SELECT * FROM userDB";

        try {
            Connection connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                if (username.equals(resultSet.getString(1))){
                    ID = resultSet.getInt(10);
                    break;
                }
            }

            resultSet.close();
            statement.close();
            connection.close();}
        catch (Exception e){

        }

        return ID;
    }

    public void updateUser(User user) {
        String[] strings = {user.Username, user.Password, user.Nickname,
                user.Email};
        int[] ints = {user.Level, user.XP, user.Credit,getUserID(user.Username)};

        String sql = "UPDATE userDB SET " +
                "username = ? ,password = ? ,nickname = ? ,email = ? " +
                ",level = ? ,xp = ? ,credit = ? WHERE ID = ?";

        try {
            Connection connection = DriverManager.getConnection(url);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,strings[0]);
            preparedStatement.setString(2,strings[1]);
            preparedStatement.setString(3,strings[2]);
            preparedStatement.setString(4,strings[3]);
            preparedStatement.setInt(5,ints[0]);
            preparedStatement.setInt(6,ints[1]);
            preparedStatement.setInt(7,ints[2]);
            preparedStatement.setInt(8,ints[3]);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();}
        catch (Exception e){

        }
    }

    public void addCard(Card card,User user){
        String name = card.name;
        int[] ints = {card.level,card.duration,card.price,card.damage,card.upgradeLevel,card.upgradeCost};
        Card.Type type = card.type;
        Character character = card.character;

        int ID=0;
        if (user!=null){
            ID = getUserID(user.Username);
        }

        String sql = "INSERT INTO cardDB(name ,level, duration, price, damage, upgradeLevel, upgradeCost, ID, type, character,imagePath)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        try {
            System.out.println(card.imgPath);;

            Connection connection = DriverManager.getConnection(url);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            for (int i = 0; i < ints.length; i++) {
                preparedStatement.setInt(i+2,ints[i]);
            }
            preparedStatement.setInt(8,ID);
            preparedStatement.setString(9,type.name());
            preparedStatement.setString(10,character.toString());
            preparedStatement.setString(11,card.imgPath);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();}
        catch (Exception e){

        }
    }



    public ArrayList<Card> adminCards(){

        String sql = "SELECT * FROM cardDB";
        ArrayList<Card> cards = new ArrayList<>();


        try {
            Connection connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String name = resultSet.getString(1);
                int level = resultSet.getInt(2);
                int duration = resultSet.getInt(3);
                int damage = resultSet.getInt(4);
                int price = resultSet.getInt(5);
                String characterSTR = resultSet.getString(7);
                Character character = Character.Mandalorian;
                Character[] characters = {Character.None,Character.Luke,
                        Character.Mandalorian,Character.BobaFett,Character.DarthVader};
                for (Character character1:characters){
                    if (character1.toString().equals(characterSTR)){
                        character = character1;
                        break;
                    }
                }
                int upgradeLevel = resultSet.getInt(8);
                int upgradeCost = resultSet.getInt(9);
                int ID = resultSet.getInt(10);
                String imgPath = resultSet.getString(11);
                if (ID==0){
                    Card card = new AdminCards(name,level,damage,duration,price,upgradeLevel,upgradeCost,character,imgPath);
                    cards.add(card);
                }
            }

            resultSet.close();
            connection.close();

            return cards;
        }
        catch (Exception e){}
        return null;
    }

    public ArrayList<Card> playerCards(User user){
        String sql = "SELECT * FROM cardDB";
        ArrayList<Card> cards = new ArrayList<>();


        try {
            Connection connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String name = resultSet.getString(1);
                int level = resultSet.getInt(2);
                int ID = resultSet.getInt(10);


                if (ID==getUserID(user.Username)){
                    Card card = getCardByName(name);
                    //System.out.println(card.name);
                    card.level=level;
                    cards.add(card);
                }
            }

            resultSet.close();
            connection.close();

            return cards;
        }
        catch (Exception e){}
        return null;
    }

    public void updateCard(Card card,String oldName){

        String sql = "UPDATE cardDB SET name=?,level=?,duration=?,damage=?,price=?,upgradeLevel=?,upgradeCost=? " +
                "WHERE name=?";

        try {
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,card.name);
            preparedStatement.setInt(2,card.level);
            preparedStatement.setInt(3,card.duration);
            preparedStatement.setInt(4,card.damage);
            preparedStatement.setInt(5,card.price);
            preparedStatement.setInt(6,card.upgradeLevel);
            preparedStatement.setInt(7,card.upgradeCost);
            preparedStatement.setString(8,oldName);
            preparedStatement.executeUpdate();

        }
        catch (Exception e){}

    }

    public ArrayList<String[]> getHistory(User user,String orderBy) {
        ArrayList<String[]> history = new ArrayList<>();
        String sqlOrder = "";
        if (!Objects.equals(orderBy, "")) sqlOrder =  " ORDER BY "+orderBy;
        if (orderBy.equals("date")) sqlOrder="";
        String sql = "SELECT * FROM historyDB"+sqlOrder;
        try{
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                if (getUserID(user.Username)!=resultSet.getInt(6)) continue;
                String opponent = resultSet.getString(1);
                int level = resultSet.getInt(2);
                String state = resultSet.getString(3);
                String date = resultSet.getString(4);
                String reward = resultSet.getString(5);

                String[] res = new String[]{opponent,String.valueOf(level),state,date,reward};
                history.add(res);

            }
            resultSet.close();
            connection.close();
        }
        catch (Exception e){}

        return history;
    }
    public void addMatchHistory(String opName,int opLevel,String state,String date,String reward,User user){

        String sql = "INSERT INTO historyDB(opponent,level,state,date,reward,ID) VALUES(?,?,?,?,?,?)";
        try{
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,opName);
            preparedStatement.setInt(2,opLevel);
            preparedStatement.setString(3,state);
            preparedStatement.setString(4,date);
            preparedStatement.setString(5,reward);
            preparedStatement.setInt(6,getUserID(user.Username));
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();}
        catch (Exception e){}
    }

    public ArrayList<Card> starterPack(){
        Random rand = new Random();
        ArrayList<Card> storage = new ArrayList<>();
        for (Card caard:existedCards){
            storage.add(caard.clone());
        }
        ArrayList<Card> result = new ArrayList<>();
        while (result.size()!=20){
            int random = rand.nextInt(storage.size());
            result.add(storage.get(random));
            storage.remove(random);
        }
        return result;
    }

    public User getUserByUsername(String username){
        for (User user:getUsers()){
            if (username.equals(user.Username)) return user;
        }
        return null;
    }


}
