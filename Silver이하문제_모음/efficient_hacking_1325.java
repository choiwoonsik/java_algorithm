package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class efficient_hacking_1325 {
    static int N, M;
    static ArrayList<Integer>[] adj = new ArrayList[10001];
    static boolean[] visited = new boolean[10001];
    static int[] saved = new int[10001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i < N + 1; i++)
            adj[i] = new ArrayList<>();

        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int end = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            adj[end].add(start);
        }

        for (int i = 1; i <= N; i++) {
            BFS(i);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++)
            max = Math.max(max, saved[i]);

        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (saved[i] == max)
                str.append(i).append(" ");
        }
        System.out.println(str);
    }

    private static void BFS(int com) {
        visited = new boolean[10001];
        Queue<Integer> q = new LinkedList<>();
        q.add(com);
        visited[com] = true;
        while (!q.isEmpty())
        {
            int now = q.poll();
            for (int i = 0; i < adj[now].size(); i++) {
                int y = adj[now].get(i);
                if (!visited[y]) {
                    q.add(y);
                    visited[y] = true;
                    saved[y]++;
                }
            }
        }
    }
}
