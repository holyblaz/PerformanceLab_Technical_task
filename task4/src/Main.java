import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws IOException {
        final String numsFilename = args[0];
        final List<Integer> nums = new ArrayList<>();
        // считываем содержимое файла с массивом
        for (String num : Files.readAllLines(Paths.get(numsFilename))) {
            nums.add(Integer.parseInt(num));
        }
        // сортируем массив
        Collections.sort(nums);
        // находим срединный элемент, от него будем считать кол-во ходов до остальных
        final int middle = nums.get(nums.size() / 2);
        int answer = 0;
        // ответ это сумма кол-ва ходов, которые надо сделать чтобы сравнять все числа со срединным
        for (int num : nums) {
            answer += Math.abs(num - middle); // вычисляем расстояние между числами, abs -- модуль числа
        }
        System.out.println(answer);
    }
}