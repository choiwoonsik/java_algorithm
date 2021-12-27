package 그래프탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 통나무옮기기_1938 {
    static int N;
    static boolean[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new boolean[N][N];
        int idx = 0;
        int jdx = 0;
        Tree myTree = new Tree(new Dot[3], 0, 0);
        Tree goal = new Tree(new Dot[3], 0, 0);

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = line.charAt(j);
                if (c == 'B') {
                    myTree.bodys[idx++] = new Dot(i, j);
                    board[i][j] = true;
                } else if (c == 'E') {
                    goal.bodys[jdx++] = new Dot(i, j);
                    board[i][j] = true;
                } else if (c == '0') {
                    board[i][j] = true;
                } else board[i][j] = false;
            }
        }
        if (myTree.bodys[0].y == myTree.bodys[2].y) myTree.dir = 0;
        else myTree.dir = 1;

        if (goal.bodys[0].y == goal.bodys[2].y) goal.dir = 0;
        else goal.dir = 1;

        int search = search(myTree, goal);
        System.out.println(search);
    }

    private static int search(Tree go, Tree end) {
        PriorityQueue<Tree> pq = new PriorityQueue<>(Comparator.comparing(T -> T.move));
        boolean[][][] isVisited = new boolean[N][N][2];
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        pq.add(go);
        isVisited[go.bodys[1].y][go.bodys[1].x][go.dir] = true;

        while (!pq.isEmpty()) {
            Tree cur = pq.poll();

            if (cur.dir == end.dir && cur.bodys[1].y == end.bodys[1].y && cur.bodys[1].x == end.bodys[1].x) {
                return cur.move;
            }

            if (canTurn(cur.bodys[1]) && !isVisited[cur.bodys[1].y][cur.bodys[1].x][(cur.dir + 1) % 2]) {
                isVisited[cur.bodys[1].y][cur.bodys[1].x][(cur.dir + 1) % 2] = true;
                pq.add(new Tree(cur.bodys, (cur.dir + 1) % 2, cur.move + 1));
            }
            for (int d = 0; d < 4; d++) {
                int ny = cur.bodys[1].y + dy[d];
                int nx = cur.bodys[1].x + dx[d];

                if (cur.dir == 0) { // 가로
                    if (ny < 0 || nx < 1 || ny >= N || nx >= N - 1) continue;
                    if (isVisited[ny][nx][cur.dir]) continue;
                    if (!board[ny][nx] || !board[ny][nx - 1] || !board[ny][nx + 1]) continue;

                    isVisited[ny][nx][cur.dir] = true;
                    pq.add(
                            new Tree(new Dot[]{new Dot(ny, nx - 1), new Dot(ny, nx), new Dot(ny, nx + 1)},
                                    cur.dir,
                                    cur.move + 1)
                    );
                }
                else { // 세로
                    if (ny < 1 || nx < 0 || ny >= N - 1 || nx >= N) continue;
                    if (isVisited[ny][nx][cur.dir]) continue;
                    if (!board[ny][nx] || !board[ny - 1][nx] || !board[ny + 1][nx]) continue;

                    isVisited[ny][nx][cur.dir] = true;
                    pq.add(
                            new Tree(new Dot[]{new Dot(ny-1, nx), new Dot(ny, nx), new Dot(ny + 1, nx)},
                                    cur.dir,
                                    cur.move + 1)
                    );
                }
            }
        }
        return 0;
    }

    private static boolean canTurn(Dot now) {
        int y = now.y;
        int x = now.x;
        if (y < 1 || x < 1 || y >= N - 1 || x >= N - 1) return false;
        if (!board[y-1][x-1] || !board[y-1][x] || !board[y-1][x+1]) return false;
        if (!board[y][x -1] || !board[y][x + 1]) return false;
        if (!board[y + 1][x -1] || !board[y + 1][x] || !board[y+1][x+1]) return false;
        return true;
    }

    private static class Tree {
        int move;
        int dir;
        Dot[] bodys;

        public Tree(Dot[] bodys, int dir, int move) {
            this.bodys = bodys;
            this.move = move;
            this.dir = dir;
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
