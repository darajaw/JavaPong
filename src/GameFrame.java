import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameFrame extends JFrame{
    
    //new PongGame instance
    PongGame game = new PongGame();

    static boolean pause = true;

    //constructor
    GameFrame(){

        //exit program when window is closed
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //the size of the game is 480x640, the size of the JFrame needs to be slightly larger
        this.setSize(650,495);

        //add the game to the JFrame
        this.add(game);

        game.setVisible(true);

        
    }

    
    

}

