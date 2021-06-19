package Silver이하문제_모음;

import java.io.*;
import java.util.*;

public class wizardShark_and_fireball2_20056 {
    static Queue<Fireball>[][] map;
    static Queue<Fireball>[][] subMap;
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N, M, K;
    static ArrayList<Fireball> fireList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new LinkedList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map[y][x].add(new Fireball(m, s, d));
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
        int sum = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x].size() > 0) {
                    sum += map[y][x].stream().mapToInt(s -> s.m).sum();
                }
            }
        }
        return sum;
    }

    private static void merge_divide() {
        // 2개 이상 불이 같이있는 좌표
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x].size() > 1) {
                    int count = map[y][x].size();
                    int sumM = 0;
                    int sumS = 0;
                    boolean isOdd = true; //홀수
                    boolean isEven = true;
                    for (Fireball fire : map[y][x]) {
                        sumM += fire.m;
                        sumS += fire.s;
                        if (fire.d % 2 == 0)
                            isOdd = false;
                        else
                            isEven = false;
                    }
                    map[y][x].clear();
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
                        map[y][x].add(new Fireball(eachFireM, eachFireS, dir[i]));
                }
            }
        }
    }

    private static void move() {
        subMap = new LinkedList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                subMap[i][j] = new LinkedList<>();
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x].size() > 0) {
                    for (Fireball fire : map[y][x]) {
                        int cd = fire.d;
                        int ny = (y + dy[cd] * fire.s) % N;
                        int nx = (x + dx[cd] * fire.s) % N;
                        if (ny < 0) ny += N;
                        if (nx < 0) nx += N;
                        subMap[ny][nx].add(new Fireball(fire.m, fire.s, fire.d));
                    }
                }
            }
        }
        map = subMap;
    }

    private static class Fireball {
        int m;
        int s;
        int d;

        public Fireball(int m, int s, int d) {
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
