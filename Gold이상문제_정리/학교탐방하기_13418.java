package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 학교탐방하기_13418 {
    static int N, M;
    static ArrayList<Node> nodes = new ArrayList<>();
    static int[] parents;
    private static class Node
    {
        int from;
        int to;
        int walk;

        public Node(int from, int to, int walk) {
            this.from = from;
            this.to = to;
            this.walk = walk;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int walk = Integer.parseInt(st.nextToken()) == 0 ? 1 : 0;
            nodes.add(new Node(u, v, walk));
        }

        initParents();
        int hardest = (int) Math.pow(findRoute(-1), 2);
        int easiest = (int) Math.pow(findRoute(1), 2);

        System.out.println(hardest - easiest);
    }

    private static int findRoute(int weight) {
        initParents();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(N -> N.walk * weight));
        pq.addAll(nodes);
        int totalWalk = 0;
        int countEdge = 0;

        while (!pq.isEmpty() && countEdge < N) {

            Node now = pq.poll();

            if (find(now.from) != find(now.to)) {
                totalWalk += now.walk;
                countEdge++;
                union(now.from, now.to);
            }
        }
        return totalWalk;
    }

    private static void initParents() {
        for (int i = 0; i < N + 1; i++) {
            parents[i] = i;
        }
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);
        parents[u] = v;
    }

    private static int find(int u) {
        if (parents[u] == u) return u;
        return parents[u] = find(parents[u]);
    }
}
