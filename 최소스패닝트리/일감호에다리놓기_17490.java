package 최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 일감호에다리놓기_17490 {
    static int N, M;
    static long K;
    static int[] parent;
    static int[] stoneCost;
    static boolean[] isBlocked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());
        parent = new int[N + 1];
        stoneCost = new int[N + 1];
        isBlocked = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
            stoneCost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            isBlocked[end] = true;
        }

        makeUnion();
        makeBridge();
    }

    private static void makeUnion() {
        for (int u = 1; u <= N; u++) {
            int v = (u + 1) % (N + 1);
            if (v == 0) v = 1;
            if (find(u) != find(v)) {
                if (isBlocked[v]) continue;
                union(u, v);
            }
        }
    }

    private static int find(int node) {
        if (parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (stoneCost[u] <= stoneCost[v]) {
            parent[v] = parent[parent[u]];
        } else parent[u] = parent[parent[v]];
    }

    private static void makeBridge() {
        long needCost = 0;
        int parentCount = 0;

        for (int i = 1; i <= N; i++) {
            if (find(i) == i) {
                needCost += stoneCost[i];
                parentCount++;
            }
        }
        if (needCost <= K || parentCount <= 1) {
            System.out.println("YES");
        } else System.out.println("NO");
    }
}
