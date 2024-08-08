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

    public MainMenu(GameFrame gameFrame, PongGame pongGame) {
        //Set the layout manager of the MainMenu panel to null for absolute positioning
        this.setLayout(null);

        //Create an inner panel with BoxLayout to arrange buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        //Main menu buttons
        JButton startButton = new JButton("Start Game");
        JButton button2 = new JButton("Options");     
        JButton exitButton = new JButton("Exit");

        //Set preferred sizes for the buttons
        startButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button2.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        exitButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        //Add buttons to the button panel
        buttonPanel.add(startButton);
        buttonPanel.add(button2);
        buttonPanel.add(exitButton);

        //Button functions

        startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
                gameFrame.toggleMenu();
                pongGame.reset();
			}
		});

		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Quit the program
				System.exit(0);
			}
		});

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