package Magnamalo;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Load {

    private static JSONObject _obj;
    private static int _numData;

    public Load() throws JSONException {

        StringBuilder sb = new StringBuilder();
        String line = null;

        try (BufferedReader file = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\Files\\input.json"))) {
            while((line = file.readLine()) != null)
                sb.append(line);
            _obj = new JSONObject(sb.toString());
            _numData = _obj.getInt("nData");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int get_numData() { return _numData; }

    public int[] loadSamples() {
        int[] samples = new int[_numData];
        for (int i = 0; i < samples.length; i++) {
            samples[i] = _obj.getJSONArray("Data").getInt(i);
        }
        return samples;
    }

    public int[] loadFabs() {
        int[] fabs = new int[_numData];
        for (int i = 0; i < fabs.length; i++) {
            fabs[i] = _obj.getJSONArray("Fabs").getInt(i);
        }
        return fabs;
    }

}
