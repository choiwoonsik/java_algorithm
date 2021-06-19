package algorithm_정리;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Advanced_prim_algorithm {
    static boolean[] visited;
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

        int N = 7; //정점의 개수
        visited = new boolean[N];

        List<Edge_prim> result = prim('A', list);
        int sum = result.stream().mapToInt(s -> s.weight).sum();
        System.out.println(sum);
    }

    private static List<Edge_prim> prim(char start, List<Edge_prim> list) {

        List<Edge_prim> MST = new ArrayList<>();

        List<Edge_prim>[] adjacent_Edge = new ArrayList[visited.length];
        for (int i = 0; i < visited.length; i++)
            adjacent_Edge[i] = new ArrayList<>();

        for (Edge_prim edge : list){
            adjacent_Edge[edge.from - 'A'].add(new Edge_prim(edge.weight, edge.from, edge.to));
            adjacent_Edge[edge.to - 'A'].add(new Edge_prim(edge.weight, edge.to, edge.from));
        }

        visited[start - 'A'] = true;
        PriorityQueue<Edge_prim> pq = new PriorityQueue<>(adjacent_Edge[start - 'A']);

        while (!pq.isEmpty()){
            Edge_prim now = pq.poll();
            if (!visited[now.to - 'A']) {
                visited[now.to - 'A'] = true;
                MST.add(now);
                for (Edge_prim next : adjacent_Edge[now.to - 'A']){
                    if (!visited[next.to - 'A'])
                        pq.add(next);
                }
            }
        }

        return MST;
    }
}
