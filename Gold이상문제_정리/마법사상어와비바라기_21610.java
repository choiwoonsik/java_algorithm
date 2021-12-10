package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법사상어와비바라기_21610 {
    static int N, M;
    static int[][] map;
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static boolean[][] disappeared;
    static Queue<Dot> cloud = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        initCloud();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int len = Integer.parseInt(st.nextToken());
            disappeared = new boolean[N][N];

            move(d, len);
            rain();
            waterMasic();
            makeCloud();
        }

        System.out.println(totalWater());
    }

    private static int totalWater() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] <= 0) continue;
                total += map[i][j];
            }
        }
        return total;
    }

    private static void makeCloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (disappeared[i][j]) continue;
                if (map[i][j] >= 2) {
                    map[i][j] -= 2;
                    cloud.add(new Dot(i, j));
                }
            }
        }
    }

    private static void waterMasic() {
        int[] t_dy = {-1, -1, 1, 1};
        int[] t_dx = {-1, 1, 1, -1};

        while (!cloud.isEmpty()) {
            Dot now = cloud.poll();

            int count = 0;
            for (int d = 0; d < 4; d++) {
                int ny = now.y + t_dy[d];
                int nx = now.x + t_dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                if (map[ny][nx] > 0) count++;
            }
            map[now.y][now.x] += count;
        }
    }

    private static void rain() {
        for (Dot now : cloud) {
            disappeared[now.y][now.x] = true;
            map[now.y][now.x] += 1;
        }
    }

    private static void move(int d, int len) {
        Queue<Dot> tmp = new LinkedList<>();
        for (Dot now : cloud) {
            int ny = now.y;
            int nx = now.x;
            for (int i = 0; i < len; i++) {
                ny += dy[d];
                nx += dx[d];

                if (ny < 0) ny = N - 1;
                if (nx < 0) nx = N - 1;
                if (ny >= N) ny = 0;
                if (nx >= N) nx = 0;
            }
            tmp.add(new Dot(ny, nx));
        }
        cloud.clear();
        cloud.addAll(tmp);
    }

    private static void initCloud() {
        for (int i = N - 2; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                cloud.add(new Dot(i, j));
            }
        }
    }

    private static class Dot {
        int y;
        int x;

        public Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
