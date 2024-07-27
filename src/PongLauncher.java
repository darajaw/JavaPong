import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PongLauncher {

    //declare and initialize the frame
    static JFrame f = new JFrame("Pong");

    public static void main(String[] args) {
        
       //exit program when window is closed
       f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

       //the size of the game will be 480x640, the size of the JFrame needs to be slightly larger
       f.setSize(650,495);

       //new PongGame instance
       PongGame game = new PongGame();

       //add the game to the JFrame
       f.add(game);

       //show the window
       f.setVisible(true);

       //new Timer for refreshing frame by frame
       //16 millisecond delay = ~60fps
       Timer timer = new Timer(16, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            //repaint the screen each frame
            game.repaint();

        }
    });

    //start the timer after it's been created
    timer.start();
    }
}
