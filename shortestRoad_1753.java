/*
정점의 개수 V 간선의 개수 E
시작 정점의 번호 K
E개의 start , end, weigh

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class shortestRoad_1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String[] VE = br.readLine().split(" ");
        int V = Integer.parseInt(VE[0]);
        int E = Integer.parseInt(VE[1]);

        List<Node_1753>[] list = new List[V+1];
        for (int i = 1; i <= V; i++)
            list[i] = new ArrayList<>();

        int[] vertex = new int[V+1];
        boolean[] visited = new boolean[V+1];
        for (int i = 1; i <= V; i++)
            vertex[i] = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());

        for (int i = 0; i < E; i++)
        {
            String[] UVW = br.readLine().split(" ");
            int u = Integer.parseInt(UVW[0]);
            int v = Integer.parseInt(UVW[1]);
            int w = Integer.parseInt(UVW[2]);
            list[u].add(new Node_1753(v, w));
        }

        vertex[start] = 0;
        int[] result = Dijkstra(start, list, vertex, visited);
        for (int i = 1; i < result.length; i++) {
            if (result[i] != Integer.MAX_VALUE)
                System.out.println(result[i]);
            else
                System.out.println("INF");
        }
    }
    private static int[] Dijkstra(int start, List<Node_1753>[] list, int[] vertex, boolean[] visited)
    {
        PriorityQueue<Node_1753> pq = new PriorityQueue<>();
        pq.add(new Node_1753(start, vertex[start]));
        while (!pq.isEmpty())
        {
            Node_1753 now = pq.poll();
            for (Node_1753 next : list[now.pos]){
                if (!visited[next.pos] && vertex[next.pos] > vertex[now.pos] + next.weigh){
                    vertex[next.pos] = vertex[now.pos] + next.weigh;
                    pq.add(new Node_1753(next.pos, vertex[next.pos]));
                }
            }
            visited[now.pos] = true;
        }
        return vertex;
    }
}
class Node_1753 implements Comparable<Node_1753>
{
    int pos;
    int weigh;
    Node_1753 (int pos, int weigh)
    {
        this.pos = pos;
        this.weigh = weigh;
    }

    @Override
    public int compareTo(Node_1753 o) {
        return this.weigh < o.weigh ? -1 : 1;
    }
}
