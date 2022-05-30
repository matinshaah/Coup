package controller;

import gui.*;
import model.*;

import java.util.Random;

public class Controller {
    public static void setButtonsEnabled(Player player){
        boolean isHuman = player instanceof HumanPlayer;
        for (ActionPanel.MyButton button :
                ActionPanel.MyButton.buttons) {
            button.setEnabled(false);
        }
        if(isHuman&& ActionPanel.MyButton.buttons.size()!=0){
            if(player.coin>10) ActionPanel.MyButton.getButtonByName("coup").setEnabled(true);
            else {
                if(player.coin>=7) ActionPanel.MyButton.getButtonByName("coup").setEnabled(true);
                ActionPanel.MyButton.getButtonByName("assassinate").setEnabled(player.coin>=3);
                if(player.coin>=1) ActionPanel.MyButton.getButtonByName("change").setEnabled(true);
                ActionPanel.MyButton.getButtonByName("income").setEnabled(true);
                ActionPanel.MyButton.getButtonByName("foreignAid").setEnabled(true);
                ActionPanel.MyButton.getButtonByName("tax").setEnabled(true);
                ActionPanel.MyButton.getButtonByName("exchange").setEnabled(true);
                ActionPanel.MyButton.getButtonByName("steal").setEnabled(true);
            }
        }else if(isHuman) System.out.println("button size is 0");
        update();
    }


    public static void getIncome(Player player){
        player.addCoin(1);
        EventPanel.panel.addTextArea(player.name,"Bank","income");
        Game.getInstance().iteratePlayer();
        update();
    }

    public static void changeCard(Player player){
        player.addCoin(-1);
        Card card;
        if(player.getAliveCardsNumber()==1){
            card = player.getRandomAliveCard();
        }else {
            if(player instanceof HumanPlayer) {
                ChangeCardDialog dialog = new ChangeCardDialog(MainFrame.mainFrame, "select the card you want to change", true, player);
                card = dialog.getCard();
            }else {
                BotPlayer botPlayer = (BotPlayer) player;
                card = botPlayer.changeCard();
            }
        }
        Card newCard = Game.deck.get(new Random().nextInt(Game.deck.size()));
        Game.getInstance().giveCardToPlayer(player,newCard);


        Game.deck.add(card);
        player.cardsId.remove(Integer.valueOf(card.id));
        card.playerId=-1;

        EventPanel.panel.addTextArea(player.name,"Deck","change card");
        Game.getInstance().iteratePlayer();
        update();
    }
    public static void getForeignAid(Player player){
        Player duke = Challenge.ChallengePlayer(player,null);
        EventPanel.panel.addTextArea(player.name,"Bank","foreign aid");
        if(duke==null) player.addCoin(2);
        else {
            EventPanel.panel.addTextArea(duke.name,player.name,"block foreign aid");
            Player challengePlayer = Challenge.ChallengePlayer(duke,CardType.Duke);
            if(challengePlayer!=null&&(!duke.hasCard(CardType.Duke))){
                EventPanel.panel.addTextArea(challengePlayer.name,duke.name,"challenge");
                loseCard(duke);
                player.addCoin(2);
            }else {
                if(challengePlayer!=null){
                    EventPanel.panel.addTextArea(challengePlayer.name,duke.name,"challenge");
                    loseCard(challengePlayer);
                }
            }
        }

        Game.getInstance().iteratePlayer();
        update();

    }
    public static void makeCoup(Player coupMaker,Player player){
        EventPanel.panel.addTextArea(coupMaker.name,player.name,"coup");
        coupMaker.addCoin(-7);
        loseCard(player);

        Game.getInstance().iteratePlayer();
        update();
    }
    public static void getTax(Player duke){
        EventPanel.panel.addTextArea(duke.name,"Bank","tax");
        Player challengePlayer = Challenge.ChallengePlayer(duke, CardType.Duke);
        if(challengePlayer!=null&&(!duke.hasCard(CardType.Duke))){
            EventPanel.panel.addTextArea(challengePlayer.name,duke.name,"challenge");
            loseCard(duke);
        }else {
            if(challengePlayer!=null){
                EventPanel.panel.addTextArea(challengePlayer.name,duke.name,"challenge");
                loseCard(challengePlayer);
            }
            duke.addCoin(3);
        }

        Game.getInstance().iteratePlayer();
        update();
    }
    public static void assassinate(Player assassin,Player player){
        EventPanel.panel.addTextArea(assassin.name,player.name,"assassination");

        Player challengePlayer= Challenge.ChallengePlayer(assassin,CardType.Assassin);
        if(challengePlayer!=null && (!assassin.hasCard(CardType.Assassin))){
            EventPanel.panel.addTextArea(challengePlayer.name,assassin.name,"challenge");

            loseCard(assassin);
        }else {
            if(challengePlayer!=null) {
                EventPanel.panel.addTextArea(challengePlayer.name,assassin.name,"challenge");

                loseCard(challengePlayer);
            }
            assassin.addCoin(-3);

            boolean counteracted;
            counteracted = counterAct(assassin,player,ActionType.Assassinate);
            if (!counteracted) {
                loseCard(player);
            }else {
                EventPanel.panel.addTextArea(player.name,assassin.name,"block assassination");

                challengePlayer = Challenge.ChallengePlayer(player,CardType.Contessa);
                if(challengePlayer!=null && (!player.hasCard(CardType.Contessa))){
                    EventPanel.panel.addTextArea(challengePlayer.name,player.name,"challenge");

                    loseCard(player);
                    loseCard(player);
                }else {
                    if(challengePlayer!=null) {
                        EventPanel.panel.addTextArea(challengePlayer.name,player.name,"challenge");

                        loseCard(challengePlayer);
                    }
                }
            }
        }

        Game.getInstance().iteratePlayer();
        update();
    }
    public static void exchange(Player ambassador){
        EventPanel.panel.addTextArea(ambassador.name,"Deck","exchange");

        Player challengePlayer = Challenge.ChallengePlayer(ambassador, CardType.Ambassador);
        if(challengePlayer!= null&&(!ambassador.hasCard(CardType.Ambassador))){
            EventPanel.panel.addTextArea(challengePlayer.name,ambassador.name,"challenge");

            loseCard(ambassador);
        }else {
            if(challengePlayer!= null) {
                EventPanel.panel.addTextArea(challengePlayer.name,ambassador.name,"challenge");

                loseCard(challengePlayer);
            }
            Card[] newCard = new Card[2];
            System.out.println(Game.deck.size());
            int firstRandom = new Random().nextInt(Game.deck.size());
            int secondRandom = new Random().nextInt(Game.deck.size());
            newCard[0]=Game.deck.get(firstRandom);
            while (newCard[1]==null){
                if(secondRandom!=firstRandom){
                    newCard[1]=Game.deck.get(secondRandom);
                }
            }
            System.out.println(firstRandom+"*"+secondRandom);
            if(newCard[0]==null) System.out.println("khar0");
            Card[] lastCard = {ambassador.cards().get(0),ambassador.cards().get(1)};
            Card[] unwanted;
            if(ambassador instanceof HumanPlayer) {
                ExchangeCardDialog dialog =new ExchangeCardDialog(MainFrame.mainFrame, "Select the cards you don't want", true, ambassador,newCard);
                unwanted = dialog.getUnwantedCards();
            }else {
                BotPlayer bot = (BotPlayer) ambassador;
                unwanted = bot.exchangeCard(newCard[0],newCard[1]);

            }
            for (int i = 0; i < 2; i++) {
                if(!(newCard[i]==unwanted[0]||newCard[i]==unwanted[1])){
                    Game.getInstance().giveCardToPlayer(ambassador,newCard[i]);
                }
                if(lastCard[i]==unwanted[0]||lastCard[i]==unwanted[1]){
                    ambassador.cardsId.remove(Integer.valueOf(lastCard[i].id));
                    lastCard[i].playerId=-1;
                    Game.deck.add(lastCard[i]);
                }
            }
        }

        Game.getInstance().iteratePlayer();
        update();
    }

