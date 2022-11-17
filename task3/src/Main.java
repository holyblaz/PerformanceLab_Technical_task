import com.cedarsoftware.util.io.JsonObject;
import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// Данный алгоритм использует библиотеку github.com/jdereg/json-io для чтения и записи json
public class Main {
    public static void main(String[] args) throws IOException {
        // Средствами библиотеки json-io читаем json из файла в java объекты чтобы было проще работать
        final String testsJsonRaw = String.join("", Files.readAllLines(Paths.get("tests.json")));
        final String valuesJsonRaw = String.join("", Files.readAllLines(Paths.get("values.json")));
        final JsonObject<String, Object> tests = (JsonObject<String, Object>) JsonReader.jsonToJava(testsJsonRaw);
        final JsonObject<String, Object> values = (JsonObject<String, Object>) JsonReader.jsonToJava(valuesJsonRaw);
        // Сохраняем соотетствие между id тестов и их результатами в отдельный ассоциативный массив idToResult
        final Map<Long, String> idToResult = new HashMap<>();
        final Object[] jo = (Object[]) values.get("values");
        for (Object valueJo : jo) {
            final long id = (Long) ((JsonObject) valueJo).get("id");
            final String value = (String) ((JsonObject) valueJo).get("value");
            System.out.println(id + " " + value);
            idToResult.put(id, value);
        }
        // рекурсивно заполняем результаты тестирования в поля value объекта tests
        fill(tests.get("tests"), idToResult);
        // записываем результат в файл report.json
        final Map<String, Object> writeParams = new HashMap<>();
        writeParams.put(JsonWriter.TYPE, false);
        writeParams.put(JsonWriter.PRETTY_PRINT, true);
        final String answerJson = JsonWriter.objectToJson(tests, writeParams);
        Files.write(Paths.get("report.json"), Collections.singleton(answerJson), StandardOpenOption.CREATE);
    }

    private static void fill(Object tests, Map<Long, String> idToResult)
    {
        for (Object test : ((Object[]) tests))
        {
            final long id = (Long) ((JsonObject) test).get("id");
            ((JsonObject) test).put("value", idToResult.getOrDefault(id, ""));
            if (((JsonObject) test).containsKey("values")) {
                fill( ((JsonObject) test).get("values"), idToResult );
            }
        }
    }

}