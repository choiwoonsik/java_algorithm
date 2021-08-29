package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 미네랄2_18500 {
    static int N, M, T;
    static char[][] board;
    static boolean[][] mark;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[] turn;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N+1][M+1];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        T = Integer.parseInt(br.readLine());
        turn = new int[T];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < T; i++) {
            turn[i] = Integer.parseInt(st.nextToken());
        }

        shot();
        printMineral();
    }

    private static void shot() {
        int t = 0;
        for (int y : turn) {
            destroy(N - y, t++ % 2 == 0);
            checkIsFall();
        }
    }

    private static void printMineral() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer.append(board[i][j]);
            }
            answer.append("\n");
        }
        System.out.print(answer);
    }

    private static void checkIsFall() {
        mark = new boolean[N][M];
        makringFirst();
        moveNotMarked();
    }

    private static void moveNotMarked() {
        ArrayList<int[]> lists = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (board[y][x] == 'x' && !mark[y][x]) {
                    board[y][x] = '.';
                    lists.add(new int[]{y, x});
                }
            }
        }

        int height = 101;
        for (int[] dot : lists) {
            int y = dot[0];
            int x = dot[1];

            while (y < N) {
                if (!mark[y][x] && board[y][x] == 'x') break;
                if (mark[y][x] && board[y][x] == 'x') {
                    height = Math.min(height, y - dot[0]);
                    break;
                }
                if (y == N - 1) {
                    height = Math.min(height, N - dot[0]);
                    break;
                }
                y++;
            }
        }

        for (int[] dot : lists) {
            int y = dot[0];
            int x = dot[1];
            board[y + height - 1][x] = 'x';
        }
    }

    private static void makringFirst() {
        for (int x = 0; x < M; x++) {
            if (!mark[N - 1][x] && board[N - 1][x] == 'x') {
                dfs(N - 1, x);
            }
        }
    }

    private static void dfs(int y, int x) {
        if (mark[y][x]) return;

        mark[y][x] = true;

        for (int d = 0; d < 4; d++) {
            int ny = dy[d] + y;
            int nx = dx[d] + x;

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
            if (board[ny][nx] == '.') continue;
            if (mark[ny][nx]) continue;
            dfs(ny, nx);
        }
    }

    private static void destroy(int y, boolean left) {
        if (left) {
            for (int x = 0; x < M; x++) {
                if (board[y][x] == 'x') {
                    board[y][x] = '.';
                    break;
                }
            }
        } else {
            for (int x = M - 1; x >= 0; x--) {
                if (board[y][x] == 'x') {
                    board[y][x] = '.';
                    break;
                }
            }
        }
    }
}
