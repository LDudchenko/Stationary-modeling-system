public class DeleteDuplicates {
    public static String delete(String str) {
        String strWithoutStartAndEndSpaces = str.trim();
        String[] words = strWithoutStartAndEndSpaces.split("\\s*[,|\\.]\\s*");
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            boolean busy = false;
            for (int j = 0; j < i; j++) {
                if (words[i].compareTo(words[j]) == 0) {
                    busy = true;
                }
            }
            if (busy == false) {
                if (i == 0) {
                    resultString.append(words[i]);
                } else {
                    resultString.append("," + words[i]);
                }
            } else {
                continue;
            }
        }
        resultString.append(".");
        return resultString.toString();
    }
}
