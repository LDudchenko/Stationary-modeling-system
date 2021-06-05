public class TestReverseSequenceOfWords {
    public static void main(String[] args) {
        testReverse("SUN, CLOUD, RAIN.");
        testReverse("RAIN, CLOUD, CLOUD, SKY, RAIN.");
        testReverse("SUN, CLOUD, CLOUD, RAIN, SKY.");
        testReverse("SNOW,CLOUD,SKY,SUN,CLOUD,SKY,SUN.");
    }

    private static void testReverse(String str){
        System.out.print(str+"=>");
        System.out.println(ReverseSequenceOfWords.reverseSequenceOfWords(str));
    }
}
