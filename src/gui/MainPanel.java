package gui;

import javax.swing.*;

public class MainPanel extends JPanel {
    LeftPanel leftPanel;
    RightPanel rightPanel;
    MainPanel(){
        initCom();
        align();
    }
    void initCom(){
        leftPanel = new LeftPanel();
        rightPanel = new RightPanel();
    }
    void align(){
        this.setLayout(null);

        this.add(leftPanel);
        leftPanel.setBounds(0,0,1050,1000);

        this.add(rightPanel);
        rightPanel.setBounds(leftPanel.getWidth(),0,1400- leftPanel.getWidth(),1000);
    }

    void update(){
        leftPanel.update();
        rightPanel.update();
    }

}
