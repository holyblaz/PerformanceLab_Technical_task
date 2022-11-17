import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        final int n = Integer.parseInt(args[0]);
        final int m = Integer.parseInt(args[1]);
        final StringBuilder path = new StringBuilder();
        int at = 1;
        do {
            // сохраняем текущий элемент в путь
            path.append(at);
            // проходим по круговому массиву m раз и вычисляем следующий элемент на пути
            for (int i = 0; i < m - 1; i++) {
                at += 1;
                if (at > n) {
                    at = 1; // не забываем вернуть счётчик элементов к единице если превышено их число
                }
            }
        } while (at > 1); // выполняем пока снова не достигнем начала массива
        System.out.println(path);
    }
}