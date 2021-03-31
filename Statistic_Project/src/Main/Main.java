package Main;

// import java.util.Random;

import Magnamalo.Load;
import Magnamalo.Save;
import Tables.Table;

import java.io.FileNotFoundException;


public class Main {

    private static final String ErrorSaveMsg = "[ERROR]: Could not save the data";
    // private static final String ErrorLoadMsg = "[ERROR]: Could not load the file";

    public static void main(String[] args) {

        Load file = new Load();
        int n = file.get_numData();

        Table table = new Table(n);
        int[] samples = file.loadSamples();
        int[] fabs = file.loadFabs();

        // Automatic form
        /*for(int i = 0; i < samples.length; i++) {
            samples[i] = i+1;
        }
        // Automatic form
        Random r = new Random();
        for (int i = 0; i < fabs.length; i++) {
            fabs[i] = r.nextInt(6) + 1;
        }*/
        table.addSamples(samples);
        table.addAbsoluteRates(fabs);
        table.directRelRate();
        table.AccumAbsoluteRate();
        table.AccumRelativeRate();

        System.out.println(table.toString());
        if(!Save.save(table))
            System.out.println(ErrorSaveMsg);
    }
}
