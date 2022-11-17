import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        final String circumferenceFilename = args[0];
        final String pointsFilename = args[1];
        // читаем содержимое файла с окружностью
        final List<String> circumferenceLines = Files.readAllLines(Paths.get(circumferenceFilename));
        // читаем содержимое файла с точками
        final List<String> pointsLines = Files.readAllLines(Paths.get(pointsFilename));
        final String[] rawOrigins = circumferenceLines.get(0).split(" ");
        // сохраняем радиус и точку центра окружности
        final float radius = Float.parseFloat(circumferenceLines.get(1));
        final float originX = Float.parseFloat(rawOrigins[0]);
        final float originY = Float.parseFloat(rawOrigins[1]);
        for (String point : pointsLines)
        {
            // сохраняем координаты точки
            final String[] rawPoint = point.split(" ");
            final float x = Float.parseFloat(rawPoint[0]);
            final float y = Float.parseFloat(rawPoint[1]);
            // вычисляем расстояние от текущей точки до центра окружности
            final double d = distance(originX, x, originY, y);
            if (d < radius) {
                System.out.println(1); // точка внутри, т.к. расстояние меньше радиуса
            }
            else if (d == radius) {
                System.out.println(0); // точка в центре, т.к. расстояние равно радиусу.
            }
            else {
                System.out.println(2); // точка вне окружности, т.к. расстояние больше радиуса
            }
        }
    }


    private static double distance(final float x0, final float x1, final float y0, final float y1)
    {
        return Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0));
    }
}