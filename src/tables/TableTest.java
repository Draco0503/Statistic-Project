package tables;

import java.util.*;
import java.util.stream.Collectors;

public class TableTest {
    private final LinkedHashMap<String, List<Double>> table;  // Cada elemento de esta tabla es una columna.

    public TableTest() {
        this.table = new LinkedHashMap<>();
    }

    public void addDataColumn(List<Integer> samples) {
        table.put("Data", Arrays.stream(samples.stream().mapToDouble(i -> (double) i).toArray()).boxed().collect(Collectors.toList()));
    }

    public void addAbsoluteRates(List<Integer> fabs) {
        table.put("Fabs", Arrays.stream(fabs.stream().mapToDouble(i -> (double) i).toArray()).boxed().collect(Collectors.toList()));
    }

    public void addDirectRelRate(List<Integer> fabs) {
        int sum = fabs.stream().mapToInt(i -> i).sum();
        table.put("Frel", Arrays.stream(fabs.stream().mapToDouble(i -> (double) i / sum).toArray()).boxed().collect(Collectors.toList()));
    }

    public void addAccAbsoluteRate() {
        List<Double> accAbs = new ArrayList<>();
        int i = 0;
        for (Double val : table.get("Fabs")) {
            accAbs.add(i > 0 ? val + accAbs.get(i - 1) : val);
            i++;
        }
        table.put("Facc", accAbs);
    }

    public void addAccRelativeRate() {
        List<Double> accRel = new ArrayList<>();
        int i = 0;
        for (Double val : table.get("Frel")) {
            accRel.add(i > 0 ? val + accRel.get(i - 1) : val);
            i++;
        }
        table.put("Frac", accRel);
    }

    public double average(List<Integer> data, List<Integer> fabs) {
        double result = 0d;
        for (int i = 0; i < data.size(); i++) {
            result += data.get(i) * fabs.get(i);
        }
        return result / data.size();
    }

    private double median(List<Integer> data) {
        if (data.size() % 2 == 0) {
            return (double) (data.get(data.size() / 2 - 1) + data.get(data.size() / 2)) / 2;
        } else {
            return data.get(data.size() / 2);
        }
    }

    public String tableResult(List<Integer> data, List<Integer> fabs) {
        StringBuilder tabla = new StringBuilder();
        tabla.append(createTopBar(table.size()));
        for (int i = -1; i < data.size(); i++) {
            for (String key : table.keySet()) {
                if (i >= 0) {
                    String content = String.format("%.2f", table.get(key).get(i));
                    tabla.append("| ");
                    tabla.append(content);
                    tabla.append(" ".repeat(Math.max(0, 15 - content.length())));
                } else {
                    tabla.append("| ");
                    tabla.append(key);
                    tabla.append(" ".repeat(Math.max(0, 15 - key.length())));
                }
            }
            tabla.append("|").append("\n");
            tabla.append(createMidBar(table.size()));
        }
        tabla.delete(tabla.toString().lastIndexOf("├"), tabla.toString().length());
        tabla.append(createBottomBar(table.size()));
        tabla.append("Total samples: ").append(fabs.stream().mapToInt(i -> i).sum()).append("\n");
        tabla.append("Average: ").append(String.format("%,2f", average(data, fabs))).append("\n");
        tabla.append("Median: ").append(String.format("%,2f", median(data))).append("\n");

        return tabla.toString();
    }

    private String createTopBar(int columnas) {
        StringBuilder separator = new StringBuilder();
        separator.append("┌");
        for (int i = 0; i < columnas; i++) {
            if (i > 0) separator.append("┬");
            separator.append("─".repeat(16));
        }
        return separator.append("┐").append("\n").toString();
    }

    private String createMidBar(int columnas) {
        StringBuilder separator = new StringBuilder();
        separator.append("├");
        for (int i = 0; i < columnas; i++) {
            if (i > 0) separator.append("┼");
            separator.append("─".repeat(16));
        }
        return separator.append("┤").append("\n").toString();
    }

    private String createBottomBar(int columnas) {
        StringBuilder separator = new StringBuilder();
        separator.append("└");
        for (int i = 0; i < columnas; i++) {
            if (i > 0) separator.append("┴");
            separator.append("─".repeat(16));
        }
        return separator.append("┘").append("\n").toString();
    }
}
