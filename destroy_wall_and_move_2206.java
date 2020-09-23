import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class destroy_wall_and_move_2206 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, anw;
    static int[] d_x = {-1, 1, 0, 0};
    static int[] d_y = {0, 0, 1, -1};
    static int[][] board;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M][2];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j) - '0';
            }
        }
        anw = -1;
        BFS();
        System.out.println(anw);
    }
    private static void BFS()
    {
        Queue<Dot_wall> queue = new LinkedList<>();
        queue.add(new Dot_wall(0, 0, 1, 0));

        while (!queue.isEmpty()) {
            Dot_wall now_dot = queue.poll();
            visited[now_dot.x][now_dot.y][now_dot.isUsed] = true;
            if (now_dot.x == N - 1 && now_dot.y == M - 1) {
                anw = now_dot.count;
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = now_dot.x + d_x[i];
                int ny = now_dot.y + d_y[i];
                int count = now_dot.count + 1;
                int isUsed = now_dot.isUsed;

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    //벽이 없는경우 && 방문한적이 없다면 // 부순 여부랑 관계없이 진행
                    if (board[nx][ny] == 0 && !visited[nx][ny][isUsed]) {
                        visited[nx][ny][isUsed] = true;
                        queue.add(new Dot_wall(nx, ny, count, isUsed));
                    }
                    //벽이있는데 && 아직 방문하지 않았고 && 아직 안부순경우
                    else if (board[nx][ny] == 1 && !visited[nx][ny][isUsed] && isUsed == 0) {
                        queue.add(new Dot_wall(nx, ny, count, 1));
                    }
                }
            }
        }
    }
}

class Dot_wall
{
    int x;
    int y;
    int count;
    int isUsed;
    Dot_wall(int x, int y, int count, int isWall)
    {
        this.x = x; this.y = y; this.count = count; this.isUsed = isWall;
    }
}