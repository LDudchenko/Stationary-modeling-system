
import java.io.File;
import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Формирование массива иксов
        double[] arrX = formArrayX(4, 0);

        System.out.println("Задание #1: ");

        //Вычисление значений функций на основе заданных иксов
        double[] arrY = mathFunction(arrX);

        //Формирование значений для сравнения
        double[] valuesX = {1.2, 3.5, 6.7};/*formArrayOfValuesX(4, 0);*/

        //Нахождение значений полинома Лагранжа в заданных точках для первого задания
        double[] valuesYTask1 = Lagrange.lagrangeForArrays(arrX, arrY, valuesX);

        print(valuesX, valuesYTask1, "Интерполяция по Лагранжу: ");

        double[] valuesYTask1Newton = Newton.newtonForArraysNonEqualIntervals(arrX, arrY, valuesX);

        print(valuesX, valuesYTask1Newton, "Интерполяция по Ньютону(для неравных промежутков): ");

        //Нахождение точных значений в заданных точках
        double[] preciseValuesY = mathFunction(valuesX);
        print(valuesX, preciseValuesY, "Значения функции в заданных точках: ");
        comparisonOfValues(valuesYTask1, preciseValuesY, "Сравнение точных значений с значениями, полученными с помощью интерполяции Лагранжа:");
        comparisonOfValues(valuesYTask1Newton, preciseValuesY, "Сравнение точных значений с значениями, полученными с помощью интерполяции Ньютона(для неравных промежутков):");

        System.out.println("\nЗадание #2: ");
        //Нахождение узлов интерполяции для минимизации максимальной погрешности
        double[] arrOfNodesMinimizationOfTheMaximumError = SearchForNodesMinimizationOfTheMaximumError.searchForNodes(1, 6, 4);

        //Вычисление значений функций на основе найденных узлов интерполяции для минимизации максимальной погрешности
        double[] arrYTask2 = mathFunction(arrOfNodesMinimizationOfTheMaximumError);

        //Нахождение значений полинома Лагранжа в заданных точках для второго задания
        double[] valuesYTask2 = Lagrange.lagrangeForArrays(arrOfNodesMinimizationOfTheMaximumError, arrYTask2, valuesX);

        print(valuesX, valuesYTask2, "Значения интерполяционного полинома Лагранжа с минимальной максимаольной погрешностью:");
        comparisonOfValues(valuesYTask1, valuesYTask2, "Сравнение значений полинома Лагранжа с минимальной максимальной погрешностью с полиномом Лагранжа, построенным в первом задании:");

        System.out.println("\nЗадание #3: ");

        //Вычисление значений функций на основе заданных иксов
        double[] arrX2 = new double[]{2, 3, 4, 5};
        double[] arrY2 = mathFunction(arrX2);
        valuesX = new double[]{2.3, 3.5, 5.8};

        //Нахождение значений полинома Ньютона для равных промежутков в заданных точках для третьеого задания
        double[] valuesYTask3 = Newton.newtonForArraysEqualIntervals(arrX2, arrY2, valuesX);

        print(valuesX, valuesYTask3, "Интерполяция по Ньютону(для равных промежутков): ");

        //Нахождение точных значений в заданных точках
        preciseValuesY = mathFunction(valuesX);
        print(valuesX, preciseValuesY, "Значения функции в заданных точках: ");
        comparisonOfValues(valuesYTask3, preciseValuesY, "Сравнение точных значений с значениями, полученными с помощью интерполяции Ньютона(для равных промежутков):");

        System.out.println("\nЗадание #4: ");
        valuesX = new double[]{2.4, 3.5, 4.9};

        //Нахождение значений линейного сплайна
        double[] valuesYTask4 = LineSpline.buildLineSplineForArrays(arrX, arrY, valuesX);
        print(valuesX, valuesYTask4, "Линейный сплайн: ");

        //Нахождение точных значений в заданных точках
        preciseValuesY = mathFunction(valuesX);
        print(valuesX, preciseValuesY, "Значения функции в заданных точках: ");
        comparisonOfValues(valuesYTask4, preciseValuesY, "Сравнение точных значений с значениями, полученными с помощью построения линейного сплайна:");

        System.out.println("\nЗадание #5: ");
        valuesX = new double[]{1.2, 3.5, 6.7};

        //Нахождение значений апроксимации функции, найденной методом наименьших квадратов
        MethodOfLeastSquares method1 = new MethodOfLeastSquares();
        double[] valuesYTask5 = method1.buildAproximationOfTheFunctionForArrays(arrX2, arrY2, valuesX);
        print(valuesX, valuesYTask5, "Апроксимация функции, построенная методом наименьших квадратов: ");

        //Уравнение полученной прямой
        method1.printEquation();

        //Нахождение точных значений в заданных точках
        preciseValuesY = mathFunction(valuesX);
        print(valuesX, preciseValuesY, "Значения функции в заданных точках: ");
        comparisonOfValues(valuesYTask5, preciseValuesY, "Сравнение точных значений с значениями апроксимации функции, построенной методом наименьших квадратов:");
        comparisonOfValues(valuesYTask5, valuesYTask3, "Сравнение значений, полученных с помощью интерполяции Ньютона(для равных промежутков) с значениями апроксимации функции, построенной методом наименьших квадратов:");

        System.out.println("\nЗадание #6: ");

        MethodOfLeastSquares method2 = new MethodOfLeastSquares();

        File dir = new File("D:\\Java_works\\Компьютерное моделирование и оптимизация\\Lab1");
        WorkWithFiles data = new WorkWithFiles(new File(dir,"data.txt"));

        data.analyze_data();
        data.readData();
        double[][] arrXTask6 = data.arrX;
        double[] arrYTask6 = data.arrY;

        double valuesYTask6 = method2.buildAproximationOfTheFunction(arrXTask6, arrYTask6, arrXTask6[0]);
        MethodOfLeastSquares.print(arrXTask6[0], arrYTask6[0], valuesYTask6);

        //Уравнение полученной прямой
        method2.printEquation();
    }

    //Формирование массива на иксов на заданных g и k
    public static double[] formArrayX(int g, int k) {
        double[] arrX = new double[4];
        arrX[0] = g - 2 * k - 3;
        arrX[1] = g - 2 * k - 1;
        arrX[2] = g - 2 * k;
        arrX[3] = g - 2 * k + 2;
        return arrX;
    }

    public static double[] formArrayOfValuesX(int g, int k) {
        double[] valuesX = new double[3];
        valuesX[0] = g - 2 * k - 2.8;
        valuesX[1] = g - 2 * k - 0.5;
        valuesX[2] = g - 2 * k + 2.7;
        return valuesX;
    }

    //Нахождение значения функции f(x)=sin(x)+x
    public static double[] mathFunction(double[] arrX) {
        double[] arrY = new double[arrX.length];
        for (int i = 0; i < arrX.length; i++) {
            arrY[i] = Math.sin(arrX[i]) + arrX[i];
        }

       /* for(int k=0; k< arrX.length; k++){
            if(k==0){
                System.out.print("|  x   |");
            }
            System.out.printf(" %.16f |", arrX[k]);
        }
        System.out.println();
        for(int j=0; j< arrY.length; j++){
            if(j==0){
                System.out.print("| f(x) |");
            }
            System.out.printf(" %.16f |", arrY[j]);
        }
        System.out.println();*/
        return arrY;
    }


    public static void comparisonOfValues(double[] values, double[] preciseValues, String text){
        System.out.println(text);
        for(int i=0; i< values.length; i++){
            System.out.printf("Разница значений в %d-й точке равна %.4f\n",(i+1),Math.abs(values[i]-preciseValues[i]));
        }
        System.out.println();
    }

    public static void print(double[] arrX, double[] arrY, String text){
        System.out.println(text);
        for(int i = 0; i< arrX.length; i++){
            System.out.printf("Значение в точке %.1f равно %.3f\n", arrX[i], arrY[i]);
        }
        System.out.println();
    }
}
