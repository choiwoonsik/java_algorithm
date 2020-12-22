import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class LimitOfWeight_1939 {
    static boolean[] visited;
    static int end;
    static ArrayList<AtoB_weight>[] graph;
    public static void main(String[] args) throws IOException {
        /*
        N, M : N개의 섬, M개의 다리 정보
        A, B, C : a - b 다리의 중량제한 C인 다리

        start - end  지점 2개의 최대 한번에 옮길수 있는 무게는?
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for (int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList<>();

        int left = 0;
        int right = 1000000000;
        visited = new boolean[N+1];

        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int limit = Integer.parseInt(st.nextToken());
            graph[from].add(new AtoB_weight(from, to, limit));
            graph[to].add(new AtoB_weight(to, from, limit));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int ret = Integer.MIN_VALUE;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (BFS(start, mid)) {
                ret = mid;
                left = mid + 1;
            } else
                right = mid - 1;
            Arrays.fill(visited, false);
        }
        System.out.println(ret);
    }

    private static boolean BFS(int start, int mid) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty())
        {
            int next = queue.poll();
            for (AtoB_weight line : graph[next])
            {
                if (!visited[line.to] && line.weight >= mid) {
                    queue.add(line.to);
                    visited[line.to] = true;
                }
            }
        }
        return visited[end];
    }

    private static boolean DFS(int start, int mid) {

        visited[start] = true;
        if (start == end)
            return true;
        for (AtoB_weight line : graph[start]) {
            //방문안한 다음 노드에 대해서 mid가 다음 무게에 맞을때까지 계속 줄인다
            if (!visited[line.to] && mid <= line.weight) {
                if (DFS(line.to, mid))
                    return true;
            }
        }
        return false;
    }
}

class AtoB_weight
{
    int from;
    int to;
    int weight;
    AtoB_weight(int from, int to, int weight)
    {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
