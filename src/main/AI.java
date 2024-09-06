package com.example.starwars_theageofrebellion;

import java.util.ArrayList;
import java.util.Random;

public class AI {

    int [][] freeSpaces = new int[2][21];
    Random random = new Random();

    public String AIDeciotion(int[][] plateuse,Card[][] plate, ArrayList<Card> cards,ArrayList<Card> cards2,int HP){
        recognizeFreeSpaces(plateuse);
        int chance = random.nextInt(10);
        String aid = destruction(plate,cards);
        if (chance<3 && canPlay(aid))  return aid;
        aid = fillTheHofreh(plateuse,cards);
        if (chance<5&& canPlay(aid)) return aid;
        aid = spellPlay(cards,cards2,plate,HP);
        if (chance<7&& canPlay(aid)) return aid;
        aid = spellPlay(cards,cards2,plate,HP);
        return aid;
    }

    public void recognizeFreeSpaces(int[][] plateuse){

        for (int i = 0; i < 21; i++) {
            int fs = 0;
            if (plateuse[0][i]==0){
                for (int j = i; j < 21; j++) {
                    if (plateuse[0][j]==0) fs++;
                    else break;
                }
            }
            freeSpaces[0][i]=fs;
        }
    }

    public ArrayList<Card> cardsCanPlace(int duration,ArrayList<Card> AiCards){
        ArrayList<Card> cards1 = new ArrayList<>();
        for (Card card:AiCards){
            if (card.duration<=duration && card.type!= Card.Type.spell){
                cards1.add(card);
            }
        }
        return cards1;
    }

    public int getIndexByName(String name,ArrayList<Card> cards){
        for (int i=0;i<cards.size();i++) {
            if (cards.get(i).name.equals(name)) return i;
        }
        return -1;
    }

    public String destruction(Card[][] plate,ArrayList<Card> AiCards){
        int finalCardIndex = -2;
        int finalPlace = -2;
        for (int i = 0; i < 21; i++) {
            if (plate[1][i]!=null && freeSpaces[0][i]!=0){
                Card enemyCard = plate[1][i];
                int duration = freeSpaces[0][i];
                ArrayList<Card> cardCanPlay = cardsCanPlace(duration,AiCards);
                ArrayList<Card> finalOptions = new ArrayList<>();
                for (Card card:cardCanPlay){
                    if (card.level>enemyCard.level) {
                        finalOptions.add(card);
                    }
                }
                if (finalOptions.isEmpty()) continue;
                int index=0;
                int dur = finalOptions.get(0).duration;
                for (int j = 1; j < finalOptions.size(); j++) {
                    if (finalOptions.get(j).duration>dur){
                        dur = finalOptions.get(j).duration;
                        index=j;
                    }
                }
                finalCardIndex = getIndexByName(finalOptions.get(index).name,AiCards);
                finalPlace = i;
                break;
            }
        }
        return String.valueOf(finalCardIndex)+"_"+String.valueOf(finalPlace);
    }


    public String fillTheHofreh(int[][] plateuse,ArrayList<Card> aiCards){
        int index = -2;
        for (int i = 0; i < 21; i++) {
            if (plateuse[1][i]==-1 && plateuse[0][i]==0){
                index = i;
            }
        }
        if (index==-2) return "-1_-1";
        int minLevelIndex = -2;
        int level = 100;
        int duration = 6;
        for (int i = 0; i < aiCards.size(); i++) {
            Card card = aiCards.get(i);
            if (card.type== Card.Type.spell) continue;
            if (card.level<=level && card.duration<=3 && card.duration<=freeSpaces[0][index]){
                level = card.level;
                minLevelIndex = i;
                duration=card.duration;
            }
        }

        return String.valueOf(minLevelIndex)+"_"+String.valueOf(index);
    }

    public String playRandomly(ArrayList<Card> aiCards){
        int maxLevelIndex = 0;
        int maxLevel = 0;
        for (int i = 1; i < aiCards.size(); i++) {
            Card card = aiCards.get(i);
            if (card.level>maxLevel && card.type!= Card.Type.spell){
                maxLevel=card.level;
                maxLevelIndex = i;
            }
        }
        int placeIndex = -2;
        for (int i = 0; i < 21; i++) {
            if (freeSpaces[0][i]>=aiCards.get(maxLevelIndex).duration){
                placeIndex = i;
                break;
            }
        }
        return String.valueOf(maxLevelIndex)+"_"+String.valueOf(placeIndex);
    }

    public String spellPlay(ArrayList<Card> aiCards,ArrayList<Card> enemyCards,Card[][] plate,int HP){

        if (containSpell(22,aiCards)){
            if (HP/2<sumOfDamage(plate[1])){
                for (int i=0;i<21;i++){
                    if (canPutThisCardHere(2,i)) return getIndexByNumber(22,aiCards)+"_"+i;
                }
            }
        }
        if (containSpell(20,aiCards)){
            if (HP/2<sumOfDamage(plate[1])){
                for (int i=0;i<21;i++){
                    if (canPutThisCardHere(3,i)) return getIndexByNumber(20,aiCards)+"_"+i;
                }
            }
        }
        if (containSpell(7,aiCards)){
            int ai = 0,e = 0;
            for (Card card:aiCards) {
                if (card.type!= Card.Type.spell) ai+=card.damage;
            }
            for (Card card:enemyCards) {
                if (card.type!= Card.Type.spell) e+=card.damage;
            }
            if (e>ai) return getIndexByNumber(7,aiCards)+"_"+-1;
        }
        if (containSpell(10,aiCards)){
            int ai = 0,e = 0;
            for (Card card:aiCards) {
                if (card.type!= Card.Type.spell) ai+=card.damage;
            }
            for (Card card:enemyCards) {
                if (card.type!= Card.Type.spell) e+=card.damage;
            }
            if (e>ai) return getIndexByNumber(10,aiCards)+"_"+-1;
        }
        if (containSpell(16,aiCards)){
            int ai = 0,e = 0;
            for (Card card:aiCards) {
                if (card.type!= Card.Type.spell) ai+=card.damage;
            }
            for (Card card:enemyCards) {
                if (card.type!= Card.Type.spell) e+=card.damage;
            }
            if (e>ai) return getIndexByNumber(16,aiCards)+"_"+-1;
        }
        if (containSpell(18,aiCards)){
            return getIndexByNumber(18,aiCards)+"_"+-1;
        }
        if (containSpell(23,aiCards)){
            return getIndexByNumber(23,aiCards)+"_"+-1;
        }
        return -2+"_"+-2;

    }

    public boolean canPlay(String aid){
        return !aid.contains("-2");
    }

    public boolean canPutThisCardHere(int duration,int index){
        return  (freeSpaces[0][index]>=duration);
    }

    public boolean containSpell(int number,ArrayList<Card> cards){
        for (Card card:cards){
            if (card.number==number) return true;
        }
        return false;
    }

    public int getIndexByNumber(int numberOfSpell,ArrayList<Card> cards){
        for (int i=0;i<cards.size();i++){
            if (cards.get(i).number==numberOfSpell) return i;
        }
        return -1;
    }

    public int sumOfDamage(Card[] cards){
        int sum=0;
        for (Card card : cards){
            if (card!=null) sum+=card.segmentDamage();
        }
        return sum;
    }
}
