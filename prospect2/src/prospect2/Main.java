package prospect2;

import java.io.*;
import java.util.*;

public class Main {

   
    private static Map<String, Integer> cellValues = new HashMap<>();

    public static void main(String[] args) {
        String inputFile = "input.csv";    
        String outputFile = "output.csv";  

        List<String[]> data = readCSV(inputFile);
        evaluateData(data);
        writeCSV(outputFile, data);
    }

    
    public static List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

   
    public static void evaluateData(List<String[]> data) {
        for (int row = 0; row < data.size(); row++) {
            String[] rowData = data.get(row);
            for (int col = 0; col < rowData.length; col++) {
                String cellKey = getCellKey(row, col);
                String cellValue = rowData[col].trim();

                if (cellValue.startsWith("=")) {
                    
                    cellValues.put(cellKey, evaluateFormula(cellValue.substring(1)));
                } else {
                   
                    cellValues.put(cellKey, Integer.parseInt(cellValue));
                }
            }
        }

        
        for (int row = 0; row < data.size(); row++) {
            String[] rowData = data.get(row);
            for (int col = 0; col < rowData.length; col++) {
                String cellKey = getCellKey(row, col);
                rowData[col] = String.valueOf(cellValues.get(cellKey));
            }
        }
    }

   
    private static String getCellKey(int row, int col) {
        return String.valueOf((char) ('A' + col)) + (row + 1);
    }

    
    private static int evaluateFormula(String expression) {
        String[] tokens = expression.split("\\+");
        int sum = 0;
        for (String token : tokens) {
            token = token.trim();
            if (Character.isLetter(token.charAt(0))) {
                
                sum += cellValues.get(token);
            } else {
                
                sum += Integer.parseInt(token);
            }
        }
        return sum;
    }

    
    public static void writeCSV(String filePath, List<String[]> data) {
        try (PrintWriter pw = new PrintWriter(new File(filePath))) {
            for (String[] row : data) {
                pw.println(String.join(",", row));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

