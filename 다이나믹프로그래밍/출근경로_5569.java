package 다이나믹프로그래밍;

import java.util.*;
import java.io.*;

public class 출근경로_5569 {
    static int[][][] dp;
    static int rightright = 0;
    static int rightdown = 1;
    static int downright = 2;
    static int downdown = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        dp = new int[h + 1][w + 1][4];

        for (int i = 1; i <= h; i++) {
            dp[i][1][downdown] = 1;
        }
        for (int i = 1; i <= w; i++) {
            dp[1][i][rightright] = 1;
        }

        for (int y = 2; y <= h; y++) {
            for (int x = 2; x <= w; x++) {

                dp[y][x][downdown] = (dp[y - 1][x][rightdown] + dp[y - 1][x][downdown]) % 100000;

                dp[y][x][rightdown] = dp[y - 1][x][rightright] % 100000;

                dp[y][x][rightright] = (dp[y][x - 1][rightright] + dp[y][x - 1][downright]) % 100000;

                dp[y][x][downright] = dp[y][x - 1][downdown] % 100000;
            }
        }

        int sum = 0;
        for (int d = 0; d < 4; d++) {
            sum += dp[h][w][d];
        }
        System.out.println(sum % 100000);
    }
}