    public static void steal (Player captain,Player player){
        EventPanel.panel.addTextArea(captain.name,player.name,"steal");

        Player challengePlayer = Challenge.ChallengePlayer(captain,CardType.Captain);
        if(challengePlayer!=null&&(!captain.hasCard(CardType.Captain))){
            EventPanel.panel.addTextArea(challengePlayer.name,captain.name,"challenge");

            loseCard(captain);
        }else {
            if(challengePlayer!=null) {
                EventPanel.panel.addTextArea(challengePlayer.name,captain.name,"challenge");

                loseCard(challengePlayer);
            }
            boolean counteracted = counterAct(captain,player,ActionType.Steal);
            if(!counteracted){
                if(player.coin>=2){
                    player.addCoin(-2);
                    captain.addCoin(+2);
                }else {
                    captain.addCoin(player.coin);
                    player.addCoin(-player.coin);
                }
            }else {
                EventPanel.panel.addTextArea(player.name,captain.name,"block steal");

                challengePlayer = Challenge.ChallengePlayer(player,CardType.Captain,CardType.Ambassador);
                if(challengePlayer!=null&&(!player.hasCard(CardType.Captain))&&(!player.hasCard(CardType.Ambassador))){
                    EventPanel.panel.addTextArea(challengePlayer.name,player.name,"challenge");

                    loseCard(player);
                    if(player.coin>=2){
                        player.addCoin(-2);
                        captain.addCoin(+2);
                    }else {
                        captain.addCoin(player.coin);
                        player.addCoin(-player.coin);
                    }
                }else {
                    if(challengePlayer!=null) {
                        EventPanel.panel.addTextArea(challengePlayer.name,player.name,"challenge");

                        loseCard(challengePlayer);
                    }
                }
            }
        }

        Game.getInstance().iteratePlayer();
        update();
    }

    public static void loseCard(Player player) {
        if (player.getAliveCardsNumber() == 1) {
            for (Card card :
                    player.cards()) {
                if (card.isAlive) {
                    EventPanel.panel.addTextArea(player.name,"kill",card.name.toString());

                    card.isAlive = false;
                }
            }
        } else{
            Card card;
            if(player instanceof HumanPlayer) {
                LoseCardDialog dialog = new LoseCardDialog(MainFrame.mainFrame, "Select card to lose", true, player);
                card = dialog.getCard();
            }else {
                BotPlayer bot = (BotPlayer) player;
                card = bot.loseCard();
            }
            card.isAlive = false;

            EventPanel.panel.addTextArea(player.name,"kill",card.name.toString());


        }

        update();
    }

    public static boolean counterAct(Player actor,Player counterActor,ActionType actionType){
        boolean result= counterActor.counterAct(actor,actionType);
        if(counterActor instanceof HumanPlayer){
            CounterActionDialog dialog =new CounterActionDialog(MainFrame.mainFrame,"Counteraction",true,counterActor,actor,actionType);
            return dialog.counteracted;

        }
        return result;
    }

    public static void update(){
        MainFrame.mainFrame.update();
    }




}
