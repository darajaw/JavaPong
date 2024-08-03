import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class PongGame extends JPanel{

    private Ball gameBall;//pong ball declared
    private Paddle userPaddle, pcPaddle;//pong paddles declared

    //Pong screen is 640X480 within the window
    static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;

    //scores for players
    int userScore = 0; 
    int pcScore = 0;

    //count of ball bounces
    int bounceCount;

    

    

    //game constructor
    public PongGame(){

        //ball and paddle instances
        gameBall = new Ball(300, 200, 3, 3, 3, Color.PINK, 10);
        pcPaddle = new Paddle(10, 200, 75, 3, Color.BLUE);
        userPaddle = new Paddle(610, 200, 75, 3, Color.RED);

        
    }
    
    //Updates and draws all the graphics on the screen     
    public void paintComponent(Graphics g){

        //draw the background, set color to BLACK and fill in a rectangle
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        //draw the ball on the screen
        gameBall.paint(g);

        //draw the paddles
        userPaddle.paint(g);
        pcPaddle.paint(g);

        //update score
        g.setColor(Color.BLACK);
        //the drawString method needs a String to print, and a location to print it at.
        g.drawString("Score - User [ " + userScore + " ]   PC [ " + pcScore + " ]", 250, 20 );

    }

    
    //Called once per frame to handle essential game operations
    public void gameLogic(int paddleDirection){
        

        //move the ball each frame
        gameBall.moveBall();

        //edge check/bounce
        gameBall.borderBounce(0, WINDOW_HEIGHT);

        //move user paddle towards where the mouse is
        userPaddle.moveTowards(paddleDirection);

        //move PC paddle towards the ball y position
        pcPaddle.moveTowards(gameBall.getY());
        
        //check if ball collides with either paddle
        if(pcPaddle.checkCollision(gameBall) || userPaddle.checkCollision(gameBall)){
            //reverse ball if they collide
            gameBall.reverseX();
            
            //count bounces
            bounceCount++;
            
        }

        //check if someone lost
        if(gameBall.getX() < 0){
            //player scores
            userScore++;
            reset();
        }
        else if(gameBall.getX() > WINDOW_WIDTH){
            //pc scores
            pcScore++;
            reset();
        }

        //every fifth bounce increase ball speed
        if (bounceCount == 5){
            //reset counter
            bounceCount = 0;
            gameBall.increaseSpeed();
        }
    }   
    

    

    //reset game
    public void reset(){

        //pause for a second before starting
        try{
            Thread.sleep(1000);
        }

        //catch exception if Thread sleep is interupted
        catch(Exception e){
            e.printStackTrace();
        }

        //reset game
        gameBall.setX(300);
        gameBall.setY(200);
        gameBall.setCx(3);
        gameBall.setCy(3);
        gameBall.setSpeed(3);
        bounceCount = 0;
    }

}