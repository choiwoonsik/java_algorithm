package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class count_island_4963 {
    static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    static int[] dy = {-1, 1, 0, 0, -1, 1, 1, -1};
    static int h, w, count;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true)
        {
            count = 0;
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (h == 0 && w == 0)
                break;
            int[][] board = new int[h][w];
            visited = new boolean[h][w];
            for (int i = 0; i < h; i++)
                board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (board[i][j] == 1 && !visited[i][j]) {
                        count++;
                        BFS(new Dot(i, j), board);
                    }
                }
            }
            System.out.println(count);
        }
    }
    private static void BFS(Dot dot, int[][] map)
    {
        Queue<Dot> queue = new LinkedList<>();
        queue.add(dot);
        while(!queue.isEmpty())
        {
            Dot now = queue.poll();
            visited[now.x][now.y] = true;
            for (int i = 0; i < 8; i++)
            {
                int dh = now.x + dx[i];
                int dw = now.y + dy[i];
                if (dh >= 0 && dw >= 0 && dh < h && dw < w) {
                    if (!visited[dh][dw] && map[dh][dw] == 1) {
                        queue.add(new Dot(dh, dw));
                        visited[dh][dw] = true;
                    }
                }
            }
        }
    }
}
