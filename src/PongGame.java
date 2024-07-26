import javax.swing.*;
import java.awt.*;

public class PongGame extends JPanel {

    private Ball gameBall;//pong ball declared

    //Pong screen is 640X480 within the window
    static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;


    //method for constructing the game
    public PongGame(){

    //new instance of the ball
    gameBall = new Ball(300, 200, 3, 3, 3, Color.PINK, 10);

    }
    
    //Updates and draws all the graphics on the screen     
    public void paintComponent(Graphics g){

        //draw the background, set color to BLACK and fill in a rectangle
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        //draw the ball on the screen
        gameBall.paint(g);

    }

   

}