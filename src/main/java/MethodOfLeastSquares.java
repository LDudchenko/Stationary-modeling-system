import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class MethodOfLeastSquares {

    private double[] coefficients;

    public double[] buildAproximationOfTheFunctionForArrays(double arrX[], double arrY[], double[] values){
        double[] valuesY =  new double[values.length];
        for(int i=0; i< valuesY.length; i++){
            valuesY[i] = buildAproximationOfTheFunction(arrX, arrY, values[i]);
        }
        return valuesY;
    }

    public double buildAproximationOfTheFunction(double arrX[], double arrY[], double value){
        double result;

        double U[][] = new double[arrX.length][2];
        for(int i=0; i<arrX.length; i++){
            for(int j=0; j<U[0].length; j++){
                if(j==0){
                    U[i][j] = 1;
                }
                else {
                    U[i][j] = arrX[i];
                }
            }
        }

        RealMatrix realMatrixU = MatrixUtils.createRealMatrix(U);
        RealMatrix transposedU = realMatrixU.transpose();

        RealMatrix multiplicationOfUAndTransposedU = transposedU.multiply(realMatrixU);
        RealMatrix invertedMatrix = new LUDecomposition(multiplicationOfUAndTransposedU).getSolver().getInverse();
        RealMatrix multiplicationOfTransposedAndInverted = invertedMatrix.multiply(transposedU);
        coefficients = multiplicationOfTransposedAndInverted.operate(arrY);
        result = coefficients[0]+coefficients[1]*value;
        return result;
    }

    public double buildAproximationOfTheFunction(double arrX[][], double arrY[], double[] values){
        double result;
        double U[][] = new double[arrX.length][arrX[0].length+1];
        for(int i = 0; i< arrX.length; i++){
            for(int j=0; j<arrX[0].length+1; j++){
                if(j==0){
                    U[i][j] = 1;
                }
                else{
                    U[i][j] = arrX[i][j-1];
                }
            }
        }

        RealMatrix realMatrixU = MatrixUtils.createRealMatrix(U);
        RealMatrix transposedU = realMatrixU.transpose();
        RealMatrix multiplicationOfUAndTransposedU = transposedU.multiply(realMatrixU);
        RealMatrix invertedMatrix = new LUDecomposition(multiplicationOfUAndTransposedU).getSolver().getInverse();
        RealMatrix multiplicationOfTransposedAndInverted = invertedMatrix.multiply(transposedU);

        coefficients = multiplicationOfTransposedAndInverted.operate(arrY);
        result = coefficients[0];
        for(int i =1 ; i<coefficients.length; i++) {
            result+=coefficients[i]*values[i-1];
        }
        return result;
    }

    public void printEquation(){
        System.out.println("Линейная функция с найденными коэффициентами:");
        System.out.printf("y=%.3f", coefficients[0]);
        for(int i=1; i<coefficients.length; i++){
            if(coefficients[i]>=0) {
                System.out.printf("+%.3f*x%d", coefficients[i], i);
            }
            else{
                System.out.printf("%.3f*x%d", coefficients[i], i);
            }
        }
        System.out.println();
    }

    public static void print(double[] arrX, double exactY, double Y){
        System.out.println("Апроксимация функции, полученная методом наименьших квадратов: ");
        System.out.printf("Значение функции %.3f для следующих аргументов:", Y);
        for(int i = 0; i<arrX.length; i++){
            System.out.print(" "+arrX[i]);
        }
        System.out.println(".");
        System.out.printf("Точное значение функции %.3f для следующих аргументов:", exactY);
        for(int i = 0; i<arrX.length; i++){
            System.out.print(" "+arrX[i]);
        }
        System.out.println(".");
        System.out.printf("Разница значений: %.3f.\n", Math.abs(Y-exactY));
    }
}
