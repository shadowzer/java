import java.util.Scanner;

public class Interface {
    private Scanner sc = new Scanner(System.in);

    public void input(Line line) {
        System.out.print("Введите начальное кол-во точек ломаной: ");
        int n = sc.nextInt();
        double x, y;
        for (int i = 0; i < n; ++i) {
            System.out.print("Введите координаты х и у: ");
            x = sc.nextDouble();
            y = sc.nextDouble();
            Point p = new Point(x, y);
            line.addPoint(p);
        }
    }

    public void printLine(Line line) {
        System.out.println(line.toString());
    }

    public void printSummaryLength(Line line) {
        double[] arr = line.getLengthOfAllSegments();
        double ans = 0;
        for (int i = 0; i < arr.length; ++i) {
            ans += arr[i];
        }
        System.out.println(ans);
    }

    public void printLinePointsEqualsToPoint(Line line, Point p) {
        Point[] points = line.findPoints(p);
        String ans = new String();
        for (int i = 0; i < points.length; ++i) {
            ans += points[i].toString() + " ";
        }
        if (ans.length() != 0)
            System.out.println(ans);
        else
            System.out.println("В ломаной нет точек с данными координатами");
    }

    public boolean isContinue(Line line) {
        System.out.print("Введите 1 для отображения вершин ломаной, 2 для отображения вершин ломаной, координаты которых совпадают с заданной точкой, 3 для вывода суммарной длины всех звеньев ломаной, 4 для задания новой точки, 0 для выхода из программы: ");
        String str = sc.next();
        double x;
        double y;
        Point p;
        switch (str.charAt(0)) {
            case '0':
                return false;
            case '1':
                printLine(line);
                break;
            case '2':
                System.out.print("Введите координаты x и y заданной точки: ");
                x = sc.nextDouble();
                y = sc.nextDouble();
                p = new Point(x, y);
                printLinePointsEqualsToPoint(line, p);
                break;
            case '3':
                printSummaryLength(line);
                break;
            case '4':
                System.out.println("Введите x и y координаты новой точки: ");
                x = sc.nextDouble();
                y = sc.nextDouble();
                p = new Point(x, y);
                line.addPoint(p);
                break;
            default:
                System.out.println("Некорректный ввод.");
                return isContinue(line);
        }
        return true;
    }
}