import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS_9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String first = br.readLine();
        String second = br.readLine();

        int[][] table = new int[1001][1001];
        for (int i = 0; i < first.length(); i++){
            for (int j = 0; j < first.length(); j++){
                table[0][j] = 0;
                table[i][0] = 0;
            }
        }
        int max = 0;

        for (int i = 0; i < first.length(); i++)
        {
            for (int j = 0; j < second.length(); j++)
            {
                if (first.charAt(i) == second.charAt(j))
                    table[i+1][j+1] = table[i][j] + 1;
                else
                    table[i+1][j+1] = Math.max(table[i+1][j], table[i][j+1]);
                max = Math.max(max, table[i+1][j+1]);
            }
        }
        System.out.println(max);
    }
}
