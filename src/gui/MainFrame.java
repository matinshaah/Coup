package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    public static MainFrame mainFrame;
    public static int WIDTH = 1400, HEIGHT = 1000;
    MainPanel panel = new MainPanel();

    public MainFrame() {
        super("Coup");
        mainFrame = this;
        init();
        this.add(panel);
        update();
    }

    private void init() {
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setLocationRelativeTo(null);
//        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void update() {
        panel.update();


        repaint();
        revalidate();
    }


}
