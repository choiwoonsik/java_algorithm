package Gold이상문제_정리;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사과와바나나_3114 {
    static int[][] appleSum = new int[1501][1501];
    static int[][] bananaSum = new int[1501][1501];
    static int[][] apple = new int[1501][1501];
    static int[][] banana = new int[1501][1501];
    static int[][] total = new int[1501][1501];
    static int[][] dp = new int[1501][1501];
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                String s = st.nextToken();
                if (s.charAt(0) == 'A') {
                    apple[i][j] = Integer.parseInt(s.substring(1));
                } else if (s.charAt(0) == 'B') {
                    banana[i][j] = Integer.parseInt(s.substring(1));
                }
            }
        }

        applePrefixSum();
        bananaPrefixSum();
        totalPrefixSum();
        findMaxSum();
        System.out.println(dp[N - 1][M - 1]);
    }

    private static void findMaxSum() {
        for (int i = 0; i < N; i++) {
            dp[i][0] = total[i][0];
        }

        for (int j = 1; j < M; j++) {
            for (int i = 0; i < N; i++) {
                // 0행
                if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + total[i][j];
                } else {
                    dp[i][j] = Math.max(
                            Math.max(
                            dp[i][j - 1] + total[i][j], // 오른쪽
                            dp[i - 1][j - 1] + total[i][j] // 대각선
                            ),
                            dp[i-1][j] - apple[i][j] // 아래
                    );
                }
            }
        }
    }

    private static void totalPrefixSum() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                total[i][j] = appleSum[i][j] + bananaSum[i][j];
            }
        }
    }

    private static void applePrefixSum() {
        for (int j = 0; j < M; j++) {
            for (int i = N - 1; i > 0; i--) {
                appleSum[i - 1][j] = appleSum[i][j] + apple[i][j];
            }
        }
    }

    private static void bananaPrefixSum() {
        for (int j = 1; j < M; j++) {
            for (int i = 0; i < N; i++) {
                bananaSum[i + 1][j] = bananaSum[i][j] + banana[i][j];
            }
        }
    }

    private static void print(int[][] obj) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(obj[i][j]+" ");
            }
            System.out.println();
        }
    }
}
