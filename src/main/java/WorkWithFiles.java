import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WorkWithFiles {

    private List<String> contentOfFile;
    int quantityOfRows;
    int quantityOfColumns;
    double[][] arrX;
    double[] arrY;

    WorkWithFiles(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        contentOfFile = new ArrayList<>();
        while (scanner.hasNext()) {
            contentOfFile.add(scanner.nextLine());
        }
    }

    public void readData() {
        quantityOfRows = contentOfFile.size();
        quantityOfColumns = contentOfFile.get(0).split("\t").length;
        arrX = new double[quantityOfRows][quantityOfColumns - 1];
        arrY = new double[quantityOfRows];
        int count = 0;
        for (int i = 0; i < quantityOfRows; i++) {
            for (int j = 0; j < quantityOfColumns; j++) {
                String[] line = contentOfFile.get(i).split("\t");
                line[j] = line[j].replace(',', '.');
                if (j == quantityOfColumns - 1) {
                    arrY[count] = Double.parseDouble(line[j]);
                    count++;
                } else {
                    arrX[i][j] = Double.parseDouble(line[j]);
                }
            }
        }
    }

    public void analyze_data() {
        this.readData();
        for (int i = 0; i < quantityOfRows; i++) {
            for (int j = 0; j < quantityOfColumns; j++) {
                String[] line = contentOfFile.get(i).split("\t");
                line[j] = line[j].replace(',', '.');
                if (!Pattern.matches("^[0-9]+(\\.[0-9]+)?$", line[j])) {
                    contentOfFile.remove(i);
                    this.readData();
                    i--;
                    break;
                }
            }
        }
        this.readData();
        double mathExpectation = 0;
        double standartDeviation = 0;
        for (int i = 0; i < quantityOfColumns-1; i++) {
            for (int j = 0; j < quantityOfRows; j++) {
                mathExpectation += arrX[j][i];
            }
            mathExpectation /= quantityOfRows;
            for (int j = 0; j < quantityOfRows; j++) {
                standartDeviation += Math.pow(arrX[j][i] - mathExpectation, 2);
            }
            standartDeviation/=quantityOfRows-1;
            standartDeviation = Math.sqrt(standartDeviation);
            for (int j = 0; j < quantityOfRows; j++) {
                if ((arrX[j][i] > mathExpectation + 3*standartDeviation) || arrX[j][i] < (mathExpectation - 3*standartDeviation)) {
                    contentOfFile.remove(j);
                    this.readData();
                    j--;
                }
            }
        }
        mathExpectation = 0;
        standartDeviation = 0;

        for (int j = 0; j < quantityOfRows; j++) {
            mathExpectation += arrY[j];
        }
        mathExpectation /= quantityOfRows;
        for (int j = 0; j < quantityOfRows; j++) {
            standartDeviation += Math.pow(arrY[j]- mathExpectation, 2);
        }
        standartDeviation/=quantityOfRows-1;
        standartDeviation = Math.sqrt(standartDeviation);
        for (int j = 0; j < quantityOfRows; j++) {
            if ((arrY[j] > mathExpectation + 3*standartDeviation) || arrY[j] < (mathExpectation - 3*standartDeviation)) {
                contentOfFile.remove(j);
                this.readData();
                j--;
            }
        }
    }
}
