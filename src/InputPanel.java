import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {

    static final long serialVersionUID = 42L;

    private JLabel quadLabel, linearLabel, ylabel;
    public JFormattedTextField quad, linear, constant;

    public InputPanel() {
        setLayout(new FlowLayout());

        quad = new JFormattedTextField(new Double(1));
        quad.setColumns(5);
        linear = new JFormattedTextField(new Double(0));
        linear.setColumns(5);
        constant = new JFormattedTextField(new Double(0));
        constant.setColumns(5);
        ylabel = new JLabel ("y =");
        quadLabel = new JLabel("x^2 +");
        linearLabel = new JLabel("x +");

        add(ylabel);
        add(quad);
        add(quadLabel);
        add(linear);
        add(linearLabel);
        add(constant);

    }

}