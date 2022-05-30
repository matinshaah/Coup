package gui;

import model.ActionType;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterActionDialog extends JDialog implements ActionListener {
    Player human;
    Player otherPlayer;
    ActionType actionType;
    JLabel label;
    public boolean counteracted= false;
    JButton counterActButton,ignoreButton;
    public CounterActionDialog(Frame frame, String title, boolean modal, Player human, Player otherPlayer, ActionType actionType) {
        super(frame,title,modal);
        this.human = human;
        this.otherPlayer = otherPlayer;
        this.actionType= actionType;

        setSize(500, 300);
        this.setResizable(false);
        setLocationRelativeTo(null);
        this.setLayout(null);

        initCom();
        align();



        setVisible(true);
    }

    void initCom(){
        label = new JLabel("The player \""+otherPlayer.name+"\" wants to "+actionType.toString()+" against you");
        counterActButton = new JButton("counteract");
        ignoreButton = new JButton("continue");
        counterActButton.setBackground(Color.white);
        ignoreButton.setBackground(Color.white);
        counterActButton.addActionListener(this);
        ignoreButton.addActionListener(this);
        counterActButton.setFocusable(false);
        ignoreButton.setFocusable(false);
    }
    void align(){
        this.add(label);
        this.add(counterActButton);
        this.add(ignoreButton);
        label.setBounds(40,50,400,50);
        counterActButton.setBounds(100,100,120,50);
        ignoreButton.setBounds(500-counterActButton.getX()-counterActButton.getWidth(),
                counterActButton.getY(),counterActButton.getWidth(),counterActButton.getHeight());

    }
        @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource()==counterActButton)
                counteracted = true;

            dispose();
    }
}
