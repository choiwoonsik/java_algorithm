import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Climb_stair_2579 {
    static int one;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] stairs = new int[301];
        int[] dp = new int[301];

        for (int i = 1; i <= N; i++)
            stairs[i] = Integer.parseInt(br.readLine());

        Arrays.fill(dp, 0);
        dp[0] = stairs[0];
        dp[1] = stairs[1];
        dp[2] = stairs[1] + stairs[2];

        for (int i = 3; i <= N; i++)
            dp[i] = Math.max((dp[i-3] + stairs[i-1] + stairs[i]), (dp[i-2] + stairs[i]));
        System.out.println(dp[N]);
    }
}
