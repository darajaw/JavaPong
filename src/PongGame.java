import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PongGame extends JPanel{
    
    
    private Ball gameBall;//pong ball declared
    private Paddle userPaddle, pcPaddle;//pong paddles declared

    //Game panel is 640 X 480 within the frame
    static final int PANEL_WIDTH = 640, PANEL_HEIGHT = 480;

    //scores for players
    int userScore = 0; 
    int pcScore = 0;

    //count of ball bounces
    int bounceCount;
    
    //Controls movement of userPaddle
    static int mover;

    //0 or PANEL_HEIGHT for paddle movement
    int paddleDirection;

    //Constants set to keycode for up and down arrows
    public static final int VK_UP = 38;
    public static final int VK_DOWN = 40;


    //game constructor
    public PongGame(){

        //ball and paddle instances
        gameBall = new Ball(300, 200, -3, 3, 3, Color.PINK, 10);
        pcPaddle = new Paddle(10, 200, 75, 3, Color.BLUE);
        userPaddle = new Paddle(610, 200, 75, 3, Color.RED);   

        //new Timer for refreshing frame by frame
        //16 millisecond delay = ~60fps
        Timer timer = new Timer(16, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //game logic
                gameLogic();

                //repaint the screen each frame
                repaint();
                
            }
        });

        //start the timer after game is created
        timer.start();
    }
    
    //Updates and draws all the graphics on the screen     
    public void paintComponent(Graphics g){

        //ensures the panel is cleared before redrawing
        super.paintComponent(g);

        //draw the background, set color to BLACK and fill in a rectangle
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

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
    public void gameLogic(){
        

        //move the ball each frame
        gameBall.moveBall();

        //edge check/bounce
        gameBall.borderBounce(0, PANEL_HEIGHT);

        //move User paddle based on input
        switch (mover) {
            case 1:
                userPaddle.moveTowards(0);
                break;
            case 2:
                userPaddle.moveTowards(PANEL_HEIGHT);
                break;
            default:
                userPaddle.moveTowards();
                break;
        }

        //move PC paddle towards the ball y position
        pcPaddle.moveTowards(gameBall.getY());
        
        //check if ball collides with either paddle
        if(pcPaddle.checkCollision(gameBall) || userPaddle.checkCollision(gameBall)){
           
            //reverse ball direction if they collision detected
            gameBall.reverseX();

            //nudge ball in correct direction to avoid paddle collision bug
            //if ball cx is positive (moving right) move it just outside the pcPaddle
            //if ball cx is negative (moving leftt) move it just outside the userPaddle
            gameBall.setX(gameBall.getCx() > 0 ? (pcPaddle.getX() + Paddle.PADDLE_WIDTH + 1) : (userPaddle.getX() - 10));
            
            //count bounces
            bounceCount++;
        }

        //check if someone lost
        if(gameBall.getX() < 0){
            //player scores
            userScore++;
            reset();
        }
        else if(gameBall.getX() > PANEL_WIDTH){
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

        //pause for a second before restarting
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
        gameBall.setCx(-3);
        gameBall.setCy(3);
        gameBall.setSpeed(3);
        bounceCount = 0;
    }

}