import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * process key input from the keyboard
 */
public class KeyInput extends KeyAdapter {

    private GameFrame gameFrame;
    private PongGame pongGame;

    public KeyInput(GameFrame gameFrame, PongGame pongGame) {
        this.gameFrame = gameFrame;
        this.pongGame = pongGame;
    }

	//methods for KeyListener
    @Override
        public void keyPressed(KeyEvent e){
            
            //Change paddle direction based on user input
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                pongGame.setMover(1);
                    break;
                case KeyEvent.VK_DOWN:
                pongGame.setMover(2);
                    break;
                case KeyEvent.VK_P:
                gameFrame.toggleMenu();
                gameFrame.toggleTimer();
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
                pongGame.setMover(0);
                    break;
            
                case KeyEvent.VK_DOWN:
                pongGame.setMover(0);
                    break;
            }
        }

}