package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 달이차오른다_가자_1194 {
    static char[][] board;
    static boolean[][][] visited;
    static int N, M;
    static int maxKey;
    static Dot start;
    private static class Dot {
        int y, x, d, k;

        public Dot(int y, int x, int d, int k) {
            this.y = y;
            this.x = x;
            this.d = d;
            this.k = k;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                if (c == '0') start = new Dot(i, j, 0, 0);
                if (c >= 'a' && c <= 'f') maxKey = Math.max(maxKey, c - 'a');
                board[i][j] = c;
            }
        }
        visited = new boolean[N][M][1 << (maxKey + 1)];
        int answer = go();
        System.out.println(answer);
    }

    private static int go() {
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        PriorityQueue<Dot> pq = new PriorityQueue<>(Comparator.comparingInt(d -> d.d));
        start.k = 0;
        pq.add(start);
        visited[start.y][start.x][start.k] = true;

        while (!pq.isEmpty()) {
            Dot now = pq.poll();

            int cy = now.y;
            int cx = now.x;
            int ck = now.k;
            int cd = now.d;

            if (board[cy][cx] >= 'A' && board[cy][cx] <= 'F') {
                if ((ck | (1 << (board[cy][cx] - 'A'))) != ck) continue;
            }
            else if (board[cy][cx] >= 'a' && board[cy][cx] <= 'f') {
                ck |= 1 << board[cy][cx] - 'a';
            }
            else if (board[cy][cx] == '1') {
                return cd;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cy + dy[d];
                int nx = cx + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
                if (board[ny][nx] == '#') continue;
                if (visited[ny][nx][ck]) continue;

                visited[ny][nx][ck] = true;
                pq.add(new Dot(ny, nx, cd + 1, ck));
            }
        }
        return -1;
    }
}
