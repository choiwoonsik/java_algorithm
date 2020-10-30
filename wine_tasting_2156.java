import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class wine_tasting_2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] wine = new int[10001];
        int[] dp = new int[10001];

        for (int i = 1; i <= N; i++)
            wine[i] = Integer.parseInt(br.readLine());

        dp[0] = 0;
        dp[1] = wine[1];
        dp[2] = wine[1] + wine[2];

        int max = Math.max(dp[1], dp[2]);
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i-3] + wine[i-1] + wine[i];
            dp[i] = Math.max(dp[i], dp[i-2] + wine[i]);
            dp[i] = Math.max(dp[i], dp[i-1]);
            max = Math.max(dp[i], max);
        }
        System.out.println(max);
    }
}
