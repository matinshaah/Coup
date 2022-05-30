package model;

import gui.ChallengeDialog;
import gui.MainFrame;

public  class HumanPlayer extends Player{
    public HumanPlayer(){

    }

    @Override
    public boolean challenge(Player player, CardType type) {
        ChallengeDialog dialog = new ChallengeDialog(MainFrame.mainFrame,"challenge",true,this,player,type);
        return dialog.challenged;
    }
    @Override
    public boolean challenge(Player player, CardType type,CardType secondType) {
        ChallengeDialog dialog = new ChallengeDialog(MainFrame.mainFrame,"challenge",true,this,player,type,secondType);
        return dialog.challenged;
    }

    @Override
    public String toString() {
        return this.name+" "+this.id+" "+this.cardsId.get(0)+" "+this.cardsId.get(1)+" "+this.coin;
    }
}
