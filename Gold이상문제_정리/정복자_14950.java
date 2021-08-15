package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 정복자_14950 {
    static int N, M, W;
    static int[] parents;
    static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
    private static class Node
    {
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        for (int i = 0; i < N + 1; i++) parents[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Node(u, v, c));
        }

        int answer = conquer();
        System.out.println(answer);
    }

    private static int conquer() {
        int totalCost = 0;
        int totalEdge = 0;
        int plus = 0;

        while (!pq.isEmpty() && totalEdge < N - 1) {
            Node now = pq.poll();

            if (find(now.from) != find(now.to)) {
                totalEdge++;
                totalCost += (now.cost + plus);
                plus += W;
                union(now.from, now.to);
            }
        }
        return totalCost;
    }

    private static int find(int u) {
        if (parents[u] == u) return u;
        else return parents[u] = find(parents[u]);
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);
        parents[parents[u]] = parents[v];
    }
}
