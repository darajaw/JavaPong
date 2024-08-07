import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;

public class MainMenu extends JPanel implements ActionListener{

    private int BUTTON_WIDTH = 200;
    private int BUTTON_HEIGHT = 50;
    private int BUTTON_PANEL_HEIGHT = 150; 
    private int BUTTON_PANEL_x = (PongGame.PANEL_WIDTH - 200) / 2;
    private int BUTTON_PANEL_y = (PongGame.PANEL_HEIGHT - 150) / 2;

    public MainMenu() {
        //Set the layout manager of the MainMenu panel to null for absolute positioning
        this.setLayout(null);

        //Create an inner panel with BoxLayout to arrange buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        //Create buttons
        JButton button1 = new JButton("Start Game");
        JButton button2 = new JButton("Options");
        JButton button3 = new JButton("Exit");

        //Set preferred sizes for the buttons
        button1.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button2.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button3.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        //Add buttons to the button panel
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        //Position the button panel within the outer MainMenu panel
        buttonPanel.setBounds(BUTTON_PANEL_x, BUTTON_PANEL_y, BUTTON_WIDTH, BUTTON_PANEL_HEIGHT);

        //Add the button panel to the MainMenu panel
        this.add(buttonPanel);

        //Make main menu background transparent
        this.setOpaque(false);
    }

public void paintComponent(Graphics g){

    super.paintComponent(g);

}

@Override
public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
}

}