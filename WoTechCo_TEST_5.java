import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WoTechCo_TEST_5 {
    public static void main(String[] args) {
        solution("1100", "0010", "1001", "1101100100101111001111000000");
        System.out.println();
        System.out.println("110011011001100110010010111100111001110000000010");
    }
    private static String solution(String penter, String pexit, String pescape, String data) {
        StringBuilder str = new StringBuilder();
        int dataCount = data.length() / penter.length();
        String[] dataArr = new String[dataCount];

        for (int i = 0; i < dataCount; i++)
        {
            int start = i * penter.length();
            int end = start + penter.length();
            dataArr[i] = data.substring(start, end);
        }

        ArrayList<String> list = new ArrayList<>();

        list.add(penter);
        for (int i = 0; i < dataCount; i++)
        {
            if (dataArr[i].equals(penter))
                list.add(pescape);
            else if (dataArr[i].equals(pexit))
                list.add(pescape);
            else if (dataArr[i].equals(pescape))
                list.add(pescape);
            list.add(dataArr[i]);
        }
        list.add(pexit);

        list.forEach(str::append);

        return str.toString();
    }
}
