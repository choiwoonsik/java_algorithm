package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 파이프옮기기1_17070 {
    static final int right = 0, rightDown = 1, down = 2;
    static int[] dy = {0, 1, 1}; // 오른쪽, 대각선, 아래
    static int[] dx = {1, 1, 0};
    static int N, answer;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        Dot init = new Dot(0, 1, right);
        if (board[N-1][N-1] == 1) System.out.println(0);
        else {
            BFS(init);
            System.out.println(answer);
        }
    }

    private static void BFS(Dot init) {
        Queue<Dot> q = new LinkedList<>();
        q.add(init);

        while (!q.isEmpty())
        {
            Dot now = q.poll();

            if (isArrive(now)) continue;

            int[] dir = setDir(now);
            int minD = dir[0];
            int maxD = dir[1];

            for (int d = minD; d <= maxD; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                if (d == rightDown) {
                    if (board[ny][nx] == 1 || board[ny - 1][nx] == 1 || board[ny][nx - 1] == 1) continue;
                } else {
                    if (board[ny][nx] == 1) continue;
                }
                q.add(new Dot(ny, nx, d));
            }
        }
    }

    private static boolean isArrive(Dot now) {
        if (now.y == N - 1 && now.x == N - 1) {
            answer++;
            return true;
        }
        return false;
    }

    private static int[] setDir(Dot now) {
        int minD;
        int maxD;
        if (now.dir == right) {
            minD = right;
            maxD = rightDown;
        }
        else if (now.dir == rightDown) {
            minD = right;
            maxD = down;
        }
        else {
            minD = rightDown;
            maxD = down;
        }
        return new int[]{minD, maxD};
    }

    private static class Dot
    {
        int y, x, dir;

        public Dot(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }
}
