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

    //method to draw the ball
    public void paint(Graphics g){

        //set the brush color to the ball color
        g.setColor(color);
    
        //paint the ball at x, y with a width and height of the ball size
        g.fillOval(x, y, size, size);
    
    }
}