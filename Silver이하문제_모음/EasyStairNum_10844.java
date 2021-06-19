package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EasyStairNum_10844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[101][10];

        for (int i = 1; i < 10; i++)
            dp[1][i] = 1;

        for (int i = 2; i <= N; i++){
            for (int j = 0; j < 10; j++)
            {
                if (j == 0)
                    dp[i][j] = dp[i-1][1] % 1000000000;
                else if (j == 9)
                    dp[i][j] = dp[i-1][8] % 1000000000;
                else
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
            }
        }
        System.out.println(Arrays.stream(dp[N]).sum() % 1000000000);
    }
}
