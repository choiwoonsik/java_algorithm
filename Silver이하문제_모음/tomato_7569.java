package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class tomato_7569
{
    static int[] pos_x = {1, -1, 0, 0, 0, 0};
    static int[] pos_y = {0, 0, 1, -1, 0, 0};
    static int[] pos_z = {0, 0, 0, 0, 1, -1};
    static int M;
    static int N;
    static int H;
    static int count;
    static int day;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Queue<Dot_3d> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = arr[0];
        N = arr[1];
        H = arr[2];
        int[][][] board = new int[H][N][M];
        boolean already = true;

        for (int h = 0; h < H; h++) {
            for (int y = 0; y < N; y++) {
                board[h][y] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int x = 0; x < M; x++){
                    if (board[h][y][x] == 0)
                        already = false;
                    else if (board[h][y][x] == 1) {
                        queue.add(new Dot_3d(h, y, x));
                        count++;
                    }
                }
            }
        }
        BFS(board);
        boolean isAll = true;
        for (int h = 0; h < H; h++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (board[h][y][x] == 0) {
                        isAll = false;
                    }
                }
            }
        }
        if (!isAll)
            System.out.println(-1);
        else if (already)
            System.out.println(0);
        else
            System.out.println(day);
    }
    private static void BFS(int[][][] board)
    {
        boolean isSpread = false;
        while (!queue.isEmpty())
        {
            int find = 0;
            for (int i = 0; i < count; i++)
            {
                Dot_3d dot = queue.poll();
                for (int j = 0; j < 6; j++)
                {
                    int x = dot.x + pos_x[j];
                    int y = dot.y + pos_y[j];
                    int z = dot.z + pos_z[j];
                    if (x < M && y < N && z < H && x >= 0 && y >= 0 && z >= 0) {
                        if (board[z][y][x] == 0) {
                            queue.add(new Dot_3d(z, y, x));
                            board[z][y][x] = 1;
                            find++;
                            isSpread = true;
                        }
                    }
                }
            }
            if (isSpread) {
                count = find;
                day++;
            }
            isSpread = false;
        }
    }
}

class Dot_3d
{
    int z;
    int y;
    int x;
    Dot_3d (int z, int y, int x)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

