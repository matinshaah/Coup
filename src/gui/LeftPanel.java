package gui;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LeftPanel extends JPanel {
    public static Color color = Color.white;
    ArrayList<PlayerPanel> playerPanels = new ArrayList<>();
    LeftPanel(){
        init();
        update();
    }
    void init(){
        this.setBackground(color);
        this.setLayout(null);
    }
    public void update(){
        this.removeAll();
        playerPanels.clear();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Player player;
                if(i==0) player = Player.list.get(1-j);
                else player = Player.list.get(3-j);
                PlayerPanel panel = new PlayerPanel(player);
                playerPanels.add(panel);

                this.add(panel);
                panel.setBounds(20+510*j,530*i+20,500,400);

            }
        }
    }
}