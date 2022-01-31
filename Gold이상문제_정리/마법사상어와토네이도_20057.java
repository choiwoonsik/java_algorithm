package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
5
0 0 0 0 0
0 0 0 0 0
0 10 0 0 0
0 0 0 0 0
0 0 0 0 0
 */
public class 마법사상어와토네이도_20057 {
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};
    static int N;
    static int[][] sandBox;
    static int outSand;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    private static void solve() {
        // torado left, down, right, up
        Pair start = new Pair(N / 2, N / 2);
        int move = 1;
        int turnCnt = 2;
        int dir = 0;
        while (move < N) {
            if (move == N - 1) {
                turnCnt = 3;
            }
            for (int cnt = 0; cnt < turnCnt; cnt++) {
                for (int go = 0; go < move; go++) {
                    start.first += dy[dir];
                    start.second += dx[dir];
                    moveSand(start.first, start.second, dir);
                }
                ++dir;
                dir %= 4;
            }
            ++move;
        }
        System.out.println(outSand);
    }

    private static void moveSand(int y, int x, int dir) {
        int initSize = sandBox[y][x];
        int tenPercent = initSize * 10 / 100;
        int sevenPercent = initSize * 7 / 100;
        int fivePercent = initSize * 5 / 100;
        int twoPercent = initSize * 2 / 100;
        int onePercent = initSize / 100;
        int alpha = initSize - (tenPercent * 2 + sevenPercent * 2 + onePercent * 2 + twoPercent * 2 + fivePercent);
        sandBox[y][x] = 0;
        /*
        go left, right
         */
        if (dir == 0 || dir == 2) {
            int turn = 1;
            if (dir == 2) turn = -1;
            inBound(y, x - (turn * 2), fivePercent);
            inBound(y, x - turn, alpha);
            inBound(y - 1, x - turn, tenPercent);
            inBound(y - 1, x, sevenPercent);
            inBound(y - 1, x + turn, onePercent);
            inBound(y + 1, x - turn, tenPercent);
            inBound(y + 1, x, sevenPercent);
            inBound(y + 1, x + turn, onePercent);
            inBound(y - 2, x, twoPercent);
            inBound(y + 2, x, twoPercent);
        }
        /*
        go down, up
         */
        if (dir == 1 || dir == 3) {
            int turn = 1;
            if (dir == 1) turn = -1;
            inBound(y - turn * 2, x, fivePercent);
            inBound(y - turn, x, alpha);
            inBound(y - turn, x + 1, tenPercent);
            inBound(y, x + 1, sevenPercent);
            inBound(y + turn, x + 1, onePercent);
            inBound(y - turn, x - 1, tenPercent);
            inBound(y, x - 1, sevenPercent);
            inBound(y + turn, x - 1, onePercent);
            inBound(y, x - 2, twoPercent);
            inBound(y, x + 2, twoPercent);
        }
    }

    private static void inBound(int y, int x, int plusSand) {
        if (y >= 0 && y < N && x >= 0 && x < N) {
            sandBox[y][x] += plusSand;
        } else {
            outSand += plusSand;
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        sandBox = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                sandBox[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
