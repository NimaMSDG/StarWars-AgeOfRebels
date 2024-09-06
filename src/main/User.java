package com.example.starwars_theageofrebellion;

import java.util.ArrayList;

public class User {
    String Username;
    String Password;
    String Nickname;
    String Email;
    RecoveryQ recoveryQ;

    ArrayList<Card> Deck(){
        return dataBase.playerCards(this);
    }

    int Level;
    int HP(){
        if (100+Level*25>500) return 500;
        return 100+Level*25;
    }
    int XP;
    int Credit;

    int lvl=1;

    private Character character;

    //public Clan clan;

    static DataBase dataBase = new DataBase("jdbc:sqlite:GameDB.db");



    User(String Username,String Password,String Nickname,String Email){
        this.Username=Username;
        this.Password=Password;
        this.Nickname=Nickname;
        this.Email=Email;
        Level = 1;
        XP = 0;
        Credit = 100;
    }
    User(String Username,String Password,String Nickname,String Email,RecoveryQ recoveryQ,int Level,int XP,int Credit){
        this.Username=Username;
        this.Password=Password;
        this.Nickname=Nickname;
        this.Email=Email;
        this.recoveryQ = recoveryQ;
        this.Level = Level;
        this.XP = XP;
        this.Credit = Credit;
    }
    public void setRecoveryQ(RecoveryQ recoveryQ){
        this.recoveryQ=recoveryQ;
    }

    public static User getUserByUsername(String username) {
        for (User user : dataBase.getUsers()) {
            if (user.Username.equals(username)) {
                return user;
            }
        }
        return null;
    }
    public static User getUserByNickname(String nickname) {
        for (User user : dataBase.getUsers()) {
            if (user.Nickname.equals(nickname)) {
                return user;
            }
        }
        return null;
    }
    public static User getUserByEmail(String email) {
        for (User user : dataBase.getUsers()) {
            if (user.Email.equals(email)) {
                return user;
            }
        }
        return null;
    }

    public static void addUser(User user) {
        dataBase.addUser(user);
        for (Card card: dataBase.starterPack()){
            dataBase.addCard(card,user);
        }
    }

    public void buyCard(Card card){
        dataBase.addCard(card,this);
    }

    public int neededXPToUpgrade(){
        return Level*10000;
    }

    public void levelUp(){
        while (XP>=neededXPToUpgrade()){
            XP-=neededXPToUpgrade();
            Level++;
        }
        dataBase.updateUser(this);
    }

    public void changeUsername(String username){
        this.Username = username;
        dataBase.updateUser(this);
    }

    public void changePassword(String password){
        this.Password = password;
        dataBase.updateUser(this);
    }

    public void changeNickname(String nickname){
        this.Nickname = nickname;
        dataBase.updateUser(this);
    }

    public void changeEmail(String email){
        this.Email = email;
        dataBase.updateUser(this);
    }

    public void getXP(int xp){
        XP+=xp;
        levelUp();
        dataBase.updateUser(this);
    }
    public void getCredit(int credit){
        Credit+=credit;
    }









}
