package sds_algorithm_class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main5 {
    static int N;
    static StringBuilder answer;
    static ArrayList<Edge>[] adj;
    static HashMap<Integer, Integer> mostAdjMap;
    static PriorityQueue<int[]> adjQ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        answer = new StringBuilder();
        mostAdjMap = new HashMap<>();

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()) + 1;
            adj = new ArrayList[N];
            mostAdjMap.clear();
            for (int j = 1; j < N; j++) adj[j] = new ArrayList<>();
            for (int j = 1; j < N-1; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                adj[from].add(new Edge(to, cost));
                adj[to].add(new Edge(from, cost));
                if (mostAdjMap.containsKey(from)) mostAdjMap.put(from, mostAdjMap.get(from) + cost);
                else mostAdjMap.put(from, cost);
                if (mostAdjMap.containsKey(to)) mostAdjMap.put(to, mostAdjMap.get(to) + cost);
                else mostAdjMap.put(to, cost);
            }
            if (i <= 9)
                findOut(i);
        }
        System.out.print(answer);
    }

    private static void findOut(int t) {
        adjQ = new PriorityQueue<>(Comparator.comparing(n -> -n[1]));
        for (int node : mostAdjMap.keySet()) {
            adjQ.add(new int[]{node, mostAdjMap.get(node)});
        }

        int totalCost = 0;
        int minCost = 999999999;
        int count = 0;
        while (!adjQ.isEmpty())
        {
            int place = adjQ.poll()[0];

            for (int home = 1; home < N; home++) {
                if (home == place) continue;
                totalCost += moveCost(home, place);
            }

            if (totalCost <= minCost) {
                minCost = totalCost;
                count++;
            } else break;
            totalCost = 0;
        }
        answer.append("#").append(t).append(" ");
        answer.append(count).append(" ").append(minCost).append("\n");
    }

    private static int moveCost(int home, int place) {
        PriorityQueue<Dot> pq = new PriorityQueue<>(Comparator.comparing(n -> n.cost));
        boolean[] visited = new boolean[N];
        pq.add(new Dot(home, 0));
        visited[home] = true;

        while (!pq.isEmpty()) {
            Dot cur = pq.poll();

            if (cur.now == place) {
                return cur.cost;
            }

            for (Edge next : adj[cur.now]) {
                if (next.to != place && adj[next.to].size() == 1) continue;
                if (visited[next.to]) continue;
                visited[next.to] = true;
                pq.add(new Dot(next.to, cur.cost + next.c));
            }
        }

        return -1;
    }

    private static class Dot {
        int now;
        int cost;

        public Dot(int now, int cost) {
            this.now = now;
            this.cost = cost;
        }
    }

    private static class Edge {
        int to, c;

        public Edge(int to, int c) {
            this.to = to;
            this.c = c;
        }
    }
}
