import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameFrame extends JFrame{
    
    //new PongGame instance
    PongGame game = new PongGame();

    

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

        //new Timer for refreshing frame by frame
        //16 millisecond delay = ~60fps
        Timer timer = new Timer(16, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //game logic
                game.gameLogic();

                //repaint the screen each frame
                game.repaint();
            }
        });

        //start the timer after game is created
        timer.start();
    }

    
    

}

