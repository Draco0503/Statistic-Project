package main;

// import java.util.Random;

import magnamalo.Load;
import magnamalo.Save;
import tables.TableTest;

import java.util.List;


public class Main {

    private static final String ErrorSaveMsg = "[ERROR]: Could not save the data";
    // private static final String ErrorLoadMsg = "[ERROR]: Could not load the file";

    public static void main(String[] args) {

        Load file = new Load();

        List<Integer> data = file.getData();
        List<Integer> fabs = file.getFabs();

        if (data.size() != fabs.size()) {
            System.err.println("INPUT ERROR");
        }

        TableTest t = new TableTest();

        t.addDataColumn(data);
        t.addAbsoluteRates(fabs);
        t.addDirectRelRate(fabs);
        t.addAccAbsoluteRate();
        t.addAccRelativeRate();

        String table = t.tableResult(data, fabs);
        System.out.println(table);

        if(!Save.save(table))
            System.out.println(ErrorSaveMsg);
    }
}
