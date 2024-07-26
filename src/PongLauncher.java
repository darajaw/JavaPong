import javax.swing.*;

public class PongLauncher {

    //declare and initialize the frame
    static JFrame f = new JFrame("Pong");

    public static void main(String[] args) {
        
       //exit program when window is closed
       f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

       //the size of the game will be 480x640, the size of the JFrame needs to be slightly larger
       f.setSize(650,495);

       //show the window
       f.setVisible(true);
    }
}
