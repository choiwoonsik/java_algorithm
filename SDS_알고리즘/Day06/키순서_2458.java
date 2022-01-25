package SDS_알고리즘.Day06;

import java.io.*;
import java.util.*;

public class 키순서_2458 {
    static boolean[][] know;
    static boolean[][] knowReverse;

    static int N;
    static int M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        know = new boolean[N + 1][N + 1];
        knowReverse = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            know[u][v] = true;
            knowReverse[v][u] = true;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (know[i][k] && know[k][j]) know[i][j] = true;
                }
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (knowReverse[i][k] && knowReverse[k][j]) knowReverse[i][j] = true;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                know[i][j] |= knowReverse[i][j];
            }
        }

        int count = 0;
        boolean notKnow;
        for (int i = 1; i <= N; i++) {
            notKnow = false;

            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (!know[i][j]) {
                    notKnow = true;
                    break;
                }
            }
            if (!notKnow) count++;
        }

        System.out.println(count);
    }
}
