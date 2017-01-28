import javax.swing.*;
import java.awt.*;

public class Main {

    static final long serialVersionUID = 42L;

    static JFrame f;

    public static void main(String args[]) {
        f = new JFrame("Quadratic solver");
        QuadraticPanel b = new QuadraticPanel();
        f.getContentPane().add(b);
        f.setSize(600,600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true); //contains paint()
    }
}
