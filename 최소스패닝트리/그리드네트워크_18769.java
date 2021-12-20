package 최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 그리드네트워크_18769 {
    static int T;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(E -> E.c));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            pq.clear();
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            parent = new int[R * C + 1];
            for (int i = 0; i < R * C + 1; i++) {
                parent[i] = i;
            }

            // 가로 간선
            for (int r = 1; r <= R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 1; c < C; c++) {
                    int u = (r - 1) * C + c;
                    int v = (r - 1) * C + c + 1;
                    int cost = Integer.parseInt(st.nextToken());
                    pq.add(new Edge(u, v, cost));
                }
            }
            // 세로 간선
            for (int r = 1; r <= R - 1; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 1; c <= C; c++) {
                    int u = (r - 1) * C + c;
                    int v = r * C + c;
                    int cost = Integer.parseInt(st.nextToken());
                    pq.add(new Edge(u, v, cost));
                }
            }
            answer.append(minCost(R * C - 1)).append("\n");
        }
        System.out.print(answer);
    }

    private static int minCost(int MAX) {
        int totalCost = 0;
        int edgeCount = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (find(cur.u) != find(cur.v)) {
                union(cur.u, cur.v);
                edgeCount++;
                totalCost += cur.c;
            }
            if (edgeCount == MAX) {
                return totalCost;
            }
        }
        return totalCost;
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
