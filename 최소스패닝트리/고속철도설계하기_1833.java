package 최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 고속철도설계하기_1833 {
    static int edgeCount;
    static int total;
    static int N;
    static int[][] cost;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(E -> E.c));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        parent = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int c = Integer.parseInt(st.nextToken());
                if (j <= i) continue;
                cost[i][j] = c;
                cost[j][i] = c;
                if (c < 0) {
                    union(i, j);
                    edgeCount++;
                    total += c * -1;
                }
                else pq.add(new Edge(i, j, c));
            }
        }
        ArrayList<Edge> needBridge = makeBridge();
        StringBuilder answer = new StringBuilder();

        answer.append(total).append(" ").append(needBridge.size()).append("\n");
        for (Edge edge : needBridge) {
            answer.append(edge.u + 1).append(" ").append(edge.v + 1).append("\n");
        }
        System.out.print(answer);
    }

    private static ArrayList<Edge> makeBridge() {
        int tmpCost = 0;
        int tmpEdgeCount = 0;
        ArrayList<Edge> bridges = new ArrayList<>();

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (find(cur.u) != find(cur.v)) {
                union(cur.u, cur.v);
                tmpCost += cost[cur.u][cur.v];
                tmpEdgeCount++;
                bridges.add(cur);
            }
            if (tmpEdgeCount == N - 1) {
                break;
            }
        }
        total += tmpCost;
        return bridges;
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
