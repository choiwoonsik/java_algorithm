import java.util.*;
import java.io.*;

public class 연결요소의개수_11724 {
    static boolean[] visited;
    static int[][] map;
    static int N,M;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            map[u][v] = 1;
            map[v][u] = 1;
        }

        int count = 0;
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                count++;
                check(i);
            }
        }
        System.out.println(count);
    }

    private static void check(int i) {
        visited[i] = true;
        for (int j = 1; j <= N; j++) {
            if (map[i][j] == 1 && !visited[j])
                check(j);
        }
    }
}
