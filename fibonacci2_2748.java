import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class fibonacci2_2748 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[91];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= N; i++)
            dp[i] = dp[i-2] + dp[i-1];

        System.out.println(dp[N]);

    }
}
