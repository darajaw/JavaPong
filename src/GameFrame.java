import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameFrame extends JFrame{
    
    //new PongGame instance
    PongGame game = new PongGame();
    MainMenu menu = new MainMenu(this, game);
    JPanel contentPanel = new JPanel(new CardLayout());

    static boolean gamepause = true;

    //constructor
    GameFrame(){

        //exit program when window is closed
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //the size of the game is 480x640, the size of the JFrame needs to be slightly larger
        this.setSize(650,495);

        contentPanel.add(menu, "Menu");
        contentPanel.add(game, "Game");

        setContentPane(contentPanel);

        this.setVisible(true);

        //Listen for key presses 
        this.addKeyListener(new KeyInput(this));
        //focus key input on this method
        this.setFocusable(true);

        //Make the rootPane and contentPane transparent
        //Allows the game to be visible behind the menu
        this.getRootPane().setOpaque(false);
        contentPanel.setOpaque(false);

    }


    public void toggleMenu() {
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        
        if (gamepause){
        cardLayout.show(getContentPane(), "Game");}
        else{cardLayout.show(getContentPane(), "Menu");}
        
        gamepause = !gamepause;
    }
    
    

}

