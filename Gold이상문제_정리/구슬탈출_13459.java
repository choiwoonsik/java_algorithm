package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 구슬탈출_13459 {
    static char[][] map;
    static boolean[][][][] visited; // 빨간공, 파란공의 방문 체크
    static int N, M;
    static INFO info;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        info = new INFO(0, 0, 0, 0, 0);
        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int y = 0; y < N; y++) {
            String line = br.readLine();
            for (int x = 0; x < M; x++) {
                map[y][x] = line.charAt(x);
                if (map[y][x] == 'B') {
                    info.by = y;
                    info.bx = x;
                }
                else if (map[y][x] == 'R') {
                    info.ry = y;
                    info.rx = x;
                }
            }
        }
        info.move = 0;

        boolean ret = bfs();
        if (ret)
            System.out.println(1);
        else
            System.out.println(0);
    }

    private static boolean bfs() {
        boolean success = false;

        Queue<INFO> q = new LinkedList<>();
        q.add(info);
        visited[info.ry][info.rx][info.by][info.bx] = true;

        while (!q.isEmpty())
        {
            INFO now = q.poll();
            if (now.move > 10)
                break;
            if (map[now.ry][now.rx] == 'O' && map[now.by][now.bx] != 'O') {
                success = true;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int n_ry = now.ry;
                int n_rx = now.rx;
                int n_by = now.by;
                int n_bx = now.bx;

                // 빨간공 직진
                while (true) {
                    if (map[n_ry][n_rx] != '#' && map[n_ry][n_rx] != 'O') {
                        n_ry += dy[d];
                        n_rx += dx[d];
                    }
                    else {
                        if (map[n_ry][n_rx] == '#') {
                            n_ry -= dy[d];
                            n_rx -= dx[d];
                        }
                        break;
                    }
                }
                // 파란공 직진
                while (true) {
                    if (map[n_by][n_bx] != '#' && map[n_by][n_bx] != 'O') {
                        n_by += dy[d];
                        n_bx += dx[d];
                    }
                    else {
                        if (map[n_by][n_bx] == '#') {
                            n_by -= dy[d];
                            n_bx -= dx[d];
                        }
                        break;
                    }
                }

                // 빨파 만난경우
                if (n_ry == n_by && n_rx == n_bx) {
                    if (map[n_ry][n_rx] != 'O') {
                        int red_dist = Math.abs(n_ry - now.ry) + Math.abs(n_rx - now.rx);
                        int blue_dist = Math.abs(n_by - now.by) + Math.abs(n_bx - now.bx);
                        // 빨공이 더 많이 움직이면
                        if (red_dist > blue_dist) {
                            n_ry -= dy[d];
                            n_rx -= dx[d];
                        }
                        else {
                            n_by -= dy[d];
                            n_bx -= dx[d];
                        }
                    }
                }
                if (!visited[n_ry][n_rx][n_by][n_bx]) {
                    visited[n_ry][n_rx][n_by][n_bx] = true;
                    INFO next = new INFO(n_ry, n_rx, n_by, n_bx, now.move + 1);
                    q.add(next);
                }
            }
        }

        return success;
    }

    private static class INFO
    {
        int ry;
        int rx;
        int by;
        int bx;
        int move;

        public INFO(int ry, int rx, int by, int bx, int move) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.move = move;
        }
    }
}
