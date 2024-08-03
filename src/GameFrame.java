import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameFrame extends JFrame implements KeyListener{
    
    //new PongGame instance
    PongGame game = new PongGame();

    //0 or WINDOW_HEIGHT for paddle movement
    int paddleDirection;

    //Constants set to keycode for up and down arrows
    public static final int VK_UP = 38;
    public static final int VK_DOWN = 40;

    //constructor
    GameFrame(){

        //exit program when window is closed
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //the size of the game is 480x640, the size of the JFrame needs to be slightly larger
        this.setSize(650,495);

        //add the game to the JFrame
        this.add(game);

        //show the window
        this.setVisible(true);

        //Listen for key presses 
        this.addKeyListener(this);
        
        //new Timer for refreshing frame by frame
        //16 millisecond delay = ~60fps
        Timer timer = new Timer(16, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //game logic
                game.gameLogic(paddleDirection);

                //repaint the screen each frame
                game.repaint();

            }

            

          

        });

        

        //start the timer after game is created
        timer.start();
    }
    
    @Override
        public void keyPressed(KeyEvent e){

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    paddleDirection = 0;
                    break;
            
                case KeyEvent.VK_DOWN:
                    paddleDirection = 480;
                    break;
            }

        }

        @Override
        public void keyTyped(KeyEvent e) {
            System.out.print(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
    

}
