package gui;

import model.Card;
import model.Player;
import resources.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeCardDialog extends JDialog implements ActionListener {
    Player player;
    Card card;
    CardButton firstCardButton,secondCardButton;
    public ChangeCardDialog(Frame frame, String title, boolean modal, Player player){
        super(frame,title,modal);
        this.player = player;


        setSize(450, 345);
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new GridLayout(1,2));
        initCom();
        align();

        setVisible(true);
    }

    void initCom(){
        firstCardButton = new CardButton(player.cards().get(0),this);
        secondCardButton =  new CardButton(player.cards().get(1),this);
    }
    void align(){
        this.add(firstCardButton);
        this.add(secondCardButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardButton cardButton = (CardButton) e.getSource();
        card = cardButton.card;

        dispose();
    }

    public Card getCard(){
        if(card==null) return player.getRandomAliveCard();
        return card;
    }

    private static class CardButton extends JButton{
        Card card;
        CardButton (Card card,ChangeCardDialog dialog){
            this.card = card;
            this.setIcon( new ImageIcon(ResourceManager.get(card.getImageResource())));
            this.addActionListener(dialog);
        }
    }
}
