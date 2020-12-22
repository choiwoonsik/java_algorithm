package FastCampus_algorithm;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class KruskalAlgorithm {

    static Dictionary<Character, Character> parent = new Hashtable<>();
    static Dictionary<Character, Integer> rank = new Hashtable<>();

    public static void main(String[] args) {
        char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        List<Edge_kruskal> list = new ArrayList<>();

        list.add(new Edge_kruskal(7, 'A', 'B'));
        list.add(new Edge_kruskal(7, 'A', 'B'));
        list.add(new Edge_kruskal(5, 'A', 'D'));
        list.add(new Edge_kruskal(7, 'B', 'A'));
        list.add(new Edge_kruskal(8, 'B', 'C'));
        list.add(new Edge_kruskal(9, 'B', 'D'));
        list.add(new Edge_kruskal(7, 'B', 'E'));
        list.add(new Edge_kruskal(8, 'C', 'B'));
        list.add(new Edge_kruskal(5, 'C', 'E'));
        list.add(new Edge_kruskal(5, 'D', 'A'));
        list.add(new Edge_kruskal(9, 'D', 'B'));
        list.add(new Edge_kruskal(7, 'D', 'E'));
        list.add(new Edge_kruskal(6, 'D', 'F'));
        list.add(new Edge_kruskal(7, 'E', 'B'));
        list.add(new Edge_kruskal(5, 'E', 'C'));
        list.add(new Edge_kruskal(7, 'E', 'D'));
        list.add(new Edge_kruskal(8, 'E', 'F'));
        list.add(new Edge_kruskal(9, 'E', 'G'));
        list.add(new Edge_kruskal(6, 'F', 'D'));
        list.add(new Edge_kruskal(8, 'F', 'E'));
        list.add(new Edge_kruskal(11, 'F', 'G'));
        list.add(new Edge_kruskal(9, 'G', 'E'));
        list.add(new Edge_kruskal(11, 'G', 'F'));

        List<Edge_kruskal> result = kruskal(vertices, list);
        result.forEach(System.out::println);
    }
    private static List<Edge_kruskal> kruskal(char[] vertices , List<Edge_kruskal> list)
    {
        List<Edge_kruskal> MST = new ArrayList<>();

        for (char node : vertices){
            make_set(node);
        }

        Collections.sort(list);

        //싸이클 없는 간선 연결하여 그래프 만들기
        for (Edge_kruskal edge : list) {
            char node_v = edge.from;
            char node_u = edge.to;

            if (find(node_v) != find(node_u)){
                union(node_v, node_u);
                MST.add(edge);
            }
        }

        return MST;
    }

    // union-by-rank 기법
    //union : 각 노드가 서로 다른 set이라면, 같은 그래프에 속하지 않는다면 합쳐준다
    private static void union(char node_v, char node_u) {
        char root1 = find(node_v);
        char root2 = find(node_u);
        if (rank.get(root1) > rank.get(root2)) {
            //root1이 더 rank가 높다면 root2를 root1의 자식노드로 붙여준다
            parent.put(root2, root1);
        }
        else {
            parent.put(root1, root2);
            if (rank.get(root1) == rank.get(root2)) {
                rank.put(root2, rank.get(root2)+1);
            }
        }
    }

    //find : 각 노드의 루트 노드를 비교하는 함수
    private static char find(char node) {
        // path compression 기법
        // 부모노드를 찾아 올라가면서 만나는 노드들을 모두 루트 노드의 자식노드로 만들어준다
        if (parent.get(node) != node){
            parent.put(node, find(parent.get(node)));
        }
        return parent.get(node);
    }

    //각각의 노드들이 부모노드로 자신을 갖고 높이는 0으로 초기화
    private static void make_set(char node) {
        parent.put(node, node);
        rank.put(node, 0);
    }
}

class Edge_kruskal implements Comparable<Edge_kruskal>
{
    int weigh;
    char from;
    char to;
    Edge_kruskal(int weigh, char from, char to)
    {
        this.weigh = weigh;
        this.from = from;
        this.to = to;
    }

    @Override
    public int compareTo(Edge_kruskal o) {
        return this.weigh < o.weigh ? -1 : 1;
    }

    @Override
    public String toString() {
        return this.weigh+", " +this.from+" - "+this.to;
    }
}
