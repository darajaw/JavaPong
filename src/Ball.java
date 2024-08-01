import java.awt.*;

public class Ball {

	//x & y are coordinates, cx & cy are changes in x & y per frame
    private int x, y, cx, cy, speed, size;
    private Color color;

	//ball constructor
    public Ball(int x, int y, int cx, int cy, int speed, Color color, int size) {
        this.x = x;
        this.y = y;
        this.cx = cx;
        this.cy = cy;
        this.speed = speed;
        this.color = color;
        this.size = size;
    }
    
    //getters and setters
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


    public int getCx() {
        return cx;
    }


    public void setCx(int cx) {
        this.cx = cx;
    }


    public int getCy() {
        return cy;
    }


    public void setCy(int cy) {
        this.cy = cy;
    }


    public int getSpeed() {
        return speed;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public int getSize() {
        return size;
    }


    public void setSize(int size) {
        this.size = size;
    }


    public Color getColor() {
        return color;
    }


    public void setColor(Color color) {
        this.color = color;
    }


    //method to draw the ball
    public void paint(Graphics g){

        //set the brush color to the ball color
        g.setColor(color);
    
        //paint the ball at x, y with a width and height of the ball size
        g.fillOval(x, y, size, size);
    
    }

    //each time the frame is painted, the ball's 
    //position will move by a factor of cx and cy
    public void moveBall(){
        x += cx;  
        y += cy;
    }

    public void borderBounce(int top, int bottom){
        
        //if the y value is at the bottom of the screen
        //subtracting size keeps the ball from passing slightly over the border
        if (y > (bottom - size)){
            reverseY();
        }
        //if y value is at top of screen
        else if(y < top){
            reverseY();
        }

        //if x value is at left or right side
        //hard-coded values, we will delete this section later
        if(x < 0){
            reverseX();
        }
        else if(x > (640 - size)){
            reverseX();
        }

    }
    //Reverse's the ball's change in x value
    public void reverseX(){
        cx *= -1;
    }

    // Reverse's the ball's change in y value
    public void reverseY(){
        cy *= -1;
    }
}