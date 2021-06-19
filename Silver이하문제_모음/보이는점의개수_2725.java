package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class 보이는점의개수_2725 {
    static int[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder str = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        visited = new int[1001][1001];

        for (int t = 0; t < T; t++)
        {
            int N = Integer.parseInt(br.readLine());
            // 모든 좌표값에 대해서 (y, x)좌표값이 서로소라면 그것은 보이는 점의 좌표이다
            int cnt = 0;
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= N; j++) {
                    if (gcd(i, j) == 1) {
                        cnt++;
                    }
                }
            }
            str.append(cnt).append("\n");
        }
        System.out.print(str);
    }

    private static int gcd(int i, int j) {
        if (visited[i][j] == 0)
        {
            if (j == 0)
                return i;
            return visited[i][j] = gcd(j, i % j);
        }
        return visited[i][j];
    }
}
