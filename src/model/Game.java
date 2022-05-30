package model;

import controller.Controller;
import gui.MainFrame;
import resources.GsonAndFile;

import javax.swing.*;
import java.util.ArrayList;


public class Game {
    private static Game instance;
    int turn=0;
    public static Game getInstance(){
        if(instance==null) instance = new Game();
        return instance;
    }
    public static ArrayList<Card> deck = new ArrayList<>();
    private Game (){
        initial();


    }
     public void iteratePlayer() {
         Player alivePlayer = null;
         int aliveNumber = 0;
         for (Player p :
                 Player.list) {
             if (p.isAlive()) {
                 aliveNumber++;
                 alivePlayer = p;
             }
         }
         if (aliveNumber == 1) {
             JOptionPane.showMessageDialog(MainFrame.mainFrame, alivePlayer.name + "  wins!");
             Controller.setButtonsEnabled(Player.get(1));
             return;
         }

         turn++;
         turn %= 4;
         Player player = Player.list.get(turn);
         if (player.isAlive()) {
             Controller.setButtonsEnabled(player);
             if(player instanceof BotPlayer) {
                 ((BotPlayer) player).play();
             }
         } else iteratePlayer();
     }


    void initial(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                new Card(CardType.getTypeById(i));
            }
        }
//        new HumanPlayer();
//        for (int i = 0; i < 3; i++) {
//            new BotPlayer(BotPlayer.Type.getTypeById(i));
//        }
        Player.list=GsonAndFile.getInstance().getPlayersList();
        for (int i = 0; i < 4; i++) {
            Player player = Player.get(i);
            for (int j = 0; j < 2; j++) {
//                boolean hasOwner = false;
//                while (! hasOwner) {
//                    int random = new Random().nextInt(15);
//                    Card card = Card.list.get(random);
//                    if(card.playerId==-1){
//                        giveCardToPlayer(player,card);
//                        hasOwner = true;
//                    }
//                }
                Card card = Card.getCardById(player.cardsId.get(j));
                giveCardToPlayer(player,card);
            }
        }
    }


    public void giveCardToPlayer(Player player,Card card){
        deck.remove(card);
        if(! player.cardsId.contains(card.id)) player.cardsId.add(card.id);
        card.playerId=player.id;

    }
}
