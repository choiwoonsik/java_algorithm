package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MinSpanningTree_1197 {
    /*
    정점의 개수 1 ~ 10000 V
    간선의 개수 1 ~ 100000 E

    a , b, c  from to weight

     */

    static Dictionary<Integer, Integer> parent = new Hashtable<>();
    static Dictionary<Integer, Integer> rank = new Hashtable<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String[] VE =  br.readLine().split(" ");
        int V = Integer.parseInt(VE[0]);
        int E = Integer.parseInt(VE[1]);

        int[] verteces = new int[V+1];
        for (int i = 1; i <= V; i++)
            verteces[i] = i;

        List<Edge_1197> list = new ArrayList<>();
        for (int i = 0; i < E; i++)
        {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.add(new Edge_1197(from, to, weight));
        }

        List<Edge_1197> result = Kruskal(verteces, list);
        int sum = result.stream().mapToInt(e -> e.weight).sum();
        System.out.println(sum);
    }

    private static List<Edge_1197> Kruskal(int[] verteces, List<Edge_1197> list) {

        List<Edge_1197> MST = new ArrayList<>();

        //각 노드마다 set 만들기
        for (int edge : verteces)
            make_set(edge);

        //정렬하기
        Collections.sort(list);

        //모든 리스트에 대해 루트 노드가 같다면 합치기, mst에 추가
        for (Edge_1197 edge : list){
            int from = edge.from;
            int to = edge.to;
            if (find(from) != find(to)){
                union(from, to);
                MST.add(edge);
            }
        }

        return MST;
    }

    private static void union(int from, int to) {
        int rootEdge1 = parent.get(from);
        int rootEdge2 = parent.get(to);

        if (rank.get(rootEdge1) < rank.get(rootEdge2)){
            parent.put(rootEdge1, rootEdge2);
        }
        else {
            parent.put(rootEdge2, rootEdge1);
            if (rank.get(rootEdge1) == rank.get(rootEdge2)){
                rank.put(rootEdge1, rank.get(rootEdge1) + 1);
            }
        }
    }

    //root 찾기
    private static int find(int edge) {
        if (parent.get(edge) != edge) {
            parent.put(edge, find(parent.get(edge)));
        }
        return parent.get(edge);
    }

    private static void make_set(int vertece) {
        parent.put(vertece, vertece);
        rank.put(vertece, 0);
    }
}

class Edge_1197 implements Comparable<Edge_1197>
{
    int from;
    int to;
    int weight;
    Edge_1197 (int from, int to, int weight)
    {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge_1197 o) {
        return this.weight < o.weight ? -1 : 1;
    }
}
