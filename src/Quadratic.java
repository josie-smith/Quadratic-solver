import java.awt.geom.Point2D;

import static java.lang.Math.sqrt;

public class Quadratic {

    public double a,b,c;

    /*public Quadratic(){
        this.a = 1;
        this.b = 0;
        this.c = 0;
    }*/

    public Quadratic ( double tom, double dick, double harry ) {
        this.a = tom;
        this.b = dick;
        this.c = harry;
    }

    public double getY(double x) {
        double y = a*x*x + b*x + c;
        return y;
    }

    public double getDeterminant() {
        double det = b*b-4*a*c;
        return det;
    }

    public Roots getRoots() {
        Roots r = new Roots(a,b,c);
        return r;
    }

    public double getIntercept() {
        double y = c;
        return y;
    }

    public Point2D.Double getTurningPoint() {
        double x = -b / (2*a);
        double y = this.getY(x);
        return new Point2D.Double(x,y);
    }

    private double minimumOf(double mA, double mB, double mC, double mD) {
        return Math.min( Math.min(mA,mB) , Math.min(mC,mD));
    }

    private double minimumOf(double mA, double mB, double mC) {
        return Math.min( Math.min(mA,mB) , mC);
    }

    private double minimumOf(double mA, double mB) {
        return Math.min(mA, mB);
    }

    private double maximumOf(double mA, double mB, double mC, double mD) {
        return Math.max(Math.max(mA, mB), Math.max(mC,mD));
    }

    private double maximumOf(double mA, double mB, double mC) {
        return Math.max(Math.max(mA, mB), mC);
    }


    private double maximumOf(double mA, double mB) {
        return Math.max(mA, mB);
    }

    public Point2D.Double getTopLeft(){
        double minx;
        double maxy;

        if (this.getDeterminant() <=0) {
            minx = minimumOf(0 , this.getTurningPoint().x);
            maxy = maximumOf(0, this.getTurningPoint().y, this.getIntercept());
        } else {
            minx = minimumOf(0 , this.getRoots().r1 , this.getRoots().r2 , this.getTurningPoint().x);
            maxy = maximumOf(0 , this.getTurningPoint().y, this.getIntercept());
        }

        return new Point2D.Double(minx,maxy);
    }

    public Point2D.Double getBottomRight(){
        double maxx;
        double miny;

        if (this.getDeterminant() <=0) {
            maxx = maximumOf(0 , this.getTurningPoint().x);
            miny = minimumOf(0, this.getTurningPoint().y, this.getIntercept());
        } else {
            maxx = maximumOf(0, this.getRoots().r2 , this.getRoots().r1 , this.getTurningPoint().x);
            miny = minimumOf(0, this.getTurningPoint().y, this.getIntercept());
        }

        return new Point2D.Double(maxx, miny);
    }

    public String getString() {
        String s = "";
        if (a==1) {
            s += "x^2";
        } else {
            s += String.valueOf(a) + "x^2";
        }

        if (b<0) {
            if (b==-1) {
                s += "-x";
            } else {
                s += String.valueOf(b) + "x";
            }
        } else if (b>0) {
            if (b==1) {
                s += "+x";
            } else {
                s += "+" + String.valueOf(b) + "x";
            }
        }

        if (c<0) {
            s += String.valueOf( c );
        } else if (c>0) {
            s += "+" + String.valueOf( c );
        }

        return s;
    }
}

class Roots {
    public double r1, r2;

    public Roots(double a, double b, double c) {
        double det = b * b - 4 * a * c;
        try {
            this.r1 = (-b - sqrt(det)) / (2 * a);
            this.r2 = (-b + sqrt(det)) / (2 * a);
        } catch (Exception e) {
            System.out.println("This fucked up");
        }
    }
}