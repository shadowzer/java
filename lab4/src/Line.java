import java.util.ArrayList;
import java.util.List;

public class Line {
    private ArrayList<Point> points = new ArrayList<Point>();

    public void addPoint (Point point) {
        points.add(point);
    }

    public Point getPointAtIndex(int idx) {
        if (idx < 0 || idx >= points.size())
            throw new IndexOutOfBoundsException("wrong index");
        return points.get(idx);
    }

    public double[] getLengthOfAllSegments () {
        double[] ans = new double[points.size() - 1];
        for (int i = 0; i < points.size() - 1; ++i) {
            ans[i] = points.get(i).distanceTo(points.get(i + 1));
        }
        return ans;
    }

    // поиск вершин ломаной, координаты которых совпадают с координатами заданной точки
    public Point[] findPoints(Point p) {
        ArrayList<Point> tmp = new ArrayList<Point>();
        for (int i = 0; i < points.size(); ++i) {
            if (points.get(i).equals(p))
                tmp.add(points.get(i));
        }

        Point[] ans = new Point[tmp.size()];
        ans = tmp.toArray(ans);
        return ans;
    }

    public String toString () {
        String ans = new String();
        for (Point pts : points) {
            ans += pts.toString();
            ans += "-";
        }
        if (ans.length() != 0)
            return ans.substring(0, ans.length() - 1);
        else
            return "В ломаной линии нет точек.";
    }
}