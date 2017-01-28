import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GraphPanel extends JPanel {

    static final long serialVersionUID = 42L;

    Quadratic Q;

    public GraphPanel() {
        setPreferredSize(new Dimension(500, 400));

    }

    public void setQ(Quadratic q){
        Q = q;
    }

    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D)g;
        g2.scale(1.0, -1.0);
        g2.translate(250, -200);

        if (Q != null) {

            //set graph bouunds
            double maxx = Q.getBottomRight().x;
            double minx = Q.getTopLeft().x;
            double miny = Q.getBottomRight().y;
            double maxy = Q.getTopLeft().y;
            double xrange = maxx - minx;
            double yrange = maxy - miny;

            if (yrange == 0 && xrange ==0){
                yrange = 10;
            } else if(yrange == 0){
                yrange = xrange*Math.sqrt(Q.a);
                miny = miny-yrange/2;
                maxy = maxy+yrange/2;
            }
            if (xrange ==0){
                xrange=yrange/Math.sqrt(Q.a);
                minx = minx - xrange/2;
                maxx = maxx + xrange/2;
            }

            minx = minx - xrange*0.5;
            maxx = maxx + xrange*0.5;
            miny = miny - yrange*0.5;
            maxy = maxy + yrange*0.5;
            xrange = maxx - minx;
            yrange = maxy - miny;

            //Move graph to the bounds
            g2.scale(500 / xrange, 400 / yrange);
            g2.translate(-250 * xrange / 500 - minx, -200 * yrange / 400 - miny);

            //draw axes
            g2.setPaint(Color.white);
            g2.fill(new Rectangle2D.Double(minx, miny, xrange, yrange));

            g.setColor(Color.black);
            g2.setStroke(new BasicStroke(0));
            Line2D line = new Line2D.Double(minx, 0, maxx, 0);
            g2.draw(line);
            line = new Line2D.Double(0, miny, 0, maxy);
            g2.draw(line);

            //draw graph
            Point2D.Double p1, p0;
            p0 = new Point2D.Double(minx, Q.getY(minx));

            for (double x = minx; x <= maxx; x+= xrange/1000) {
                p1 = new Point2D.Double(x, Q.getY(x));
                line = new Line2D.Double(p0.x, p0.y, p1.x, p1.y);
                g2.draw(line);
                p0 = p1;
            }

            //label graph
            g2.scale(xrange / 500, -yrange / 400);
            g2.drawString("y", -10, (float)(-maxy*400/yrange) +10);
            g2.drawString("x", (float)(maxx*500/xrange) -10, 10);

            if (Q.getDeterminant() < 0){
                //write intercept
                g2.drawString(String.valueOf(Math.round( Q.getIntercept() *100)/100.0), -35 , (float)( -Q.getIntercept()*400/yrange )+10);

                if (Q.getTurningPoint().x != 0){
                    //write tp
                    if (Q.a < 0){
                        g2.drawString("("+ String.valueOf(Math.round( Q.getTurningPoint().x *100)/100.0) +" , "+
                                String.valueOf(Math.round( Q.getTurningPoint().y *100)/100.0) +")"
                                ,(float)(Q.getTurningPoint().x*500/xrange)-25 , (float)(-Q.getTurningPoint().y*400/yrange)-5);
                    } else {
                        g2.drawString("("+ String.valueOf(Math.round( Q.getTurningPoint().x *100)/100.0) +" , "+
                                String.valueOf(Math.round( Q.getTurningPoint().y *100)/100.0) +")"
                                ,(float)(Q.getTurningPoint().x*500/xrange)-25 , (float)(-Q.getTurningPoint().y*400/yrange)+15);
                    }
                        }
            } else if (Q.getDeterminant() == 0){
                //write one root
                g2.drawString(String.valueOf(Math.round( Q.getRoots().r1 *100)/100.0), (float)(Q.getRoots().r1*500/xrange)-20 , 15);

                if (Q.getTurningPoint().x != 0){
                    //write intercept
                    g2.drawString(String.valueOf(Math.round( Q.getIntercept() *100)/100.0), -35 , (float)( -Q.getIntercept()*400/yrange)+10);
                }
            } else { // Q.getDeterminant() > 0
                //write roots
                g2.drawString(String.valueOf(Math.round( Q.getRoots().r1 *100)/100.0), (float)(Q.getRoots().r1*500/xrange)-25 , 15);
                g2.drawString(String.valueOf(Math.round( Q.getRoots().r2 *100)/100.0), (float)(Q.getRoots().r2*500/xrange)+5 , 15);

                if (Q.getTurningPoint().x != 0){
                    //write tp
                    if (Q.a < 0){
                        g2.drawString("("+ String.valueOf(Math.round( Q.getTurningPoint().x *100)/100.0) +" , "+
                                String.valueOf(Math.round( Q.getTurningPoint().y *100)/100.0) +")"
                                ,(float)(Q.getTurningPoint().x*500/xrange)-25 , (float)(-Q.getTurningPoint().y*400/yrange)-5);
                    } else {
                        g2.drawString("("+ String.valueOf(Math.round( Q.getTurningPoint().x *100)/100.0) +" , "+
                                String.valueOf(Math.round( Q.getTurningPoint().y *100)/100.0) +")"
                                ,(float)(Q.getTurningPoint().x*500/xrange)-25 , (float)(-Q.getTurningPoint().y*400/yrange)+15);
                    }

                    if (Q.getIntercept() != 0){
                        //write intercept
                        g2.drawString(String.valueOf(Math.round( Q.getIntercept() *100)/100.0), -35 , (float)( -Q.getIntercept()*400/yrange)+10);
                    }
                } else {
                    //write intercept
                    g2.drawString(String.valueOf(Math.round( Q.getIntercept() *100)/100.0), -35 , (float)( -Q.getIntercept()*400/yrange)+10);
                }
            }

        } else { //Q == null - button not pressed yet
            g.setColor(Color.white);
            g.fillRect(-250, -200, 500, 400);

            //draw axes
            g.setColor(Color.black);
            g.drawLine(-250, 0, 250, 0);
            g.drawLine(0, -200, 0, 200);
            g2.scale(1.0, -1.0);

            //label axes
            g2.drawString("y", -10, -190);
            g2.drawString("x", 240, 10);
        }

    }

}