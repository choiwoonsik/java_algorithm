package algorithm_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Dijkstra_algo {
    public static void main(String[] args) throws IOException {
        Dijkstra(0);
    }

    private static void Dijkstra(int start) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<Node>[] adjcent = new ArrayList[V];
        boolean[] visited = new boolean[V];
        Node[] Distances = new Node[V];

        for (int i = 0; i < V; i++)
            adjcent[i] = new ArrayList<>();

        for (int i = 0; i < E; i++)
        {
            st = new StringTokenizer(br.readLine());
            int go = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjcent[go].add(new Node(end, weight));
        }

        for (int i = 0; i < V; i++)
        {
            if (i == start)
                Distances[i] = new Node(i, 0);
            else
                Distances[i] = new Node(i, Integer.MAX_VALUE);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(Distances[start]);

        while (!pq.isEmpty())
        {
            Node now = pq.poll();
            for (Node next : adjcent[now.V]){
                //배열에서 next노드까지의 길이가  now노드까지의 길이 + now에서 next까지의 길이 보다 크면 바꾼다
                if (!visited[next.V] && Distances[next.V].weight > Distances[now.V].weight + next.weight)
                {
                    Distances[next.V].weight = Distances[now.V].weight + next.weight;
                    pq.add(Distances[next.V]);
                }
            }
            visited[now.V] = true;
        }
        System.out.println(Arrays.toString(Distances));
    }

    private static class Node implements Comparable<Node>
    {
        int V;
        int weight;
        Node(int v, int weight)
        {
            this.V = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(weight, o.weight);
        }

        @Override
        public String toString() {
            return this.weight+" ";
        }
    }
}
