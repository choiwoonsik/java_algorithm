package pccp.배열;

import java.util.Arrays;

public class 삼각달팽이 {
    public static void main(String[] args) {
        int[] a = new Solution_삼각달팽이().solution(6);
        System.out.println(Arrays.toString(a));
    }
}

class Solution_삼각달팽이 {
    static int N;
    static int CNT = 1;
    static int[][] map;
    static int[] dy = {1, 0, -1};
    static int[] dx = {0, 1, -1};

    public int[] solution(int n) {
        N = n;
        map = new int[N][N];

        buildMap();
        return printMap();
    }

    private int[] printMap() {
        int len = (N * (N + 1)) / 2;
        int[] answer = new int[len];
        int idx = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) answer[idx++] = map[i][j];
            }
        }

        return answer;
    }

    private void buildMap() {
        int dir = 0;
        Dot now = new Dot(-1, 0);
        map = new int[N][N];
        Arrays.stream(map).forEach(it -> Arrays.fill(it, 0));

        int len = N;
        while (len > 0) {
            for (int i = 0; i < len; i++) {
                now.move(dy[dir], dx[dir]);
                map[now.y][now.x] = CNT++;
            }

            len--;
            dir++;
            dir %= 3;
        }
    }

    private static class Dot {
        int y, x;

        public Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public void move(int my, int mx) {
            this.y += my;
            this.x += mx;
        }
    }
}
