package 분리집합_UnionFind;

import java.io.*;
import java.util.StringTokenizer;

public class 친구비_16562 {
    static int N, M, K;
    static int[] friendCost;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        friendCost = new int[N + 1];
        parent = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            friendCost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (find(u) != find(v)) union(u, v);
        }

        int needCost = 0;
        for (int i = 1; i <= N; i++) {
            if (find(i) == i) needCost += friendCost[i];
        }

        if (needCost <= K) {
            bw.write(needCost+"");
        } else {
            bw.write("Oh no");
        }
        bw.flush();
        bw.close();
    }

    private static int find(int node) {
        if (parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);

        if (friendCost[u] <= friendCost[v]) {
            parent[v] = parent[parent[u]];
        } else parent[u] = parent[parent[v]];
    }
}
