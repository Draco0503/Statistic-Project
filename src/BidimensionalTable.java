public class BidimensionalTable extends Table {

    public BidimensionalTable(int rows, int columns) {
        super(rows, columns);
    }


    public void addXsamples(int[] Xsamples){
        super.addSamples(Xsamples);
    }
    public void addYsamples(int[] Ysamples) {
        for(int i = 1; i< getTable()[0].length; i++) {
            getTable()[0][i] = Ysamples[i - 1];
        }
    }

    public void addXRangeSamples(String[] samples) {
        super.addRangeSamples(samples);
    }

    public void addYRangeSamples(String[] samples) {
        for(int i = 1; i< getTable()[0].length; i++) {
            getTable()[0][i] = Double.parseDouble(samples[i - 1]);
        }
    }
    /*
    public double XMarginalDistribution() {

    }

    public double YMarginalDistribution() {

    }*/

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();
        table.append("------------------------------------------------\n");
        for(int i = 0; i < getTable().length; i++) {
            for(int j = 0; j < getTable()[i].length; j++) {
                if (i == 0 && j == 0)
                    table.append("|  x/y  |");
                else {
                    table.append("|  ");
                    table.append(String.format("%.2f", getTable()[i][j]));
                    table.append("  |");
                }
            }
            table.append("\n");
            table.append("------------------------------------------------\n");
        }

        return table.toString();
    }
}
