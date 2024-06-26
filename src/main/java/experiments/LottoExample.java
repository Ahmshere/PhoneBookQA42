package experiments;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LottoExample {



    private static void readCSV(String filePath, int startRow, int startColunm, int endRow, int endColumn) throws FileNotFoundException {
        Map<Integer, Integer> valueCounts = new HashMap<>();
        try (CSVReader reader = new CSVReader(new BufferedReader(new FileReader(filePath)))) {
            List<String[]> lines = reader.readAll();

            for (int i = startRow - 1; i < Math.min(endRow, lines.size()); i++) {
                String[] line = lines.get(i);
                for (int j = startColunm - 1; j < Math.min(endColumn, line.length); j++) {
                    if (!line[j].isEmpty()) {
                        int value = Integer.parseInt(line[j]);
                        valueCounts.put(value, valueCounts.getOrDefault(value, 0) + 1);
                    }
                }
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

        printValueCounts(valueCounts);
    }

    private static void printValueCounts(Map<Integer, Integer> valueCounts) {
        valueCounts.entrySet()
                .stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue() + " times."));

    }

    public static void main(String[] args) {
        int startRow = 2;
        int startColumn = 3;
        int endRow = 2000;
        int endColumn = 8;

        String filePath = "C:\\Lotto\\Lotto.csv";
        try{
            if (filePath.endsWith(".csv")){
                readCSV(filePath, startRow, startColumn, endRow,endColumn);
            }else if(filePath.endsWith(".xls")){
// excel
            }else {
                System.out.println("Unsupported type...");
            }
        }catch (IOException e){e.printStackTrace();}
    }

}
