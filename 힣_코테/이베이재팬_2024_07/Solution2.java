package 힣_코테.이베이재팬_2024_07;

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution2 {
    int[] dy = {0, 1, 0, -1};
    int[] dx = {1, 0, -1, 0};
    boolean[][] visited;
    int[][] map;
    PriorityQueue<Dot> queue = new PriorityQueue<>(Comparator.comparing(d -> d.cost));
    int MIN = Integer.MAX_VALUE;
    int SIZE;
    int MID_Y;
    int MID_X;

    public int solution(int N, int[] board) {
        SIZE = N;
        map = new int[N][N];
        visited = new boolean[N][N];
        MID_Y = N / 2;
        MID_X = N / 2;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = board[i * N + j];
            }
        }

        bfs();

        return MIN;
    }

    private void bfs() {
        queue.add(new Dot(MID_Y, MID_X, map[MID_Y][MID_X]));

        while (!queue.isEmpty()) {
            Dot dot = queue.poll();
            int cy = dot.y;
            int cx = dot.x;
            int cost = dot.cost;

            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                // 탈출
                if (ny < 0 || nx < 0 || ny >= SIZE || nx >= SIZE) {
                    MIN = Math.min(MIN, cost);
                    continue;
                }

                if (visited[ny][nx]) continue;
                if (ny == nx) continue;
                int currentMax = Math.max(Math.abs(cy - MID_Y), Math.abs(cx - MID_X));
                int nextMax = Math.max(Math.abs(ny - MID_Y), Math.abs(nx - MID_X));
                if (currentMax > nextMax) continue;

                visited[ny][nx] = true;
                queue.add(new Dot(ny, nx, cost + map[ny][nx]));
            }
        }
    }

    private class Dot {
        int y;
        int x;
        int cost;

        public Dot(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }
}
