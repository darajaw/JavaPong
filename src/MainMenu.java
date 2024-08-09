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

    public MainMenu(GameFrame gameFrame, PongGame pongGame) {

        this.gameFrame = gameFrame;
        this.pongGame = pongGame;
        
        //Set the layout manager of the MainMenu panel to null for absolute positioning
        this.setLayout(null);

        //Create an inner panel with BoxLayout to arrange buttons
        menuButtons = new JPanel();
        menuButtons.setLayout(new BoxLayout(menuButtons, BoxLayout.Y_AXIS));

        //Position the button panel within the outer MainMenu panel
        menuButtons.setBounds(BUTTON_PANEL_x, BUTTON_PANEL_y, BUTTON_WIDTH, BUTTON_PANEL_HEIGHT);       
        
        //Main menu buttons
        startButton = new JButton("Start Game");
        optionButton = new JButton("Options");     
        exitButton = new JButton("Exit"); 

        //Set preferred sizes for the buttons
        startButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        optionButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        exitButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        //Add buttons to the button panel
        menuButtons.add(startButton);
        menuButtons.add(optionButton);
        menuButtons.add(exitButton);

        //Start button funciton
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
        
        //exit button function
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

        subButtons = new JPanel();
        subButtons.setLayout(new BoxLayout(subButtons, BoxLayout.Y_AXIS));
        subButtons.setBounds(BUTTON_PANEL_x, BUTTON_PANEL_y, BUTTON_WIDTH, BUTTON_PANEL_HEIGHT);  

        //submenu buttons
        modeButton = new JButton("Light Mode");
        muteButton = new JButton("Unmute");        
        subExit = new JButton("Exit");

        modeButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        muteButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        subExit.setMaximumSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT));

        subButtons.add(modeButton);
        subButtons.add(muteButton);
        subButtons.add(subExit);

        //Start button funciton
        modeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

                modeButton.setText(pongGame.gameDark?"Light Mode":"Dark Mode");
                pongGame.toggleDarkMode();

			}
		});

        //exit button function
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

        this.add(subButtons);
        subButtons.setVisible(false);
        //Add the button panel to the MainMenu panel
        this.add(menuButtons);

        //Make main menu background transparent
        this.setOpaque(false);
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
}