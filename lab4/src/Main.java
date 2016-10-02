public class Main {

    public static void main(String[] args) {
        Line line = new Line();
        Interface anInterface = new Interface();
        anInterface.input(line);
        while (anInterface.isContinue(line));
    }
}
