package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 동전바꿔주기_2624 {
    static int M, N;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        M = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][M + 1];

        ArrayList<int[]> coins = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int coin = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            coins.add(new int[]{coin, cnt});
        }
        coins.add(new int[]{0, 0});

        coins.sort(Comparator.comparingInt(c -> c[0]));
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }
        for (int cidx = 1; cidx <= N; cidx++) {
            int[] coin = coins.get(cidx);
            int cost = coin[0];
            int coinCnt = coin[1];
            for (int money = 1; money <= M; money++) {
                for (int i = 0; i <= coinCnt; i++) {
                    if (money - (cost * i) < 0) break;
                    dp[cidx][money] += dp[cidx - 1][money - (cost * i)];
                }
            }
        }

        System.out.println(dp[N][M]);
    }
}
