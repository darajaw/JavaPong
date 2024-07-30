import javax.swing.*;
import java.awt.*;

public class PongGame extends JPanel {

    private Ball gameBall;//pong ball declared
    private Paddle userPaddle, pcPaddle;//pong paddles declared

    //Pong screen is 640X480 within the window
    static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;


    //method for constructing the game
    public PongGame(){

    //ball and paddle instances
    gameBall = new Ball(300, 200, 3, 3, 3, Color.PINK, 10);
    userPaddle = new Paddle(10, 200, 75, 3, Color.BLUE);
    pcPaddle = new Paddle(610, 200, 75, 3, Color.RED);

    }
    
    //Updates and draws all the graphics on the screen     
    public void paintComponent(Graphics g){

        //draw the background, set color to BLACK and fill in a rectangle
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        //draw the ball on the screen
        gameBall.paint(g);

        //draw the paddles
        userPaddle.paint(g);
        pcPaddle.paint(g);

    }

    
    //Called once per frame to handle essential game operations
     public void gameLogic(){

        //move the ball each frame
        gameBall.moveBall();

        //edge check/bounce
        gameBall.borderBounce(0, WINDOW_HEIGHT);

}

   

}