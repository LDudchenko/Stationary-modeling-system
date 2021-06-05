public class SearchForNodesMinimizationOfTheMaximumError {
    public static double[] searchForNodes(double startOfSegment, double endOfSegment, int countOfNodes) {
        double[] arrOfNodes = new double[countOfNodes];
        for (int i = 0; i < countOfNodes; i++) {
            double t= Math.cos((double)(2 * i + 1) / (2 * countOfNodes + 2) * Math.PI);
            arrOfNodes[i] = (startOfSegment + endOfSegment) / 2 + (endOfSegment - startOfSegment) / 2 * t;
        }
        return arrOfNodes;
    }
}
