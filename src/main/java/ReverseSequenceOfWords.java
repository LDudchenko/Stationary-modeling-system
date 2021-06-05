public class ReverseSequenceOfWords {
    public static String reverseSequenceOfWords(String str){
        String strWithoutStartAndEndSpaces = str.trim();
        String[] words = strWithoutStartAndEndSpaces.split("\\s*[,|\\.]\\s*");
        StringBuilder resultString = new StringBuilder();
        for(int i=words.length-1; i>=0; i--){
            if(i==0){
                resultString.append(words[i] + ".");
            }else {
                resultString.append(words[i] + ",");
            }
        }
        return resultString.toString();
    }
}
