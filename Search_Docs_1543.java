import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Search_Docs_1543 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String subStr = br.readLine();

        int count = 0;
        while (s.contains(subStr))
        {
            count++;
            s = s.replaceFirst(subStr, ".");
        }
        System.out.println(count);
    }
}
