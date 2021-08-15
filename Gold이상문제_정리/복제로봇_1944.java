package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 복제로봇_1944 {
    static final int KEY = 99;
    static int N, M;
    static boolean[][] visited = new boolean[51][51];
    static int[][] map = new int[51][51];
    static boolean[][] checkNode;
    static int[][] keyMap = new int[51][51];
    static int[] parents;
    static Dot[] keys = new Dot[252];
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.dist));
    private static class Node {
        int from;
        int to;
        int dist;

        public Node(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }
    private static class Dot {
        int y, x, dist;

        public Dot(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int keyNumber = 0;
        for (int y = 0; y < N; y++) {
            String s = br.readLine();
            for (int x = 0; x < N; x++) {
                char c = s.charAt(x);
                if (c == 'S' || c == 'K') {
                    keyMap[y][x] = keyNumber;
                    keys[keyNumber++] = new Dot(y, x, 0);
                    map[y][x] = KEY;
                }
                else map[y][x] = c - '0';
            }
        }
        parents = new int[keyNumber];
        checkNode = new boolean[keyNumber][keyNumber];
        for (int i = 0; i < keyNumber; i++)
            parents[i] = i;

        int answer = -1;
        if (findAllEdge())
            answer = MST();
        System.out.println(answer);
    }

    private static int MST() {
        int cost = 0;
        int cnt = 0;
        while (!pq.isEmpty() && cnt < M) {
            Node here = pq.poll();

            int fromFind = find(here.from);
            int toFind = find(here.to);

            if (fromFind != toFind) {
                cnt++;
                union(fromFind, toFind);
                cost += here.dist;
            }
        }
        return cost;
    }

    private static int find(int from) {
        if (parents[from] == from) return from;
        return parents[from] = find(parents[from]);
    }

    private static void union(int from, int to) {
        parents[parents[from]] = parents[to];
    }

    private static boolean findAllEdge() {
        for (int from = 0; from < M; from++) {
            if (!BFS(keys[from], keyMap[keys[from].y][keys[from].x])) return false;
        }
        return true;
    }

    private static boolean BFS(Dot fromKey, int from) {
        visited = new boolean[N][N];
        visited[fromKey.y][fromKey.x] = true;

        PriorityQueue<Dot> innerPq = new PriorityQueue<>(Comparator.comparingInt(d -> d.dist));
        innerPq.add(fromKey);

        boolean flag = false;
        while (!innerPq.isEmpty()) {

            Dot now = innerPq.poll();

            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                int to = keyMap[ny][nx];

                if (map[ny][nx] == 1) continue;
                if (visited[ny][nx]) continue;
                if (map[ny][nx] == KEY && !checkNode[from][to]) {
                    checkNode[from][to] = true;
                    checkNode[to][from] = true;
                    pq.add(new Node(from, to, now.dist + 1));
                    flag = true;
                }
                visited[ny][nx] = true;
                innerPq.add(new Dot(ny, nx, now.dist + 1));
            }
        }
        return flag;
    }
}
