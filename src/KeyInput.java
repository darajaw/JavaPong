import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * process key input from the keyboard
 */
public class KeyInput extends KeyAdapter {

    private GameFrame gameFrame;

    public KeyInput(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

	//methods for KeyListener
    @Override
        public void keyPressed(KeyEvent e){
            
            //Change paddle direction based on user input
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                PongGame.mover=1;
                    break;
                case KeyEvent.VK_DOWN:
                PongGame.mover=2;
                    break;
                case KeyEvent.VK_M:
                gameFrame.toggleMenu();
                break;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {

            //Stop moving User paddle if there is no input
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                PongGame.mover=0;
                    break;
            
                case KeyEvent.VK_DOWN:
                PongGame.mover=0;
                    break;
            }
        }

}