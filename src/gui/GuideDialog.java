package gui;

import javax.swing.*;
import java.awt.*;

public class GuideDialog extends JDialog {
    JLabel guideImage;
    public GuideDialog(Frame frame,String title,boolean modal){
        super(frame,title,modal);
        setSize(475, 345);
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setResizable(false);
        initCom();
        align();

        setVisible(true);//should be the last order
    }
    void initCom(){
        guideImage = new JLabel();
        guideImage.setVisible(true);
        guideImage.setIcon(new ImageIcon("src/resources/images/guideCard.png"));
    }
    void align(){
        this.add(guideImage);
        guideImage.setBounds(0,0,460,300);
    }

}
