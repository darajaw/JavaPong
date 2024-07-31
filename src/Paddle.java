import java.awt.*; //needed for Color

public class Paddle {

    //declare instance variables
    private int height, x, y, speed;
    private Color color;

    //constant for paddle size
    static final int PADDLE_WIDTH = 15;

    /**
     * Paddle constructor
     * @param x the x position to start drawing the paddle
     * @param y the y position to start drawing the paddle
     * @param height the paddle height
     * @param speed the amount the paddle may move per frame
     * @param color the paddle color
     */
    public Paddle(int x, int y, int height, int speed, Color color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.speed = speed;
        this.color = color;
    }

    //Draw rectangle on screen
    public void paint(Graphics g){

        //paint the rectangle for the paddle
        g.setColor(color);
        g.drawRect(x, y, PADDLE_WIDTH, height);
    }

    //paddle movement method
    public void moveTowards(int moveToY) {

        //find the location of the center of the paddle
        //x,y of paddle is the top left corner
        int centerY = y + height / 2;

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
}
