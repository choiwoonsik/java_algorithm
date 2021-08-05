package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
7
8
5 1 2
5 2 2
7 5 2
6 5 2
6 3 3
6 4 4
7 6 3
7 4 5
 */
public class 장난감조립_2637 {
    static int N, M;
    static ArrayList<part>[] adj;
    static boolean[] isBasic;
    static int[][] dp;
    static int[] indegree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dp = new int[N + 1][N + 1];
        adj = new ArrayList[N + 1];
        isBasic = new boolean[N + 1];
        indegree = new int[N + 1];
        Arrays.fill(isBasic, true);
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int upperPart = Integer.parseInt(st.nextToken());
            int lowerPart = Integer.parseInt(st.nextToken());
            int neddCount = Integer.parseInt(st.nextToken());
            adj[lowerPart].add(new part(upperPart, neddCount));
            indegree[upperPart]++;
        }

        makeMasterpiece();

        StringBuilder answer = new StringBuilder();
        for (int j = 0; j < N + 1; j++) {
            if (dp[N][j] != 0) answer.append(j).append(" ").append(dp[N][j]).append("\n");
        }
        System.out.print(answer);
    }

    private static void makeMasterpiece() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] == 0) {
                dp[i][i] = 1;
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {

            int base = queue.poll();

            for (part upper : adj[base]) {
                for (int i = 1; i < N + 1; i++) {
                    dp[upper.number][i] += dp[base][i] * upper.count;
                }
                indegree[upper.number]--;
                if (indegree[upper.number] == 0) queue.add(upper.number);
            }
        }

    }

    private static class part
    {
        int number;
        int count;

        public part(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }
}
