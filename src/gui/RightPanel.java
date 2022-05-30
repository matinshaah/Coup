package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel implements ActionListener {
    JButton guideButton;
    EventPanel eventPanel;
    ActionPanel actionPanel;
    RightPanel(){
        this.setBackground(LeftPanel.color);
        initCom();
        align();
    }
    void initCom(){
        eventPanel = new EventPanel();
        guideButton = new JButton("guide");
        actionPanel = new ActionPanel();
    }
    void align(){
        this.setLayout(null);

        this.add(eventPanel);
        eventPanel.setBounds(0,50,330,300);
        eventPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        eventPanel.setBackground(Color.white);

        this.add(actionPanel);
        actionPanel.setBounds(0,350,330,600);
        actionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        actionPanel.setBackground(Color.white);

        this.add(guideButton);
        guideButton.setBounds(5,5,120,40);
        guideButton.setBackground(Color.white);
        guideButton.setFocusable(false);
        guideButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new GuideDialog(MainFrame.mainFrame,"Guide",true);
    }

    void update(){
    }

}
