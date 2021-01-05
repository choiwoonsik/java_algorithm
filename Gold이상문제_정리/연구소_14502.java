package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 연구소_14502 {
    static int[][] map;
    static int[][] subMap;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int N,M;
    static int maxSize = -100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        subMap = new int[N][M];
        visited = new boolean[N][M];

        for (int y = 0; y < N; y++)
        {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++)
            {
                map[y][x] = Integer.parseInt(st.nextToken());
                subMap[y][x] = map[y][x];
            }
        }
        make_wall(0, 0,0);
        System.out.println(maxSize);
    }

    private static void make_wall(int y, int x, int count) {

        if (count == 3) {
            for (int i = 0; i < N; i++)
                subMap[i] = map[i].clone();
            fill_birus();
            maxSize = Math.max(count_safe(), maxSize);
            return;
        }

        for (int i = y; i < N; i++) {
            for (int j = x; j < M; j++) {
                if (map[i][j] == 0)
                {
                    map[i][j] = 1;
                    make_wall(y, x + 1, count + 1);
                    map[i][j] = 0;
                }
                x = 0;
            }
        }
    }

    private static int count_safe() {
        int cnt = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (subMap[y][x] == 0)
                    cnt++;
            }
        }
        return cnt;
    }

    private static void fill_birus() {
        visited = new boolean[N][M];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (subMap[y][x] == 2 && !visited[y][x]) {
                    DFS(y, x);
                }
            }
        }
    }

    private static void DFS(int y, int x) {
        visited[y][x] = true;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                if (subMap[ny][nx] == 0 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    subMap[ny][nx] = 2;
                    DFS(ny, nx);
                }
            }
        }
    }
}
