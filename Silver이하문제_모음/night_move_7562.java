package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class night_move_7562 {
    static int[] dx = {-2, -2, 2, 2, -1, 1, -1, 1};
    static int[] dy = {-1, 1, -1, 1, -2, -2, 2, 2};
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Queue<Dot_count> queue;
    public static void main(String[] args) throws IOException
    {
        int K = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
        for (int i = 0; i < K; i++)
        {
            N = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
            map = new int[N][N];
            visited = new boolean[N][N];
            queue = new LinkedList<>();
            int tmp[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            StringTokenizer st = new StringTokenizer(br.readLine());
            Dot target = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            BFS(new Dot_count(tmp[0], tmp[1], 0), target);
        }
    }
    private static void BFS(Dot_count dot, Dot target)
    {
        queue.add(dot);
        while (!queue.isEmpty())
        {
            Dot_count now = queue.poll();
            visited[now.x][now.y] = true;
            if (now.x == target.x && now.y == target.y)
            {
                System.out.println(now.count);
                return;
            }
            for (int i = 0; i < 8; i++)
            {
                int x = dx[i] + now.x;
                int y = dy[i] + now.y;
                if (x >= 0 && y >= 0 && x < N && y < N) {
                    if (!visited[x][y]) {
                        visited[x][y] = true;
                        queue.add(new Dot_count(x, y, now.count + 1));
                    }
                }
            }
        }
    }
}
