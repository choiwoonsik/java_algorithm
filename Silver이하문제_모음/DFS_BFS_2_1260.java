package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFS_BFS_2_1260 {
    static boolean[][] map;
    static boolean[] visited;
    static int N, M, V;
    static StringBuilder str = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        map = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            map[first][second] = true;
            map[second][first] = true;
        }

        DFS(V);
        str.append("\n");
        BFS(V);
        System.out.println(str);
    }

    private static void BFS(int v) {
        Queue<Integer> pq = new LinkedList<>();
        pq.add(v);
        visited = new boolean[N + 1];

        while (!pq.isEmpty())
        {
            int now = pq.poll();
            if (!visited[now]) {
                visited[now] = true;
                str.append(now).append(" ");
                for (int i = 1; i <= N; i++) {
                    if (!visited[i] && (map[now][i] || map[i][now])) {
                        pq.add(i);
                    }
                }
            }
        }
    }

    private static void DFS(int v) {
        if (!visited[v]) {
            visited[v] = true;
            str.append(v).append(" ");
            for (int i = 1; i <= N; i++) {
                if (map[v][i] || map[i][v]) {
                    DFS(i);
                }
            }
        }
    }
}
