package gui;

import model.Player;
import resources.ResourceManager;

import javax.swing.*;
import java.awt.*;



public class PlayerPanel extends JPanel {

    Player player;
    JLabel coin,firstCard,secondCard,name;
    PlayerPanel(Player player){

        this.player = player;

//        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(LeftPanel.color);

        this.setLayout(null);
//
        ImageIcon coinImg =new ImageIcon("src/resources/images/coin.png");
//        ImageIcon coinImg =new ImageIcon(ResourceManager.get(ImageResource.coin));
        String amount = player.coin+"";
        coin = new JLabel(amount);
//        coin.setBorder(BorderFactory.createLineBorder(Color.blue));
        coin.setIcon(coinImg);
        coin.setFont(new Font("",Font.PLAIN,30));
//        coin.setForeground(new Color(254,228,64));
        this.add(coin);
        coin.setBounds(320,0,100,70);

        name=new JLabel("Player "+(player.id+1));
        this.add(name);
        name.setBounds(5,0,250,70);
//        name.setForeground(Color.red);
        name.setFont(new Font("",Font.BOLD,50));
//        name.setBorder(BorderFactory.createLineBorder(Color.blue));

        ImageIcon card1 =new ImageIcon(ResourceManager.get(player.cards().get(0).getImageResource()));
//        ImageIcon card1 =new ImageIcon("src/resources/images/aliveCaptain.jpg");
        ImageIcon card2 =new ImageIcon(ResourceManager.get(player.cards().get(1).getImageResource()));
//        ImageIcon card2 =new ImageIcon("src/resources/images/backOfCard.jpg");

        firstCard = new JLabel(card1);
        secondCard = new JLabel(card2);

        this.add(firstCard);
        this.add(secondCard);
//        firstCard.setBorder(BorderFactory.createLineBorder(Color.red));
        firstCard.setBounds(5,80,200,315);


//        secondCard.setBorder(BorderFactory.createLineBorder(Color.red));
        secondCard.setBounds(firstCard.getBounds().x+firstCard.getWidth()+5,80,200,315);

    }
}
