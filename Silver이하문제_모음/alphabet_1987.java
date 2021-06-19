package Silver이하문제_모음;

import java.io.*;
import java.util.*;

public class alphabet_1987 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int H;
    static int W;
    static char[][] board;
    static boolean[] visited;
    static int[] pos_x = {-1, 1, 0, 0};
    static int[] pos_y = {0, 0, -1, 1};
    static int MAX;
    public static void main(String[] args) throws IOException
    {
        int n[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        H = n[0];
        W = n[1];
        board = new char[H][W];
        visited = new boolean[26];
        for (int i = 0 ; i < H; i++)
            board[i] = br.readLine().toCharArray();
        visited[board[0][0] - 65] = true;
        backTracking(0, 0, 1);
        System.out.print(MAX);
    }
    private static void backTracking(int x, int y, int count)
    {
        for (int i = 0; i < 4; i++)
        {
            int n_x = x + pos_x[i];
            int n_y = y + pos_y[i];
            if (n_x >= 0 && n_y >= 0 && n_x < W && n_y < H) {
                if (!visited[board[n_y][n_x] - 65])
                {
                    visited[board[n_y][n_x] - 65] = true;
                    backTracking(n_x, n_y, count + 1);
                    visited[board[n_y][n_x] - 65] = false;
                }
            }
        }
        if (MAX < count)
            MAX = count;
    }
}