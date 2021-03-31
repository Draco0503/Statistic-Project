package magnamalo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Load {

    private JSONObject _obj;

    public Load() throws JSONException {
        StringBuilder sb = new StringBuilder();
        String line;

        try (BufferedReader file = new BufferedReader(new FileReader("src/files/input.json"))) {
            while ((line = file.readLine()) != null)
                sb.append(line);
            _obj = new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getData() {
        int[] fabs = this._obj.getJSONArray("Data").toList().stream().mapToInt(i -> (int) i).toArray();
        return IntStream.of(fabs).boxed().collect(Collectors.toList());
    }

    public List<Integer> getFabs() {
        int[] fabs = this._obj.getJSONArray("Fabs").toList().stream().mapToInt(i -> (int) i).toArray();
        return IntStream.of(fabs).boxed().collect(Collectors.toList());
    }
}
