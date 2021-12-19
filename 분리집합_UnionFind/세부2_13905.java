package 분리집합_UnionFind;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 세부2_13905 {
    static int N, M;
    static int S, E;
    static PriorityQueue<Edge> pq;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>(Comparator.comparing(E -> -E.w));
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u, v, c));
        }
        bw.write(go()+"");
        bw.flush();
        bw.close();
    }

    private static int go() {
        int answer = 1000001;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (find(now.u) != find(now.v)) {
                union(now.u, now.v);
                answer = Math.min(answer, now.w);
            }
            if (find(S) == find(E)) {
                return answer;
            }
        }
        return 0;
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            parent[v] = parent[parent[u]];
        }
    }

    private static int find(int node) {
        if (parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }

    private static class Edge
    {
        int u;
        int v;
        int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}
