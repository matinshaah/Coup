package gui;

import model.BotPlayer;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectPlayerDialog extends JDialog implements ActionListener {
    ArrayList<MyButton> buttons = new ArrayList<>();
    private Player selectedPlayer;
    public SelectPlayerDialog(Frame frame, String title, boolean modal){
        super(frame,title,modal);

        setSize(400, 100);
        setLocationRelativeTo(null);
        this.setLayout(new GridLayout(1,2));
        initCom();
        align();

        setVisible(true);
    }
    void initCom(){
        for (Player player:
             Player.list) {
            if(player.isAlive()&&player instanceof BotPlayer){
                MyButton button = new MyButton(player);
                buttons.add(button);
                button.addActionListener(this);
            }

        }
    }
    void align(){
        this.setLayout(new GridLayout(1,buttons.size()));
        for (MyButton b :
                buttons) {
            this.add(b);
        }
    }
    public Player getSelectedPlayer(){
        Player player = selectedPlayer;
        if(player==null){
            for (Player p :
                    Player.list) {
                if (p instanceof BotPlayer && p.isAlive())
                    return p;
            }
        }
        return player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        selectedPlayer = ((MyButton) e.getSource()).player;
        dispose();
    }

    private static class MyButton extends JButton{
        Player player;
        MyButton(Player player){
            super(player.name);
            this.player= player;
            this.setBackground(Color.white);
            this.setFocusable(false);
        }

    }
}
