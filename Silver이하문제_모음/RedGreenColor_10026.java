package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RedGreenColor_10026 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] board;
    static int[] pos_x = {0, 0, -1, 1};
    static int[] pos_y = {-1, 1, 0, 0};
    static boolean[][] visited;
    static boolean R;
    static boolean G;
    static boolean RG;
    static boolean B;
    static int N;
    public static void main(String[] args) throws IOException {
        N = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
        board = new char[N][N];
        visited = new boolean[N][N];
        int count = 0;

        for (int i = 0; i < N; i++)
            board[i] = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((board[i][j] == 'R' || board[i][j] == 'G') && !visited[i][j]) {
                    count++;
                    RG = true;
                    DFS(new Dot(i, j));
                }
                else if (board[i][j] == 'B' && !visited[i][j]) {
                    count++;
                    B = true;
                    DFS(new Dot(i, j));
                }
                RG = false;
                B = false;
            }
        }
        int tmp = count;
        count = 0;
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'R' && !visited[i][j]) {
                    R = true;
                    count++;
                    G_DFS(new Dot(i, j));
                }
                else if (board[i][j] == 'G' && !visited[i][j]) {
                    G = true;
                    count++;
                    G_DFS(new Dot(i, j));
                }
                else if (board[i][j] == 'B' && !visited[i][j]) {
                    B = true;
                    count++;
                    G_DFS(new Dot(i, j));
                }
                R = false;
                G = false;
                B = false;
            }
        }
        System.out.println(count + " " + tmp);
    }
    private static void DFS(Dot dot)
    {
        visited[dot.x][dot.y] = true;
        for (int i = 0; i < 4; i++)
        {
            int x = dot.x + pos_x[i];
            int y = dot.y + pos_y[i];
            if (x >= 0 && y >= 0 && x < N && y < N) {
                if (!visited[x][y] && RG) {
                    if (board[x][y] == 'R' || board[x][y] == 'G') {
                        DFS(new Dot(x, y));
                    }
                }
                else if (!visited[x][y] && B) {
                    if (board[x][y] == 'B') {
                        DFS(new Dot(x, y));
                    }
                }
            }
        }
    }
    private static void G_DFS(Dot dot)
    {
        visited[dot.x][dot.y] = true;
        for (int i = 0; i < 4; i++)
        {
            int x = dot.x + pos_x[i];
            int y = dot.y + pos_y[i];
            if (x >= 0 && y >= 0 && x < N && y < N) {
                if (!visited[x][y] && board[x][y] == 'R' && R) {
                    G_DFS(new Dot(x, y));
                }
                else if (!visited[x][y] && board[x][y] == 'G' && G) {
                    G_DFS(new Dot(x, y));
                }
                else if (!visited[x][y] && board[x][y] == 'B' && B) {
                    G_DFS(new Dot(x, y));
                }
            }
        }
    }
}