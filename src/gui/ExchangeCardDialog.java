package gui;

import model.Card;
import model.Player;
import resources.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ExchangeCardDialog extends JDialog implements ActionListener {
    Player player;
    ArrayList<Card> selectedCard = new ArrayList<>();
    Card[] newCard;
    Card[] unwanted = new Card[2];
    int i =0;
    public ExchangeCardDialog(Frame frame, String title, boolean modal, Player player,Card[] newCard){
        super(frame,title,modal);
        this.player = player;
        this.newCard=newCard;

        setSize(450, 680);
        setLocationRelativeTo(null);
        this.setLayout(new GridLayout(2,2));

        initSelectedCard();
        initCom();
        align();

        setVisible(true);
    }
    void initCom(){
        for (int i = 0; i <4 ; i++) {
            new CardButton(this,selectedCard.get(i));
        }
    }
    void align(){

    }
    void initSelectedCard(){
        selectedCard.addAll(player.cards());
        selectedCard.add(newCard[0]);
        selectedCard.add(newCard[1]);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        CardButton cardButton = (CardButton) e.getSource();
        cardButton.setEnabled(false);
        i++;
        if(unwanted[0]==null) unwanted[0]=cardButton.card;
        else unwanted[1] = cardButton.card;

        if(i==2){
            dispose();
        }

    }
    public Card[] getUnwantedCards(){

        if(unwanted[1]!=null){
            return unwanted;
        }
        return newCard;
    }

    private static class CardButton extends JButton{
        Card card;
        CardButton(ExchangeCardDialog dialog,Card card){
            dialog.add(this);
            this.card = card;
            this.setIcon( new ImageIcon(ResourceManager.get(card.getImageResource())));
            this.addActionListener(dialog);
            if(!card.isAlive) this.setEnabled(false);
        }
    }
}
