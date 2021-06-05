public class Lagrange {
    //Нахождение значения интерполяционного полинома Лагранжа для массива точек
    public static double[] lagrangeForArrays(double[] arrX, double[] arrY, double[] valuesX) {
        double[] valuesY = new double[valuesX.length];
        for (int i = 0; i < valuesX.length; i++) {
            valuesY[i] = lagrange(arrX, arrY, valuesX[i]);
        }
        return valuesY;
    }

    //Нахождение значения интерполяционного полинома Лагранжа в конкретной точке
    public static double lagrange(double[] arrX, double[] arrY, double valueX) {
        double result = 0;
        double resultX;
        for (int i = 0; i < arrY.length; i++) {
            resultX = 1;
            for (int j = 0; j < arrX.length; j++) {
                if (i != j) {
                    resultX *= (valueX - arrX[j]) / (arrX[i] - arrX[j]);
                }
            }
            result += resultX * arrY[i];
        }
        return result;
    }

}
