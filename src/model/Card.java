package model;

import resources.ImageResource;

import java.util.ArrayList;

public class Card {
    public static ArrayList<Card> list = new ArrayList<>();
    private static int idCounter = 0;
    public int id;
    public CardType name;
    public int playerId=-1;
    public boolean isAlive=true;
    public Card(CardType cardType){
        list.add(this);
        Game.deck.add(this);
        this.name = cardType;
        id=idCounter;
        idCounter++;
    }

    public ImageResource getImageResource(){
        if(playerId !=-1){
            Player player = Player.get(playerId);
            if (player instanceof BotPlayer) {
                if (isAlive) return ImageResource.backOfCard;
            }
        }
        String life = isAlive?"alive":"dead";
        String path = life+name.toString();
        return ImageResource.valueOf(path);
    }

    public  static Card getCardById(int id){
        for (Card c :
                list) {
            if (c.id == id)
                return c;
        }
        return null;
    }


}
