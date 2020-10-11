package FastCampus_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Dijkstra_algorithm {
    public static void main(String[] args) throws IOException {
        Dijkstra_pq(0);
    }

    private static void Dijkstra_pq(int start) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //정점의 개수 , 간선의 개수
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<Edge>[] adj = new ArrayList[V];
        for (int i = 0; i  < V; i++)
            adj[i] = new ArrayList<>();

        //간선의 출발, 도착, 가중치를 읽어들여서 그래프의 연결상태를 저장
        for (int i = 0; i < E; i++) {
            //출발 노드, 도착 노드, 가중치
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int weigth = Integer.parseInt(st.nextToken());
            adj[from].add(new Edge(dest, weigth));
        }

        //우선순위 큐, 방문 체크
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] check = new boolean[V];

        //첫노드로부터의 모든 노드들의 거리배열
        Edge[] D = new Edge[V];
        //배열중 출발 노드만 거리가 0 나머지는 최대값으로 설정
        for (int i = 0; i < V; i++){
            if (i == start)
                D[i] = new Edge(i, 0);
            else
                D[i] = new Edge(i, Integer.MAX_VALUE);
        }
        //우선순위큐에 시작 노드를 넣고 시작
        pq.add(D[start]);
        while (!pq.isEmpty())
        {
            //우선순위 큐에 가장 위에있는 노드 및 가중치를 가져온다
            Edge nowV = pq.poll();
            //edge.v에 연결되어있는 간선 리스트를 가져온다
            for (Edge nextV : adj[nowV.v]){
                //시작노드부터 다음 노드에 대한 기존 가중치보다 지금까지의 경로 + 다음 노드로의 경로 가중치 합이 더 작다면 변경
                if (!check[nextV.v] && D[nextV.v].weight > D[nowV.v].weight + nextV.weight) {
                    D[nextV.v].weight = D[nowV.v].weight + nextV.weight;
                    pq.add(D[nextV.v]);
                }
            }
            check[nowV.v] = true;
        }
        System.out.println(Arrays.toString(D));
    }
}

class Edge implements Comparable<Edge> {
    int v, weight;

    public Edge(int v, int weight) {
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return weight + " ";
    }
}