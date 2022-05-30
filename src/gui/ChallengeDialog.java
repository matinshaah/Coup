package gui;


import model.CardType;
import model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChallengeDialog extends JDialog implements ActionListener {
    Player human;
    Player otherPlayer;
    CardType cardType;
    CardType secondType;
    JLabel label;
    public boolean challenged = false;
    JButton challengeButton, ignoreButton;

    public ChallengeDialog(Frame frame, String title, boolean modal, Player human, Player otherPlayer, CardType type) {
        super(frame, title, modal);
        this.human = human;
        this.otherPlayer = otherPlayer;
        this.cardType = type;

        setSize(500, 300);
        setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);

        initCom();
        align();


        setVisible(true);
    }

    public ChallengeDialog(Frame frame, String title, boolean modal, Player human, Player otherPlayer, CardType type,CardType secondType) {
        super(frame, title, modal);
        this.human = human;
        this.otherPlayer = otherPlayer;
        this.cardType = type;
        this.secondType =secondType;

        setSize(500, 300);
        setLocationRelativeTo(null);
        this.setLayout(null);

        initCom();
        align();


        setVisible(true);

    }

        void initCom() {
            if (cardType == null) {
                label = new JLabel("The player \"" + otherPlayer.name + "\" wants to " + "get foreign aid");
            } else if (secondType == null) {
                label = new JLabel("The player \"" + otherPlayer.name + "\" claims to have " + cardType);
            } else {
                label = new JLabel("The player \"" + otherPlayer.name + "\" claims to have " + cardType + " or " + secondType);
            }
            challengeButton = new JButton(cardType != null ? "challenge" : "block");
            ignoreButton = new JButton("continue");
            challengeButton.setBackground(Color.white);
            ignoreButton.setBackground(Color.white);
            challengeButton.addActionListener(this);
            ignoreButton.addActionListener(this);
            challengeButton.setFocusable(false);
            ignoreButton.setFocusable(false);
        }
    void align(){
        this.add(label);
        this.add(challengeButton);
        this.add(ignoreButton);

        label.setBounds(40,50,400,50);
        challengeButton.setBounds(100,100,120,50);
        ignoreButton.setBounds(500-challengeButton.getX()-challengeButton.getWidth(),
                challengeButton.getY(),challengeButton.getWidth(),challengeButton.getHeight());

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==challengeButton)
            challenged = true;

        dispose();
    }
}
