package 최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 세부_13905 {
    static int N, M;
    static int S, E;
    static ArrayList<Edge>[] adj;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dp = new int[N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[u].add(new Edge(v, c));
            adj[v].add(new Edge(u, c));
        }
        go();
    }

    private static void go() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(E -> -E.w));
        for (Edge edge : adj[S]) {
            pq.add(edge);
            dp[edge.pos] = edge.w;
        }
        dp[S] = 1000001;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (now.pos == E) {
                System.out.println(now.w);
                return;
            }

            for (Edge next : adj[now.pos]) {
                int max = Math.min(now.w, next.w);
                if (dp[next.pos] < max) {
                    dp[next.pos] = max;
                    pq.add(new Edge(next.pos, max));
                }
            }
        }
        System.out.println(0);
    }

    private static class Edge
    {
        int pos;
        int w;

        Edge(int pos, int w) {
            this.pos = pos;
            this.w = w;
        }
    }
}
