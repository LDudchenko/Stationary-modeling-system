import java.util.Arrays;

public class LineSpline {
    public static double[] buildLineSplineForArrays(double[] arrX, double[] arrY, double[] value){
        double[] res = new double[value.length];
        for(int j=0; j<value.length; j++){
            res[j] = buildLineSpline(arrX, arrY, value[j]);
        }
        return res;
    }

    public static double buildLineSpline(double[] arrX, double[] arrY, double value){
        double res=0;
        double[][] arrOfIntervals = new double[arrX.length-1][2];
        for(int i = 0; i< arrOfIntervals.length; i++){
            arrOfIntervals[i][0] = (arrY[i] - arrY[i+1])/ (arrX[i]-arrX[i+1]);
            arrOfIntervals[i][1] = (arrX[i]*arrY[i+1]-arrX[i+1]*arrY[i])/ (arrX[i]-arrX[i+1]);
        }

        if(Double.compare(value, arrX[0])<0){
            res = arrOfIntervals[0][0]*value + arrOfIntervals[0][1];
        }
        else if(Double.compare(value, arrX[arrX.length-1])>0){
            res = arrOfIntervals[arrX.length-2][0]*value + arrOfIntervals[arrX.length-2][1];
        }
        else {
            for (int i = 0; i < arrOfIntervals.length; i++) {
                if (Double.compare(value, arrX[i]) > 0 && Double.compare(value, arrX[i + 1]) < 0) {
                    res = arrOfIntervals[i][0] * value + arrOfIntervals[i][1];
                }
            }
        }
        return res;
    }
}
