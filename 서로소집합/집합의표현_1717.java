package 서로소집합;

import java.util.*;
import java.io.*;
/*
7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 1
 */
public class 집합의표현_1717 {
    static int N;
    static int M;
    static int[] parent;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (type == 0) {
                union(u, v);
            } else if (type == 1) {
                if (find(u) == find(v)) answer.append("yes");
                else answer.append("no");
                answer.append("\n");
            }
        }
        System.out.print(answer);
    }

    private static int find(int u) {
        if (parent[u] == u) return u;
        else return parent[u] = find(parent[u]);
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);
        parent[u] = v;
    }
}
