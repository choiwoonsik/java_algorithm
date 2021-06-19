package algorithm_정리;

import java.util.*;

public class PrimAlgorithm {
    static List<Edge_prim>[] adjacent_edges;
    public static void main(String[] args) {
        List<Edge_prim> list = new ArrayList<>();
        list.add(new Edge_prim(7, 'A', 'B'));
        list.add(new Edge_prim(5, 'A', 'D'));
        list.add(new Edge_prim(8, 'B', 'C'));
        list.add(new Edge_prim(9, 'B', 'D'));
        list.add(new Edge_prim(7, 'B', 'E'));
        list.add(new Edge_prim(5, 'C', 'E'));
        list.add(new Edge_prim(7, 'D', 'E'));
        list.add(new Edge_prim(6, 'D', 'F'));
        list.add(new Edge_prim(8, 'E', 'F'));
        list.add(new Edge_prim(9, 'E', 'G'));
        list.add(new Edge_prim(11, 'F', 'G'));

        int N = 7; // 정점의 개수
        adjacent_edges = new ArrayList[N];
        for (int i = 0; i < N; i++)
            adjacent_edges[i] = new ArrayList<>();

        char start_node = 'A';
        List<Edge_prim> result = prim(start_node, list);
        int sum = result.stream().mapToInt(s -> s.weight).sum();
        System.out.println(sum);
        result.forEach(System.out::println);
    }

    private static List<Edge_prim> prim(char start_node, List<Edge_prim> list) {
        //최소 신장 트리
        List<Edge_prim> MST = new ArrayList<>();

        //모든 간선 정보 저장
        for (Edge_prim edge : list){
            adjacent_edges[edge.from - 'A'].add(new Edge_prim(edge.weight, edge.from, edge.to));
            adjacent_edges[edge.to - 'A'].add(new Edge_prim(edge.weight, edge.to, edge.from));
        }

        //연결된 모든 정점들의 집합
        List<Character> connected_nodes = new ArrayList<>();
        connected_nodes.add(start_node);

        // 후보군 간선 Q - start노드에 연결된 간선 정보들이 들어간다
        PriorityQueue<Edge_prim> candidate_edge_list = new PriorityQueue<>(adjacent_edges[start_node - 'A']);

        while (!candidate_edge_list.isEmpty())
        {
            //간선의 가중치가 가장 작은 간선을 뽑는다
            Edge_prim edge = candidate_edge_list.poll();
            // 연결된 노드에 포함되지 않는다면 넣는다
            if (!connected_nodes.contains(edge.to)) {
                connected_nodes.add(edge.to);
                MST.add(edge);
                //다음 노드의 인접 간선들에 대해 이미 연결된 노드에 포함되지 않는다면! 후보군 간선 Q에 넣는다
                for (Edge_prim nextEdge : adjacent_edges[edge.to - 'A']) {
                    if (!connected_nodes.contains(nextEdge.to))
                        candidate_edge_list.add(nextEdge);
                }
            }
        }
        return MST;
    }
}

class Edge_prim implements Comparable<Edge_prim>
{
    int weight;
    char from;
    char to;
    Edge_prim (int weight, char from, char to){
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return this.weight +" : "+this.from + " - " + this.to;
    }

    @Override
    public int compareTo(Edge_prim o) {
        return this.weight < o.weight ? -1 : 1;
    }
}
