package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 안정적인네트워크_2406 {
    static int N, M;
    static int[][] cost;
    static int[] parents;
    static int edgeCount;
    static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.c));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (find(u) != find(v)) {
                edgeCount++;
                union(u, v);
            }

        }

        cost = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            if (i == 1) continue;
            for (int j = 1; j < N + 1; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (i == j || j == 1) continue;
                pq.add(new Edge(i, j, cost));
            }
        }

        mst();
    }

    private static void mst() {
        int totalCost = 0;
        ArrayList<Edge> list = new ArrayList<>();

        while (!pq.isEmpty() && edgeCount < N - 2) {
            Edge now = pq.poll();

            if (find(now.u) != find(now.v)) {
                totalCost += now.c;
                edgeCount++;
                list.add(now);
                union(now.u, now.v);
            }
        }

        System.out.println(totalCost + " " + list.size());
        for (Edge edge : list) {
            System.out.println(edge.u + " " + edge.v);
        }
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);
        parents[parents[u]] = parents[v];
    }

    private static int find(int u) {
        if (parents[u] == u) return u;
        return parents[u] = find(parents[u]);
    }

    private static class Edge
    {
        int u, v, c;

        public Edge(int u, int v, int c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }
    }
}
