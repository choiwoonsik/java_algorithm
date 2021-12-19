package 최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MST게임_16202 {
    static int N;
    static int M;
    static int K;
    static int[] parent;
    static PriorityQueue<Edge> allPq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        allPq = new PriorityQueue<>(Comparator.comparing(E -> E.c));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            allPq.add(new Edge(u, v, i + 1));
        }

        boolean isOver = false;
        for (int i = 0; i < K; i++) {
            int cost = 0;
            if (!isOver) {
                if ((cost = MSTGame()) == 0)
                    isOver = true;
            }
            answer.append(cost).append(" ");
            allPq.poll();
        }
        System.out.print(answer);
    }

    private static int MSTGame() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(E -> E.c));
        pq.addAll(allPq);
        for (int i = 0; i < N + 1; i++) parent[i] = i;
        int totalCost = 0;
        int edgeCount = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (find(cur.u) != find(cur.v)) {
                union(cur.u, cur.v);
                totalCost += cur.c;
                edgeCount++;
            }

            if (edgeCount == N - 1) {
                return totalCost;
            }
        }

        return 0;
    }

    private static int find(int node) {
        if (parent[node] == node) return node;
        return parent[node] = find(parent[node]);
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
