import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuadraticPanel extends JPanel implements ActionListener {

    static final long serialVersionUID = 42L;

    private JLabel title;
    private JButton button;
    private InputPanel input;
    private GraphPanel graph;
    private Quadratic Q;


    public QuadraticPanel() {

        //set variables
        title = new JLabel("Enter your quadratic below:");
        button = new JButton("Solve");
        input = new InputPanel();
        graph = new GraphPanel();

        //Layout and borders
        //setLayout(new BorderLayout());
        setLayout(new GridBagLayout());
        setSize(600, 100);
        /*title.setBorder(new EmptyBorder(5, 10, 5, 10));
        input.setBorder(new EmptyBorder(5, 10, 5, 10));
        roots.setBorder(new EmptyBorder(5, 10, 5, 10));*/

        //Border Layout add components
        /*this.add(BorderLayout.NORTH, title);
        //this.add(BorderLayout.CENTER, input);
        this.add(BorderLayout.EAST, button);
        this.add(BorderLayout.CENTER, graph);*/

        //Grid Bag Layout add components
        GridBagConstraints c0 = new GridBagConstraints(),
                c1 = new GridBagConstraints(),
                c2 = new GridBagConstraints(),
                c3 = new GridBagConstraints(),
                c4 = new GridBagConstraints();
        c0.gridx = 0;
        c0.gridy = 0;
        c0.gridwidth = 2;
        c0.fill = GridBagConstraints.HORIZONTAL;
        c0.insets = new Insets(10,40,0,0);
        this.add(title, c0);

        c1.gridx = 0;
        c1.gridy = 1;
        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 0.5;
        c1.insets = new Insets(10,10,10,0);
        this.add(input, c1);

        c2.gridx = 1;
        c2.gridy = 1;
        c2.weightx = 0.5;
        this.add(button, c2);

        c3.gridx = 0;
        c3.gridy = 2;
        c3.gridwidth = 2;
        c3.fill = GridBagConstraints.BOTH;
        c3.insets = new Insets(0,40,40,0);
        this.add(graph, c3);

        //add action listeners
        button.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ev) {
        double a = Double.parseDouble(input.quad.getText());
        double b = Double.parseDouble(input.linear.getText());
        double c = Double.parseDouble(input.constant.getText());
        Q = new Quadratic(a,b,c);

        graph.setQ(Q);

       /* if ( d > 0 )
        {
            this.roots.setText("The quadratic " + Q.getString() + " has two real roots, which are " +
                    String.format("%.5f", Q.getRoots().r1) + " and " + String.format("%.5f", Q.getRoots().r2) + ".");
            //Use %.5f to round to 5 decimal places, %.5g to round to 5 significant figures.
            //Use %f to print as it is, %5f will print with padding to the left up to 5 digits, %-5f with padding to the right.
        }
        else if ( d < 0 )
        {
            this.roots.setText("The quadratic " + Q.getString() + " has two complex roots.");
        }
        else // if d==0
        {
            this.roots.setText("The quadratic "+Q.getString()+" has one root, which is " +String.format("%.5f" , Q.getTurningPoint()) +".");
        }*/

        this.repaint();
    }

}