package model;

import java.util.ArrayList;

public abstract class Player {
    public static ArrayList<Player> list = new ArrayList<>();
    private static int idCounter=0;
    public final int id;
//    public ArrayList<Card> cards= new ArrayList<>();
    public ArrayList<Integer> cardsId = new ArrayList<>();
    public int coin;
    public String name;
    public Player(){
        list.add(this);
        id = idCounter;
        idCounter++;
        coin=2;
        name= "Player "+(id+1);
    }
    public ArrayList<Card> cards(){
        ArrayList<Card> result = new ArrayList<>();
        for (Integer id :
                cardsId) {
            result.add(Card.getCardById(id));
        }
        return result;
    }

    public void addCoin(int x){
        coin = coin+x;
    }

    public boolean isAlive(){
        return getAliveCardsNumber()!=0;
    }

    public int getAliveCardsNumber(){
        int i = 0;
        for (Card card :
                cards()) {
            if(card.isAlive)
                i++;
        }
        return i;
    }

    public boolean counterAct(Player player,ActionType actionType){
        return false;
    }

    public boolean challenge(Player player,CardType type){
        return false;
    }
    public boolean challenge(Player player,CardType type,CardType secondType){
        return false;
    }



    public static Player get(int id){
        return list.get(id);
    }

    public boolean hasCard(CardType type){
        for (Card card :
                cards()) {
            if (card.name == type&&card.isAlive)
                return true;
        }
        return false;
    }
    public Card getRandomAliveCard(){
        for (Card card :
                cards()) {
            if (card.isAlive)
                return card;
        }
        return null;
    }

    Player getRandomAliveOtherPlayer(){
        Player otherPlayer;
        ArrayList<Integer> randomList = Challenge.getRandomOrder();
        for (Integer i :
                randomList) {
            Player player = Player.list.get(i);
            if(player!=this&& player.isAlive()){
                otherPlayer = player;
                return otherPlayer;
            }
        }
        return null;
    }



}
