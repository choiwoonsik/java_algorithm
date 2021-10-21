package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
6
0 0 0 0 0 0
0 1 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
 */
public class 파이프옮기기1_2_17070 {
    static final int right = 0, rightDown = 1, down = 2;
    static int[] dy = {0, 1, 1}; // 오른쪽, 대각선, 아래
    static int[] dx = {1, 1, 0};
    static int N;
    static int[][] board;
    static int Count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
        System.out.println(Count);
    }

    private static void solve() {
        if (board[N-1][N-1] != 1) {
            Dot start = new Dot(0, 1, right);
            bfs(start);
        } else Count = 0;
    }

    private static void bfs(Dot start) {
        Queue<Dot> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Dot now = queue.poll();

            if (now.y == N - 1 && now.x == N - 1) {
                Count++;
                continue;
            }

            int sD = -1;
            int eD = -1;
            if (now.dir == right) {
                sD = right;
                eD = rightDown;
            } else if (now.dir == rightDown) {
                sD = right;
                eD = down;
            } else if (now.dir == down) {
                sD = rightDown;
                eD = down;
            }

            for (int d = sD; d <= eD; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                if (outBound(ny, nx)) continue;
                if (d == rightDown) {
                    if (isBlock(ny, nx)) continue;
                } else {
                    if (board[ny][nx] == 1) continue;
                }
                queue.add(new Dot(ny, nx, d));
            }
        }
    }

    private static boolean outBound(int ny, int nx) {
        return ny < 0 || nx < 0 || ny >= N || nx >= N;
    }

    private static boolean isBlock(int y, int x) {
        return board[y][x - 1] == 1 || board[y][x] == 1 || board[y - 1][x] == 1;
    }

    private static class Dot {
        int y, x, dir;

        public Dot(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }
}
