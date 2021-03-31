package magnamalo;

import tables.Table;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    public static boolean save(String result) {
        try (BufferedWriter file = new BufferedWriter(new FileWriter("src/files/output.out"))) {
            file.write(result);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
