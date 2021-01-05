package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 로봇_1726 {
    static int[][] map = new int[101][101];
    static boolean[][][] visited = new boolean[101][101][5];
    static int H, W;
    static int[] dy = {0, 0, 0, 1, -1};
    static int[] dx = {0, 1, -1, 0, 0};
    static Dot sDot, eDot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        for (int y = 1; y <= H; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= W; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        boolean isFirst = true;
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            if (isFirst) {
                sDot = new Dot(y, x, dir, 0);
                isFirst = false;
            }
            else
                eDot = new Dot(y, x, dir, 0);
        }
        int ans = BFS();
        System.out.println(ans);
    }

    private static int BFS() {
        Queue<Dot> que = new LinkedList<>();
        que.add(sDot);
        visited[sDot.y][sDot.x][sDot.dir] = true;
        int ans = 987654321;

        while (!que.isEmpty()) {
            Dot now = que.poll();

            if (now.y == eDot.y && now.x == eDot.x && now.dir == eDot.dir) {
                ans = Math.min(ans, now.cnt);
                continue;
            }

            for (int go = 1; go <= 3; go++) {
                int ny = now.y + dy[now.dir] * go;
                int nx = now.x + dx[now.dir] * go;
                if (ny >= 1 && ny <= H && nx >= 1 && nx <= W) {
                    if (!visited[ny][nx][now.dir]) {
                        if (map[ny][nx] == 1)
                            break;
                        que.add(new Dot(ny, nx, now.dir, now.cnt + 1));
                        visited[ny][nx][now.dir] = true;
                    }
                }
            }

            for (int d = 1; d <= 4; d++) {
                if (!visited[now.y][now.x][d] && d != now.dir) {
                    visited[now.y][now.x][d] = true;
                    if (d == reverse(now.dir))
                        que.add(new Dot(now.y, now.x, d, now.cnt + 2));
                    else
                        que.add(new Dot(now.y, now.x, d, now.cnt + 1));
                }
            }
        }
        return ans;
    }

    private static int reverse(int d) {
        if (d == 1)
            return 2;
        if (d == 2)
            return 1;
        if (d == 3)
            return 4;
        if (d == 4)
            return 3;
        else
            return 0;
    }

    private static class Dot
    {
        int y;
        int x;
        int dir;
        int cnt;

        public Dot(int y, int x, int dir, int cnt) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
}
