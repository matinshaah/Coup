import gui.MainFrame;
import model.Game;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Game.getInstance();
        SwingUtilities.invokeLater(MainFrame::new);
    }
}