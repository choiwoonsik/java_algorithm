package 최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class 도시건설_21924 {
    static int[] parent;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long MAX = 0;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) parent[i] = i;
        pq = new PriorityQueue<>(Comparator.comparingInt(E -> E.c));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u, v, c));
            MAX += c;
        }

        long MIN = mst(N);
        if (MIN == -1) System.out.println(MIN);
        else System.out.println(MAX - MIN);
    }

    private static long mst(int N) {
        long minCost = 0;
        int edgeCnt = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (find(cur.u) != find(cur.v)) {
                union(cur.u, cur.v);
                minCost += cur.c;
                edgeCnt++;
            }
            if (edgeCnt == N - 1) {
                return minCost;
            }
        }
        return -1;
    }

    private static int find(int u) {
        if (parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);
        parent[v] = parent[parent[u]];
    }

    private static class Edge {
        int u, v, c;

        public Edge(int u, int v, int c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }
    }
}
