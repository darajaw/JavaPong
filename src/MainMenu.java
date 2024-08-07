import javax.swing.JPanel;
import java.awt.Graphics;
import javax.swing.JButton;

public class MainMenu extends JPanel{

    public MainMenu(){
    setOpaque(false);
}

public void paintComponent(Graphics g){

    super.paintComponent(g);

    g.drawString("MENU BOOOOOIII", 250, 250 );
}

}