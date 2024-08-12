import java.awt.*; //needed for Color

public class Paddle {

    //declare instance variables
    private int x, y, speed, moveToY;
    private Color color;

    //constant for paddle size
    static final int PADDLE_WIDTH = 15;
    static final int PADDLE_HEIGHT = 75;

    /**
     * Paddle constructor
     * @param x the x position to start drawing the paddle
     * @param y the y position to start drawing the paddle
     * @param speed the amount the paddle may move per frame
     * @param color the paddle color
     */
    public Paddle(int x, int y, int speed, Color color) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.color = color;
    }  

    
    public int getX() {
        return x;
    }


    public void setX(int x) {
        this.x = x;
    }


    public int getY() {
        return y;
    }


    public void setY(int y) {
        this.y = y;
    }


    public int getSpeed() {
        return speed;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }


    //Draw rectangle on screen
    public void paint(Graphics g){

        //paint the rectangle for the paddle
        g.setColor(color);

        //check if paddle is outside the border and correct it
        borderCheck();

        //draw the paddle 
        g.drawRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    public void moveTowards(){

        //keeps the paddle in place if it's not set to move
        this.moveToY = y + PADDLE_HEIGHT / 2;

    }
    
    //paddle movement method
    public void moveTowards(int moveToY) {
        

        //find the location of the center of the paddle
        //x,y of paddle is the top left corner
        int centerY = y + PADDLE_HEIGHT / 2;
        
        //determine if we need to move more than the speed away from where we are now
        //checks if the paddle is off center from the target but within the speed parameters
        if(Math.abs(centerY - moveToY) > speed){
            //if the center of the paddle is too far down
            if(centerY > moveToY){
                //move the paddle up by the speed
                y -= speed;
            }
            //if the center of the paddle is too far up
            if(centerY < moveToY){
                //move the paddle down by speed
                y += speed;
            }
        }
    }

    public void movement(){

    }

    public boolean checkCollision(Ball b){

        int rightX = x + PADDLE_WIDTH;
        int bottomY = y + PADDLE_HEIGHT;
    
        //check if the Ball is between the x values of the paddle
        if(b.getX() > (x - b.getSize()) && b.getX() < rightX){
            //check if Ball is between the y values of the paddle
            if(b.getY() > y && b.getY() < bottomY){
                //if we get here, we know the ball and the paddle have collided
                return true;
            }
        }
    
        //if we get here, one of the checks failed, and the ball has not collided
        return false;
    
    }

    private void borderCheck(){
        
        //keep paddle from leaving the window
        if (y <= 0){ this.y = 0;}
        if (y + PADDLE_HEIGHT >= PongGame.PANEL_HEIGHT){this.y = PongGame.PANEL_HEIGHT - PADDLE_HEIGHT;}
    }
}
