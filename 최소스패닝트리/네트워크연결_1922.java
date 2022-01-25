package 최소스패닝트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
6
9
1 2 5
1 3 4
2 3 2
2 4 7
3 4 6
3 5 11
4 5 3
4 6 8
5 6 8

 */

public class 네트워크연결_1922 {
    static int N;
    static int M;
    static int totalCost;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[1001];

        for (int i = 0; i <= 1000; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Edge(f, t, c));
        }

        while (!pq.isEmpty()) {
            Edge curEdge = pq.poll();
            int from = curEdge.from;
            int to = curEdge.to;

            if (find(from) != find(to)) {
                union(from, to);
                totalCost += curEdge.cost;
            }
        }

        System.out.println(totalCost);
    }

    private static int find(int node) {
        if (parent[node] == node) return node;
        else return parent[node] = find(parent[node]);
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);
        parent[u] = v;
    }

    private static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
