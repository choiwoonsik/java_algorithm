import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class find_road_11403 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static int[][] make_map;
    static boolean[] visited;
    static int N;
    public static void main(String[] args) throws IOException
    {
        int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = n[0];
        map = new int[N][N];
        make_map = new int[N][N];
        for (int i = 0; i < N; i++)
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            BFS(i);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(make_map[i][j]+" ");
            }
            System.out.println();
        }
    }
    private static void BFS(int x)
    {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        while (!queue.isEmpty())
        {
            int now = queue.poll();
            visited[now] = true;
            for (int i = 0; i < N; i++)
            {
                if (map[now][i] == 1)
                {
                    if (!visited[i]) {
                        make_map[x][i] = 1;
                        queue.add(i);
                    }
                    else if (i == x)
                        make_map[x][x] = 1;
                }
            }
        }
    }
}
