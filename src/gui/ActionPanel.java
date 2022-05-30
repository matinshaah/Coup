package gui;

import controller.Controller;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ActionPanel extends JPanel implements ActionListener {
    public ActionPanel(){
        this.setLayout(new GridLayout(8,1));
        initCom();
    }
    void initCom(){
        new MyButton("income");
        new MyButton("change");
        new MyButton("foreignAid");
        MyButton coup = new MyButton("coup");
        coup.setEnabled(false);
        new MyButton("tax");
        MyButton assassinate = new MyButton("assassinate");
        assassinate.setEnabled(false);
        new MyButton("exchange");
        new MyButton("steal");
        for (MyButton button :
                MyButton.buttons) {
            button.addActionListener(this);
            this.add(button);
        }

    }




    @Override
    public void actionPerformed(ActionEvent e) {
        SelectPlayerDialog dialog;
        Player selectedPlayer;
        switch (((MyButton)(e.getSource())).name){
            case "income":
                Controller.getIncome(Player.get(0));
                break;
            case "change":
                Controller.changeCard(Player.get(0));
                break;
            case "foreignAid":
                Controller.getForeignAid(Player.get(0));
                break;
            case "coup":
                dialog = new SelectPlayerDialog(MainFrame.mainFrame,"Select the player",true);
                selectedPlayer = dialog.getSelectedPlayer();
                Controller.makeCoup(Player.get(0),selectedPlayer);

                break;
            case "tax":
                Controller.getTax(Player.get(0));
                break;
            case "assassinate":
                dialog = new SelectPlayerDialog(MainFrame.mainFrame,"Select the player",true);
                selectedPlayer = dialog.getSelectedPlayer();
                Controller.assassinate(Player.get(0),selectedPlayer);
                break;
            case "exchange":
                Controller.exchange(Player.get(0));
                break;
            case "steal":
                dialog = new SelectPlayerDialog(MainFrame.mainFrame,"Select the player",true);
                selectedPlayer = dialog.getSelectedPlayer();
                Controller.steal(Player.get(0),selectedPlayer);
                break;
        }
    }


    public static class MyButton extends JButton{
        public static ArrayList<MyButton> buttons = new ArrayList<>();
        String name;
        MyButton (String name){
            super(name);
            setFocusable(false);
//            setEnabled(false);
            setFont(new Font("",Font.PLAIN,20));
            buttons.add(this);
            this.name = name;
            this.setBackground(Color.white);
//            this.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        public static MyButton getButtonByName(String name){
            for (MyButton button :
                    buttons) {
                if(button.name.equals(name))
                    return button;
            }
            return null;
        }
    }


}
