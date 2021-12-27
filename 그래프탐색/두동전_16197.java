package 그래프탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 두동전_16197 {
    static int N, M;
    static char[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        Pair coin = new Pair(0, new Dot(0, 0), new Dot(0, 0));
        boolean first = true;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                map[i][j] = c;
                if (c == 'o') {
                    if (first) {
                        coin.one = new Dot(i, j);
                        first = false;
                    } else coin.two = new Dot(i, j);
                }
            }
        }

        int answer = bfs(coin);
        System.out.println(answer);
    }

    private static int bfs(Pair coin) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparing(C -> C.move));
        int[] dy = {-1, 1, 0, 0}; // up, down, left, right
        int[] dx = {0, 0, -1, 1};
        visited = new boolean[N][M][2];
        Dot dot1 = coin.one;
        Dot dot2 = coin.two;
        visited[dot1.y][dot1.x][0] = true;
        visited[dot2.y][dot2.x][1] = true;
        pq.add(coin);

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            if (cur.move >= 10) break;

            Dot one = cur.one;
            Dot two = cur.two;

            for (int d = 0; d < 4; d++) {
                int oneNy = one.y + dy[d];
                int oneNx = one.x + dx[d];
                int twoNy = two.y + dy[d];
                int twoNx = two.x + dx[d];

                if (isOut(oneNy, oneNx) && isOut(twoNy, twoNx)) continue;
                else if (isOut(oneNy, oneNx) || isOut(twoNy, twoNx)) {
                    return cur.move + 1;
                }
                Dot nextOne = one;
                Dot nextTwo = two;
                if (move(oneNy, oneNx)) nextOne = new Dot(oneNy, oneNx);
                if (move(twoNy, twoNx)) nextTwo = new Dot(twoNy, twoNx);

                pq.add(new Pair(cur.move + 1, nextOne, nextTwo));
            }
        }
        return -1;
    }

    private static boolean isOut(int y, int x) {
        return (y < 0 || x < 0 || y >= N || x >= M);
    }

    private static boolean move(int y, int x) {
        return map[y][x] != '#';
    }

    private static class Pair {
        int move;
        Dot one, two;

        public Pair(int move, Dot one, Dot two) {
            this.move = move;
            this.one = one;
            this.two = two;
        }
    }

    private static class Dot {
        int y, x;

        public Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
