package Magnamalo;

import Tables.Table;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    public static boolean save(Table table) {
        try (BufferedWriter file = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\src\\Files\\output.out"))) {
            file.write(table.toString());
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
