package SDS_알고리즘.Day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 피보나치수2_2748 {
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new long[N+1];

        dp[1] = 1;
        dp[2] = 1;
        long dfs = dfs(N);
        System.out.println(dfs);
    }

    private static long dfs(int n) {
        if (n == 1) return dp[1];
        if (n == 2) return dp[2];

        if (dp[n] == 0)
            dp[n] = dfs(n - 1) + dfs(n - 2);
        else return dp[n];
        return dp[n];
    }
}
