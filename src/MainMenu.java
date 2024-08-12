import javax.swing.JPanel;
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

    private GameFrame gameFrame;
    private PongGame pongGame;
    private MusicPlayer music = new MusicPlayer();

    public MainMenu(GameFrame gameFrame, PongGame pongGame) {

        music.toggleBackgroundMusic();
        
        this.gameFrame = gameFrame;
        this.pongGame = pongGame;
        
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

        modeButton = buttonMaker(modeButton, "Light Mode", subButtons);
        muteButton = buttonMaker(muteButton, "Mute",subButtons);
        subExit = buttonMaker(subExit, "Exit", subButtons);

        //Add the menu buttons to the menu panel
        this.add(menuButtons);

        //Add the submenu buttons to the menu but keep them hidden by default
        this.add(subButtons);
        subButtons.setVisible(false);

        //Make main menu background transparent
        this.setOpaque(false);


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
                
                pongGame.toggleDarkMode();
                modeButton.setText(pongGame.getGameDark() ? "Dark Mode" : "Light Mode");

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

    public void paintComponent(Graphics g){

        super.paintComponent(g);

    }


    public void startGame() {
        gameStarted = true;

        startButton.setText("Resume Game"); 
        exitButton.setText("Exit Game");
    }

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

    //A method to streamline the process of making buttons and adding them to JPanels
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