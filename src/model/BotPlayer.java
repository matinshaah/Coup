package model;

import controller.Controller;
import java.util.Random;

public class BotPlayer extends Player{
    Type type;
    int i=0;
    enum Type{
        Paranoid(0),
        CoupMaker(1),
        CautiousKiller(2),
        Default(3);
        final int id;
        Type(int id){
            this.id=id;
        }

    }
    BotPlayer(Type type){
        this.type=type;
    }

    public void play() {
        if(!isAlive()){
            Game.getInstance().iteratePlayer();
            return;
        }
        Controller.update();
        try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(this.type==Type.CoupMaker)
            coupMakerPlay();
        else if(this.type==Type.CautiousKiller){
            cautiousKillerPlay();
        }else {
            boolean random= new Random().nextBoolean();

            if(this.coin>=7) {
                Player otherPlayer = getRandomAliveOtherPlayer();
                Controller.makeCoup(this,otherPlayer);
            } else if((this.hasCard(CardType.Assassin))&&coin>=3){
                Player otherPlayer = getRandomAliveOtherPlayer();
                if(otherPlayer.coin>0) Controller.assassinate(this,otherPlayer);
            }else if(this.hasCard(CardType.Captain)||random){
                Player otherPlayer = getRandomAliveOtherPlayer();
                Controller.steal(this,otherPlayer);
            }else if(this.hasCard(CardType.Duke)){
                Controller.getTax(this);
            }else {
                random = new Random().nextBoolean();
                if(random) Controller.getForeignAid(this);
                else Controller.getIncome(this);
            }
        }

        Controller.update();

    }
    void coupMakerPlay(){
        if(this.hasCard(CardType.Assassin)&&coin>=3) {
            Player otherPlayer=getRandomAliveOtherPlayer();
            Controller.assassinate(this,otherPlayer);
            return;
        }
        if(coin<7){
            Controller.getTax(this);

        }else {
            Player otherPlayer=getRandomAliveOtherPlayer();
            Controller.makeCoup(this,otherPlayer);
        }
    }

    void cautiousKillerPlay(){
        if(this.hasCard(CardType.Assassin)){
            if(coin>=3){
                Player otherPlayer = this.getRandomAliveOtherPlayer();
                Controller.assassinate(this, otherPlayer);
            }else {
                if(hasCard(CardType.Duke)) Controller.getTax(this);
                else Controller.getIncome(this);
            }
        }else if(this.hasCard(CardType.Ambassador)){
            Controller.exchange(this);
        }else {
            if(coin>=1) Controller.changeCard(this);
            else {
                i++;
                if(i%2==0)Controller.getForeignAid(this);
                else Controller.getIncome(this);
            }
        }
    }




    @Override
    public boolean counterAct(Player player, ActionType actionType) {
        if(actionType==ActionType.Steal){
            return this.hasCard(CardType.Captain) || this.hasCard(CardType.Ambassador);
        }else if(actionType==ActionType.Assassinate){
            if(this.hasCard(CardType.Contessa))
                return true;
            else return this.getAliveCardsNumber()==1;
        }
        return false;
    }

    @Override
    public boolean challenge(Player player, CardType type) {
        if(this.type==Type.Paranoid){
            i++;
            return i%2==0;
        }
        if(type==null){
            if(this.hasCard(CardType.Duke)||this.type==Type.CoupMaker)
                return true;
        }
        if(this.type==Type.Default) return new Random().nextBoolean();

        return super.challenge(player, type);
    }

    @Override
    public boolean challenge(Player player, CardType type, CardType secondType) {
        if(this.type==Type.Paranoid){
            i++;
            return i%2==0;
        }
        if(this.type==Type.Default) return new Random().nextBoolean();
        return super.challenge(player, type, secondType);
    }

    public Card changeCard(){
        return this.getRandomAliveCard();
    }

    public Card[] exchangeCard(Card firstNewCard,Card secondNewCard){
        Card[] unwanted = new Card[2];
        if(this.type==Type.CautiousKiller){
            System.out.println(firstNewCard.id+"*"+secondNewCard.id);
            if(firstNewCard.name==CardType.Assassin){
                for (Card c :
                        cards()) {
                    if (c.isAlive && c.name == CardType.Ambassador) {
                        unwanted[0] = c;
                        unwanted[1]=secondNewCard;
                        return unwanted;
                    }
                }
            }
            if(secondNewCard.name==CardType.Assassin){
                for (Card c :
                        cards()) {
                    if (c.isAlive && c.name == CardType.Ambassador) {
                        unwanted[0] = c;
                        unwanted[1]=firstNewCard;
                        return unwanted;
                    }
                }
            }
        }

        unwanted[0]=firstNewCard;
        unwanted[1]=secondNewCard;

        return unwanted;
    }

    public Card loseCard(){
        if(type==Type.CoupMaker){
            for (Card c:
                 cards()) {
                if(c.name!=CardType.Duke)
                    return c;
            }
        }else if(type==Type.CautiousKiller){
            for (Card c:
                    cards()) {
                if(c.name!=CardType.Assassin&&c.name!=CardType.Ambassador)
                    return c;
            }
            for (Card c:
                    cards()) {
                if(c.name!=CardType.Assassin)
                    return c;
            }
        }
        return this.getRandomAliveCard();
    }

    @Override
    public String toString() {
        return this.name+" "+this.id+" "+this.cardsId.get(0)+" "+this.cardsId.get(1)+" "+this.coin+ " "+this.type.toString();
    }
}
