/*
 * Class: GameFrame
 * Purpose: Main JFrame containing the game and timer
 */

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameFrame extends JFrame{
    
    static final int FRAME_WIDTH = 655;
    static final int FRAME_HEIGHT = 519;
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
        this.setSize(FRAME_WIDTH,FRAME_HEIGHT);

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

    /**
     * Toggles the timer on and off for the game
     */
    public void toggleTimer(){
        
        if (timerRunning){this.timer.stop();}
        else{this.timer.start();}

        timerRunning = !timerRunning;        
    }

     /**
      * Switches between menu and game content panes
      */
    public void toggleMenu() {

        if (gamePause){
        cardLayout.show(getContentPane(), "Game");}
        else{cardLayout.show(getContentPane(), "Menu");}
        
        gamePause = !gamePause;
    }

    /**
     * Redraws the main menu with the updated dark or light mode
     */
    public void toggleMainMenu() {
        menu.setBackground(game.isGameDark()?Color.BLACK:Color.WHITE);
        menu.revalidate();
        menu.repaint();

        //Pull up main menu
        cardLayout.show(getContentPane(), "Menu");        
    }

    /**
     * Redraws game with the updated dark or light mode
     */
    public void toggleDarkMode() {
        
        game.setGameDark(!game.isGameDark());
        
        menu.setBackground(game.isGameDark()?Color.BLACK:Color.WHITE);
        menu.revalidate();
        menu.repaint();

        //Pull up main menu
        cardLayout.show(getContentPane(), "Menu");        
    }

}
