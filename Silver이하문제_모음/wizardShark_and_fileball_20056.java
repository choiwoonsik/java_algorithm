package Silver이하문제_모음;

import java.util.*;
import java.io.*;

public class wizardShark_and_fileball_20056 {
    static int[][] map;
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N, M, K;
    static ArrayList<Fireball> fireList = new ArrayList<>();
    static ArrayList<Pos> posList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireList.add(new Fireball(y, x, m, s, d));
        }

        solve();
    }

    private static void solve() {
        for (int move = 0; move < K; move++) {
            //move
            move();
            // merge
            merge_divide();
        }
        int ret = all_fire_m();
        System.out.println(ret);
    }

    private static int all_fire_m() {
        return fireList.stream().mapToInt(fire -> fire.m).sum();
    }

    private static void merge_divide() {
        // 2개 이상 불이 같이있는 좌표
        for (Pos pos : posList) {
            ArrayList<Fireball> removeFire = new ArrayList<>();
            int count = map[pos.y][pos.x];
            int sumM = 0;
            int sumS = 0;
            boolean isOdd = true; //홀수
            boolean isEven = true;
            for (Fireball fire : fireList) {
                if (pos.y == fire.y && pos.x == fire.x) {
                    sumM += fire.m;
                    sumS += fire.s;
                    if (fire.d % 2 == 0)
                        isOdd = false;
                    else
                        isEven = false;
                    removeFire.add(fire);
                }
            }
            for (Fireball rmFire : removeFire)
                fireList.remove(rmFire);
            if (sumM <= 4)
                continue;
            int eachFireM = sumM / 5;
            int eachFireS = sumS / count;
            int[] dir;
            if (isOdd || isEven)
                dir = new int[]{0, 2, 4, 6};
            else
                dir = new int[]{1, 3, 5, 7};
            for (int i = 0; i < 4; i++)
                fireList.add(new Fireball(pos.y, pos.x, eachFireM, eachFireS, dir[i]));
        }
    }

    private static void move() {
        map = new int[N][N];
        posList.clear();
        for (Fireball fire : fireList) {
            int cy = fire.y;
            int cx = fire.x;
            int cd = fire.d;
            int ny = (cy + dy[cd] * fire.s) % N;
            int nx = (cx + dx[cd] * fire.s) % N;
            if (ny < 0) ny += N;
            if (nx < 0) nx += N;
            fire.y = ny;
            fire.x = nx;
            map[ny][nx]++;
        }
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] >= 2)
                    posList.add(new Pos(y, x));
            }
        }
    }

    private static class Fireball {
        int y;
        int x;
        int m;
        int s;
        int d;

        public Fireball(int y, int x, int m, int s, int d) {
            this.y = y;
            this.x = x;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    private static class Pos {
        int y;
        int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
