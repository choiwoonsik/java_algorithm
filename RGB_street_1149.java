import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RGB_street_1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] table = new int[N+1][N+1];
        int[][] dp = new int[N+1][N+1];

        Arrays.fill(dp[0], 0);

        for (int i = 1; i <= N; i++)
        {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            table[i][0] = R;
            table[i][1] = G;
            table[i][2] = B;
        }

        for (int i = 1; i <= N; i++)
        {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + table[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + table[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + table[i][2];
        }
        int min = Math.min(Math.min(dp[N][0], +dp[N][1]), dp[N][2]);
        System.out.println(min);
    }
}
