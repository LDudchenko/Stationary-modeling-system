public class Newton {
    public static double[] newtonForArraysEqualIntervals(double[] arrX, double[] arrY, double[] valuesX) {
        double[] valuesY = new double[valuesX.length];
        for (int i = 0; i < valuesX.length; i++) {
            valuesY[i] = newtonForEqualIntervals(arrX, arrY, valuesX[i]);
        }
        return valuesY;
    }

    public static double[] newtonForArraysNonEqualIntervals(double[] arrX, double[] arrY, double[] valuesX) {
        double[] valuesY = new double[valuesX.length];
        for (int i = 0; i < valuesX.length; i++) {
            valuesY[i] = newtonForNonEqualIntervals(arrX, arrY, valuesX[i]);
        }
        return valuesY;
    }

     public static double newtonForEqualIntervals(double[] arrX, double[] arrY, double value){
        double res = 0;
        double h = arrX[1] - arrX[0];
        for(int i=0; i< arrX.length; i++){
            double P = 1;
            for(int j =0; j<i; j++){
                P*= value - arrX[j];
            }
            P = (finalDifferences(arrY, i) * P) / (factorial(i)*h);
            res+=P;
        }
        return res;
    }

    public static int factorial(int num){
        int result = 1;
        for(int i=1; i<=num; i++){
            result = result*i;
        }
        return result;
    }

    public static double finalDifferences(double[] arr, int rate){
        if(rate == 0){
            return arr[0];
        }
        else{
            double[] finalDifferences = new double[rate];
            for(int i=0; i<rate; i++){
                finalDifferences[i] = arr[i+1] - arr[i];
            }
            return finalDifferences(finalDifferences, rate -1);
        }
    }

    public static double newtonForNonEqualIntervals(double[] arrX, double[] arrY, double value){
        double res = 0;
        for(int i=0; i< arrX.length; i++){
            double P = 1;
            for(int j =0; j<i; j++){
                P*= value - arrX[j];
            }
            P = P*separatedDifferences(arrX,arrY, i);
            res+=P;
        }
        return res;
    }

    static int count = 1;

    public static double separatedDifferences(double[] arrX, double[] arrY, int rate){
        if(rate == 0){
            count = 1;
            return arrY[0];
        }
        else{
            double[] newArrY = new double[rate];
            for(int i=0; i<rate; i++){
                newArrY[i] = (arrY[i+1] - arrY[i])/(arrX[i+count] - arrX[i]);
            }
            count++;
            return separatedDifferences(arrX, newArrY,rate - 1);
        }
    }
}
