package gui;
import javax.swing.*;
import java.awt.*;


public class EventPanel extends JPanel {
    public static EventPanel panel;
    JScrollPane pane;
    JLabel  name;
    JTextArea textArea;
    EventPanel(){
        panel=this;
        init();
        align();
    }
    void init(){
        name = new JLabel("Events",SwingConstants.CENTER);
        textArea = new JTextArea();
        pane = new JScrollPane(textArea);
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas",Font.PLAIN,14));
        pane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }
    void align(){
        this.setLayout(null);
        this.add(name);
        name.setBounds(0,0,330,30);
        this.add(pane);
        pane.setBounds(0,30,330,270);
    }
    public void addTextArea(String firstPlayer,String secondPlayer,String action){
        String str=firstPlayer+" -> "+secondPlayer+": "+action;
        textArea.append(str+"\n");
    }
}
