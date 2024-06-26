package helpers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// Ya dovbavi kakuyto hren'
public class NewApproachReadTable {
    private static Map<Integer, Integer> valueCounts = new HashMap<>();
    private static Map<Integer, List<Integer>> valuePositions = new HashMap<>();
    static int endRow = 250;  // какой строкой заканчивать чтение
    public static void main(String[] args) {
        // Укажите путь к файлу
        String filePath = "C:\\Lotto\\Lotto.csv";

        // с какой строки и столбца начинать чтение
        int startRow = 2; // начальная строка. В екселе первая строка имеет индекс 1
        int startColumn = 3; //начальная колонка
        int endColumn = 8; // последняя колонка
        int strongNumberColumn = 9; /*
        Поле strongNumberColumn используется для указания столбца, содержащего специальные значения, которые называются
        "сильными числами". Эти числа обрабатываются отдельно от всех остальных чисел в таблице.
        Основная цель использования strongNumberColumn — подсчитать количество появлений этих "сильных чисел" и
        сохранить их позиции (строки), в которых они встречаются.*/

        try {
            if (filePath.endsWith(".csv")) {
                readCSV(filePath, startRow, startColumn, endRow, endColumn);
            } else if (filePath.endsWith(".xlsx")) {
                readExcel(filePath, startRow, startColumn, endRow, endColumn);
            } else {
                System.out.println("Неподдерживаемый формат файла.");
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        // calculateFrequencyForTopNumbers(12); // true/false - выводить ли среднее значение каждого числа.
    }

    // Метод для чтения данных из CSV файла
    private static void readCSV(String filePath, int startRow, int startColumn, int endRow, int endColumn)
            throws IOException, CsvException {
        Map<Integer, Integer> valueCounts = new HashMap<>(); // Создается словарь (Map), где ключи — это значения из CSV файла, а значения — количество их повторений.

        try (CSVReader reader = new CSVReader(new BufferedReader(new FileReader(filePath)))) {
            // Создается объект CSVReader, который читает файл, используя буферизированный FileReader.
            //try-with-resources автоматически закрывает reader после завершения блока try.
            List<String[]> lines = reader.readAll(); // Все строки из CSV файла читаются и сохраняются в список массивов строк lines.

            /*Цикл по строкам файла:
Цикл начинается с startRow - 1 (поскольку строки в списке индексируются с нуля) и продолжается до endRow или до конца списка lines, в зависимости от того, что меньше.
             */
            for (int i = startRow - 1; i < Math.min(endRow, lines.size()); i++) {
                // Из списка lines извлекается текущая строка line.
                String[] line = lines.get(i);
                for (int j = startColumn - 1; j < Math.min(endColumn, line.length); j++) {
                    // Цикл начинается с startColumn - 1 (поскольку столбцы индексируются с нуля)
                    // и продолжается до endColumn или до конца массива line, в зависимости от того, что меньше.

                    //Проверка, что ячейка не пустая:
                    if (!line[j].isEmpty()) {
                        // Значение ячейки преобразуется в целое число.
                        int value = Integer.parseInt(line[j]);

                        // Значение добавляется в словарь valueCounts, увеличивая количество его повторений на 1.
                        valueCounts.put(value, valueCounts.getOrDefault(value, 0) + 1);
                    }
                }
            }
        } // Завершается блок try-with-resources, автоматически закрывая CSVReader

        printValueCounts(valueCounts);
    }

    // Метод для чтения данных из Excel файла
    private static void readExcel(String filePath, int startRow, int startColumn, int endRow, int endColumn)
            throws IOException {
        Map<Integer, Integer> valueCounts = new HashMap<>(); // Создается словарь (Map), где ключи — это значения из Excel файла, а значения — количество их повторений.

        try (Workbook workbook = new XSSFWorkbook(new File(filePath))) { // Создается объект Workbook для чтения Excel файла.
          //  try-with-resources автоматически закрывает workbook после завершения блока try.
           // Из рабочей книги (workbook) извлекается первый лист sheet.
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = startRow - 1; i < Math.min(endRow, sheet.getPhysicalNumberOfRows()); i++) { // Цикл начинается с startRow - 1 (поскольку строки индексируются с нуля)
                // и продолжается до endRow или до конца листа, в зависимости от того, что меньше.
                Row row = sheet.getRow(i); // Из листа sheet извлекается текущая строка row
                if (row != null) {
                    // Цикл начинается с startColumn - 1 (поскольку столбцы индексируются с нуля) и продолжается до
                    // endColumn или до конца строки, в зависимости от того, что меньше.
                    for (int j = startColumn - 1; j < Math.min(endColumn, row.getPhysicalNumberOfCells()); j++) {
                        Cell cell = row.getCell(j); // Из строки row извлекается текущая ячейка cell.
                        int value = getNumericCellValue(cell); // Из ячейки cell извлекается числовое значение с помощью метода getNumericCellValue.
                        valueCounts.put(value, valueCounts.getOrDefault(value, 0) + 1); // Значение добавляется в словарь valueCounts, увеличивая количество его повторений на 1.
                    }
                }
            }
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }

        printValueCounts(valueCounts);
    }

    // Метод для получения числового значения ячейки Excel
    private static int getNumericCellValue(Cell cell) {
        if (cell == null) {
            return 0;
        }

        switch (cell.getCellType()) {
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            default:
                return 0;
        }
    }

    // Метод для печати количества повторений значений
    private static void printValueCounts(Map<Integer, Integer> valueCounts) {
        System.out.println("Встречаемость значений : ");
        valueCounts.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue() + " раз"));
    }


}