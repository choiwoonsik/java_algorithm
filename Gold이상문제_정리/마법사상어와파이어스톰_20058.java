package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
3 1
1 2 3 4 5 6 7 8
8 7 6 5 4 3 2 1
1 2 3 4 5 6 7 8
8 7 6 5 4 3 2 1
1 2 3 4 5 6 7 8
8 7 6 5 4 3 2 1
1 2 3 4 5 6 7 8
8 7 6 5 4 3 2 1
1

얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1 줄어든다
 */
public class 마법사상어와파이어스톰_20058 {
    static int N, Q, L;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] visited;
    static int[][] board;
    static int[][] tmpBoard;
    static ArrayList<Integer> qList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        intput();
        solve();
    }

    private static void solve() {
        for (Integer size : qList) {
            for (int i = 0; i < L; i += size) {
                for (int j = 0; j < L; j += size) {
                    tmpBoard = new int[size][size];
                    turn90(i, j, size);
                }
            }
            melting();
        }
        countAll();
        getLargestLand();
    }

    private static void getLargestLand() {
        int MAX = 0;
        visited = new boolean[L][L];

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (visited[i][j] || board[i][j] == 0) continue;
                MAX = Math.max(MAX, BFS(i, j));
            }
        }
        System.out.println(MAX);
    }

    private static int BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int y = now[0];
            int x = now[1];

            if (board[y][x] >= 1) count++;

            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + y;
                int nx = dx[d] + x;

                if (ny < 0 || nx < 0 || ny >= L || nx >= L) continue;
                if (board[ny][nx] == 0) continue;
                if (visited[ny][nx]) continue;
                visited[ny][nx] = true;
                queue.add(new int[]{ny, nx});
            }
        }

        return count;
    }

    private static void countAll() {
        int sum = 0;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                sum += board[i][j];
            }
        }
        System.out.println(sum);
    }

    private static void melting() {
        ArrayList<int[]> meltingDot = new ArrayList<>();

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                int count = 0;
                if (board[i][j] > 0) {
                    int ny, nx;
                    for (int d = 0; d < 4; d++) {
                        ny = i + dy[d];
                        nx = j + dx[d];
                        if (ny < 0 || ny >= L || nx < 0 || nx >= L) continue;
                        if (board[ny][nx] == 0) continue;
                        count++;
                    }
                    if (count < 3) meltingDot.add(new int[]{i, j});
                }
            }
        }
        for (int[] dot : meltingDot) {
            board[dot[0]][dot[1]]--;
        }
    }

    private static void turn90(int y, int x, Integer size) {
        int idx = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tmpBoard[j][size - idx] = board[y + i][x + j];
            }
            idx++;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i + y][j + x] = tmpBoard[i][j];
            }
        }
    }

    private static void intput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        L = (int) Math.pow(2, N);
        board = new int[L][L];

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < L; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(st.nextToken());
            qList.add((int) Math.pow(2, q));
        }
    }
}
