import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class order_prompt_1032 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] strarr;
    public static void main(String[] args) throws IOException
    {
        int N = Arrays.stream(br.readLine().split(" " )).mapToInt(Integer::parseInt).toArray()[0];
        strarr = new String[N];
        for (int i = 0; i < N; i++)
            strarr[i] = br.readLine();

        String origin = strarr[0];
        String what = "";
        for (int i = 0; i < origin.length(); i++)
            what += compare(origin, i);
        System.out.println(what);
    }
    private static String compare(String origin, int i)
    {
        for (int j = 1; j < strarr.length; j++) {
            if (origin.charAt(i) != strarr[j].charAt(i)) {
                return "?";
            }
        }
        return (String.valueOf(origin.charAt(i)));
    }
}
