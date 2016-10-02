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

    // returns an array of 2 elements: [0]=x, [1]=y
    public double[] getCoordinates() {
        double[] ans = new double[2];
        ans[0] = x;
        ans[1] = y;
        return ans;
    }

    public void relocationPoint(double x, double y) {
        setCoordinates(x, y);
    }

    public double distanceTo() {
        return Math.sqrt(x*x + y*y);
    }

    public double distanceTo(Point p) {
        double x = p.getCoordinates()[0];
        double y = p.getCoordinates()[1];
        return Math.sqrt((x - this.x)*(x - this.x) + (y - this.y)*(y - this.y));
    }

    public String toString() {
        return ("(" + Double.toString(x) + ";" + Double.toString(y) + ")");
    }

    public boolean isEquals(Point p) {
        double x = p.getCoordinates()[0];
        double y = p.getCoordinates()[1];
        if (this.x == x && this.y == y)
            return true;
        else
            return false;
    }
}