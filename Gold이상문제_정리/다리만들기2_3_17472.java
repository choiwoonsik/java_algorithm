package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 다리만들기2_3_17472 {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] parents;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static PriorityQueue<Bridge> pq = new PriorityQueue<>(Comparator.comparingInt(b -> b.len));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        findAllIsland();
        makeAllBridge();
        int answer = matchingBrdige();

        int other = find(parents[1]);
        for (int i = 2; i < parents.length; i++) {
            if (other != find(parents[i])) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(answer);
    }

    private static int matchingBrdige() {
        int totalBridgeLen = 0;
        while (!pq.isEmpty()) {
            Bridge bridge = pq.poll();

            int landA = find(bridge.landA);
            int landB = find(bridge.landB);

            if (landA != landB) {
                union(landA, landB);
                totalBridgeLen += bridge.len;
            }
        }
        return totalBridgeLen;
    }

    private static void union(int landA, int landB) {
        parents[landA] = landB;
    }

    private static int find(int land) {
        if (parents[land] == land) return land;
        return parents[land] = find(parents[land]);
    }

    private static void makeAllBridge() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] > 0) {
                    setBridge(i, j);
                }
            }
        }
    }

    private static void setBridge(int y, int x) {
        int nowLand = board[y][x];

        for (int d = 0; d < 4; d++) {
            boolean flag = false;
            int ny = y, nx = x;
            int len = 0;

            while (true) {
                ny += dy[d];
                nx += dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M) break;
                if (board[ny][nx] == nowLand) break;
                if (board[ny][nx] > 0 && len < 2) break;
                if (board[ny][nx] > 0 && len >= 2) {
                    flag = true;
                    break;
                }
                len++;
            }
            if (flag) pq.add(new Bridge(nowLand, board[ny][nx], len));
        }
    }

    private static void findAllIsland() {
        visited = new boolean[N][M];
        int indexing = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && board[i][j] != 0) {
                    DFS(i, j, indexing++);
                }
            }
        }

        parents = new int[indexing];
        for (int i = 1; i < indexing; i++)
            parents[i] = i;
    }

    private static void DFS(int y, int x, int indexing) {
        if (y < 0 || x < 0 || y >= N || x >= M || board[y][x] == 0 || visited[y][x])
            return;

        board[y][x] = indexing;
        visited[y][x] = true;

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            DFS(ny, nx, indexing);
        }
    }

    private static class Bridge {
        int landA;
        int landB;
        int len;

        public Bridge(int landA, int landB, int len) {
            this.landA = landA;
            this.landB = landB;
            this.len = len;
        }
    }
}
