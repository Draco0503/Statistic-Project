public class Table {

    private static double[][] _table = {{0,0}};
    private int _total;
    private boolean _rangeData = false;

    // Create the Frequency Table univariable template
    public Table(int rows) {
        _table = new double[rows + 1][5];
        /*_table[0][0] = Double.parseDouble("Table");
        _table[0][1] = Double.parseDouble("Fabs");
        _table[0][2] = Double.parseDouble("Frel");
        _table[0][3] = Double.parseDouble("Facc");
        _table[0][4] = Double.parseDouble("FRac");*/
        _total = 0;

    }

    public Table(int rows, int columns) {
        _table = new double[rows + 1][columns + 1];
        _total = 0;
    }

    public double[][] getTable() {
        return _table;
    }

    // Calculates the total of samples in the table
    public int total_samples() {
        int total = 0;
        for(int i = 1; i < _table.length; i++) {
            total += _table[i][1];
        }
        return total;
    }
    // This method is for a table where there is no range in the samples
    // The array "samples[]" must be ordered
    public void addSamples(int[] samples) {
        for(int i = 1; i < _table.length; i++) {
            _table[i][0] = samples[i - 1];
        }
    }
    // This method is for range samples
    // The array "samples[]" must be ordered
    public void addRangeSamples(String[] samples) {
        for(int i = 1; i < _table.length; i++) {
            _table[i][0] = Integer.parseInt(samples[i]);
        }
        _rangeData = true;
    }

    // The array "fabs[]" must be ordered
    public void addAbsoluteRates(int[] fabs) {
        for(int i = 1; i < _table.length; i++) {
            _table[i][1] = fabs[i - 1];
        }
    }

    // The array "frel[]" must be ordered
    public void addRelativeRate(double[] frel) {
        for(int i = 1; i < _table.length; i++) {
            _table[i][2] = frel[i - 1];
        }
    }

    // Calculates and adds the Relative Rate to the table
    public void directRelRate() {
        int total = total_samples();
        double aux = 0;
        for(int i = 1; i < _table.length; i++) {
            _table[i][2] = _table[i][1] / total;
            aux += _table[i][2];
        }
        if(aux > 1.00)
            System.out.println("[ERROR]: Relative Rate bigger than 1.0d\n");
        else if(aux < 1.00)
            System.out.println("[ERROR]: Relative Rate less than 1.0d\n");
        else
            System.out.println("Relative Rate successfully calculated\n");
    }

    // Calculates and adds the Accumulated Absolute Rate to the table
    public void AccumAbsoluteRate() {
        _table[1][3] = _table[1][1];
        for(int i = 2; i < _table.length; i++) {
            _table[i][3] = _table[i][1] + _table[i - 1][3];
        }
    }

    // Calculates and adds the Accumulated Relative Rate to the table
    public void AccumRelativeRate() {
        _table[1][4] = _table[1][2];
        for(int i = 2; i < _table.length; i++) {
            _table[i][4] = _table[i][2] + _table[i - 1][4];
        }
    }

    public double average() {
        double av = 0.00;
        for(int i = 1; i < _table.length; i++) {
            av += (_table[i][1]*_table[i][0]);
        }
        return av / _table.length - 1;
    }

    public double median() {
        if((_table.length - 2) % 2 == 0){
            return ((_table[(_table.length - 1) / 2][0] + _table[_table.length / 2][0]) / 2);
        }
        else {
            return _table[_table.length / 2][0];
        }
    }
    /*
    public double median_range() {

    }

    public double mode() {

    }*/

    // Prints the table
    public String toString() {
        StringBuilder table = new StringBuilder();
        table.append("------------------------------------------------\n");
        table.append("| Table  |"); table.append("|  Fabs  |"); table.append("|  Frel  |"); table.append("|  Facc  |"); table.append("|  FRac  |\n");
        table.append("------------------------------------------------\n");
        for(int i = 1; i < _table.length; i++) {
            for(int j = 0; j < _table[i].length; j++) {
                table.append("|  ");
                table.append(String.format("%.2f", _table[i][j]));
                table.append("  |");
            }
            table.append("\n");
            table.append("------------------------------------------------\n");
        }
        table.append("Total samples = "); table.append(total_samples()); table.append("\n");
        table.append("Average = "); table.append(average()); table.append("\n");
        if(!_rangeData)
            table.append("Median = "); table.append(median()); table.append("\n");
        return table.toString();
    }

}



// TODO adapt this class to be set by JSON extension