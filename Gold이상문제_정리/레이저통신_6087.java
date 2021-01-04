package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 레이저통신_6087 {
    static char[][] map = new char[101][101];
    static int[][] mirrorCnt = new int[101][101];
    static int W, H;
    static Dot sDot, eDot;
    static int[] dy = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        boolean isFirst = true;

        for (int y = 0; y < H; y++)
        {
            String str = br.readLine();
            for (int x = 0; x < W; x++)
            {
                map[y][x] = str.charAt(x);
                if (map[y][x] == 'C' && isFirst) {
                    isFirst = false;
                    sDot = new Dot(y, x, 0, 0);
                }
                else if (map[y][x] == 'C' && !isFirst)
                    eDot = new Dot(y, x, 0, 0);
            }
        }

        BFS();
        System.out.println(mirrorCnt[eDot.y][eDot.x]);
    }

    private static void BFS() {

        for (int i = 0; i < 101; i++)
            Arrays.fill(mirrorCnt[i], 10001);

        Queue<Dot> que = new LinkedList<>();
        mirrorCnt[sDot.y][sDot.x] = 0;

        //처음 시작점에서 4방향으로 먼저 출발 (추가)
        for (int d = 0; d < 4; d++)
        {
            int ny = sDot.y + dy[d];
            int nx = sDot.x + dx[d];
            if (ny >= 0 && ny < H && nx >= 0 && nx < W && map[ny][nx] != '*') {
                mirrorCnt[ny][nx] = 0;
                que.add(new Dot(ny, nx, d, 0));
            }
        }
        while (!que.isEmpty())
        {
            Dot now = que.poll();
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                if (ny >= 0 && ny < H && nx >= 0 && nx < W && map[ny][nx] != '*') {
                    int nm = (now.dir == d) ? now.cnt : now.cnt + 1;
                    if (mirrorCnt[ny][nx] >= nm) {
                        mirrorCnt[ny][nx] = nm;
                        que.add(new Dot(ny, nx, d, nm));
                    }
                }
            }
        }
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
