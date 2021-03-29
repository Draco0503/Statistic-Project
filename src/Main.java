import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int n = 4;
        Table table = new Table(n);
        int[] samples = new int[n];
        // Automatic form
        // TODO input form
        for(int i = 0; i < samples.length; i++) {
            samples[i] = i+1;
        }
        int[] fabs = new int[n];
        // Automatic form
        // TODO input form
        Random r = new Random();
        for (int i = 0; i < fabs.length; i++) {
            fabs[i] = r.nextInt(6);
        }
        table.addSamples(samples);
        table.addAbsoluteRates(fabs);
        table.directRelRate();
        table.AccumAbsoluteRate();
        table.AccumRelativeRate();

        System.out.println(table.toString());
    }
}
