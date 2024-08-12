import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameFrame extends JFrame{
    
    //new PongGame instance
    PongGame game = new PongGame();
    MainMenu menu = new MainMenu(this, game);
    JPanel contentPanel = new JPanel(new CardLayout());

    private Timer timer;

    private boolean gamePause = true;
    private boolean timerRunning = false;

    CardLayout cardLayout;
    //constructor
    GameFrame(){

        //exit program when window is closed
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //the size of the game is 480x640, the size of the JFrame needs to be slightly larger
        this.setSize(650,495);

        //Game and menu panels added to the content panel to be used in a cardlayout 
        contentPanel.add(menu, "Menu");
        contentPanel.add(game, "Game");
        
        setContentPane(contentPanel);

        cardLayout = (CardLayout) getContentPane().getLayout();
        this.setVisible(true);

        //Listen for key presses 
        this.addKeyListener(new KeyInput(this,game));
        //focus key input on this method
        this.setFocusable(true);

        //Make the rootPane and contentPane transparent
        //Allows the game to be visible behind the menu
        this.getRootPane().setOpaque(false);
        contentPanel.setOpaque(false);


        //new Timer for refreshing frame by frame
        //16 millisecond delay = ~60fps
        timer = new Timer(16, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //game logic
                game.gameLogic();

                //repaint the screen each frame
                game.repaint();
                
            }
        });
        
    }

    public void toggleTimer(){
        
        if (timerRunning){this.timer.stop();}
        else{this.timer.start();}

        timerRunning = !timerRunning;        
    }

     //if game is paused resume and show game 
     //if game is playing pause game and show menu
    public void toggleMenu() {
        
        menu.setOpaque(false);

        if (gamePause){
        cardLayout.show(getContentPane(), "Game");}
        else{cardLayout.show(getContentPane(), "Menu");}
        
        gamePause = !gamePause;
    }

    public void toggleMainMenu() {
        
        //Make main menu background white
        menu.setOpaque(true);
        menu.setBackground(Color.WHITE);
        menu.revalidate();
        menu.repaint();

        //Pull up main menu
        cardLayout.show(getContentPane(), "Menu");        
    }
    
}

