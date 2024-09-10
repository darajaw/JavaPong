/*
 * Class: MainMenu
 * Purpose: Object class for the game menus
 * 
 */
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
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
    private boolean gameStarted;
    private boolean subMenu;
    private JPanel menuButtons;
    private JPanel subButtons;

    JButton startButton;
    JButton optionButton;
    JButton exitButton;
    JButton subExit;

    JButton modeButton;
    JButton muteButton;

    
    private PongGame pongGame;
    private MusicPlayer music = new MusicPlayer();

    private Ball gameBall;//pong ball declared
    private Paddle userPaddle, pcPaddle;//pong paddles declared

    public MainMenu(GameFrame gameFrame, PongGame pongGame) {
        this.pongGame = pongGame;

        music.toggleBackgroundMusic();        
        
        this.setBackground(pongGame.isGameDark() ? Color.BLACK : Color.WHITE);

        //Set the layout manager of the MainMenu panel to null for absolute positioning
        this.setLayout(null);

        //Create an inner panel with BoxLayout to arrange buttons
        //Position th button panel in the center of the screen
        menuButtons = new JPanel();
        menuButtons.setLayout(new BoxLayout(menuButtons, BoxLayout.Y_AXIS));
        menuButtons.setBounds(BUTTON_PANEL_x, BUTTON_PANEL_y, BUTTON_WIDTH, BUTTON_PANEL_HEIGHT);   

        //Do the same for the submenu buttons
        subButtons = new JPanel();
        subButtons.setLayout(new BoxLayout(subButtons, BoxLayout.Y_AXIS));
        subButtons.setBounds(BUTTON_PANEL_x, BUTTON_PANEL_y, BUTTON_WIDTH, BUTTON_PANEL_HEIGHT);

        //Add the various buttons to each panel
        startButton = buttonMaker(startButton, "Start Game", menuButtons);
        optionButton = buttonMaker(optionButton, "Options", menuButtons);
        exitButton = buttonMaker(exitButton, "Exit", menuButtons);

        modeButton = buttonMaker(modeButton, "Dark Mode", subButtons);
        muteButton = buttonMaker(muteButton, "Mute",subButtons);
        subExit = buttonMaker(subExit, "Exit", subButtons);

        //Add the menu buttons to the menu panel
        this.add(menuButtons);

        //Add the submenu buttons to the menu but keep them hidden by default
        this.add(subButtons);
        subButtons.setVisible(false);

        //Action listeners for button functions
        startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

                gameFrame.toggleMenu();
                gameFrame.toggleTimer();

                //if game is not started, start game 
                if(!gameStarted){startGame();}

			}
		});

        optionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                subMenu = false;
                toggleSubMenu();

            }
        });
        
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

                //if game has started, go back to main menu
                if(gameStarted){
                    stopGame(); 
                    gameFrame.toggleMainMenu();
                }

                //If the game hasn't started, quit the program
                else{System.exit(0);}
				
			}
		});

        modeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                
                gameFrame.toggleDarkMode();
                modeButton.setText(pongGame.isGameDark() ? "Dark Mode" : "Light Mode");

			}
		});

        muteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

                music.toggleBackgroundMusic();
                muteButton.setText(music.getIsPlaying() ? "Mute" : "Unmute");
			}
		});

		subExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

                //if game has started, go back to main menu
                if(gameStarted){
                    toggleSubMenu();
                }

                else{
                    toggleSubMenu();
                    gameFrame.toggleMainMenu();
                }
			}
		});
    }

    /**
     * Repaints the menu with game components in the background
     * @param g the graphics object to draw the game
     */
    public void paintComponent(Graphics g){
            gameBall = pongGame.getGameBall();
            userPaddle = pongGame.getUserPaddle();
            pcPaddle = pongGame.getPCPaddle();

            //ensures the panel is cleared before redrawing
            super.paintComponent(g);
            
            // Cast Graphics to Graphics2D for more advanced drawing
            Graphics2D g2d = (Graphics2D) g;
        
            if(gameStarted){
            //set score color based on background
            g.setColor(pongGame.isGameDark() ? Color.WHITE : Color.BLACK);
            // Draw the dashed line down the center
            Stroke originalStroke = g2d.getStroke();
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{10}, 0));
            g2d.drawLine(PongGame.getPanelWidth() / 2, 4, PongGame.getPanelWidth() / 2, PongGame.getPanelHeight());
            g2d.setStroke(originalStroke);

            // Draw scores with large numbers
            g.setFont(new Font("Arial", Font.BOLD, 50)); // Set the font size to 50
            g.drawString(String.valueOf(pongGame.getPCScore()), PongGame.getPanelWidth() / 4 - g.getFontMetrics().stringWidth(String.valueOf(pongGame.getPCScore())) / 2, PongGame.getPanelHeight() / 2);
            g.drawString(String.valueOf(pongGame.getUserScore()), 3 * PongGame.getPanelWidth() / 4 - g.getFontMetrics().stringWidth(String.valueOf(pongGame.getUserScore())) / 2, PongGame.getPanelHeight() / 2);



            //draw the ball on the screen
            gameBall.paint(g);
    
            //draw the paddles
            userPaddle.paint(g);
            pcPaddle.paint(g);

            
            }

    }

    /**
     * Starts the game and changes the button text
     */
    public void startGame() {
        gameStarted = true;

        startButton.setText("Resume Game"); 
        exitButton.setText("Exit Game");
    }

    /**
     * Stops the game and resets the game components
     */
    public void stopGame() {
        pongGame.reset();
        gameStarted = false;
        
        startButton.setText("Start Game"); 
        exitButton.setText("Exit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    /**
     * Toggles the submenu visibility
     */
    private void toggleSubMenu(){

        subMenu = !subMenu;

        if(subMenu){
            menuButtons.setVisible(false);
            subButtons.setVisible(true);
        }

        else{
            menuButtons.setVisible(true);
            subButtons.setVisible(false);
        }
    }

    /**
     * Creates a button with the specified text and adds it to the specified JPanel
     * @param button button to be created
     * @param text text to be displayed on the button
     * @param buttonPanel JPanel to add the button to
     * @return the created button
     */
    private JButton buttonMaker(JButton button, String text, JPanel buttonPanel){

        //Button made with text
        button = new JButton(text);

        //Button dimensions set
        button.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        //Button added to target JPanel
        buttonPanel.add(button);

        return button;
    }
}