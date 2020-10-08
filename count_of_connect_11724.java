import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class count_of_connect_11724 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Queue<Integer> queue = new LinkedList<>();
    static int N;   //점의 개수
    static int L;   //선의 개수
    static boolean[][] graph;   //그래프 간선 정보 저장
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = n[0];
        L = n[1];
        graph = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];
        int count = 0;
        for (int i = 0; i < L; i++) {
            int[] l = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[l[0]][l[1]] = true;
            graph[l[1]][l[0]] = true;
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                count++;
                BFS(i);
            }
        }
        System.out.println(count);
    }
    private static void BFS(int dot)
    {
        queue.add(dot);
        while (!queue.isEmpty()) {
            int pos = queue.poll();
            visited[pos] = true;
            for (int i = 1; i <= N; i++) {
                if (graph[pos][i]) {
                    graph[pos][i] = false;
                    queue.add(i);
                }
            }
        }
    }
}
