public class Point {
    private double x;
    private double y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(double x, double y) {
        setCoordinates(x, y);
    }

    public void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void relocationPoint(double x, double y) {
        setCoordinates(x, y);
    }

    public double distanceTo() {
        return Math.sqrt(x*x + y*y);
    }

    public double distanceTo(Point p) {
        return Math.sqrt((p.x - this.x)*(p.x - this.x) + (p.y - this.y)*(p.y - this.y));
    }

    public String toString() {
        return ("(" + Double.toString(x) + ";" + Double.toString(y) + ")");
    }

    public boolean equals(Point p) {
        if (this.x == p.x && this.y == p.y)
            return true;
        else
            return false;
    }
}