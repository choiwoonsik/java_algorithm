package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 암벽등반_2412 {
    static int N, T;
    static Dot[] dots;
    static boolean[] visited;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        dots = new Dot[N + 1];
        visited = new boolean[N + 1];
        adj = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            dots[i] = new Dot( y, x, 0);
            adj[i] = new ArrayList<>();
        }
        adj[0] = new ArrayList<>();
        dots[0] = new Dot(0, 0, 0);

        Arrays.sort(dots, Comparator.comparing(d -> d.y));
        for (int i = 0; i < N + 1; i++) {
            dots[i].idx = i;
        }

        makeGraph();
        bfs(new Dot(0, 0, 0, 0));
    }

    private static void bfs(Dot dot) {
        PriorityQueue<Dot> pq = new PriorityQueue<>(Comparator.comparing(D -> D.move));
        pq.add(dot);
        visited[dot.idx] = true;

        while (!pq.isEmpty()) {
            Dot cur = pq.poll();

            if (cur.y == T) {
                System.out.println(cur.move);
                return;
            }

            for (Integer next : adj[cur.idx]) {
                if (!visited[next]) {
                    visited[next] = true;
                    dots[next].move = cur.move + 1;
                    pq.add(dots[next]);
                }
            }
        }
        System.out.println(-1);
    }

    private static void makeGraph() {
        for (int i = 0; i < N; i++) {
            int y = dots[i].y;
            int x = dots[i].x;

            int[] bound = findBound(y);
            int start = bound[0];
            int end = bound[1];

            for (int s = start; s < end; s++) {
                if (dots[s].x <= x + 2 && dots[s].x >= Math.max(x - 2, 0)) {
                    adj[i].add(s);
                }
            }
        }
    }

    private static int[] findBound(int y) {
        int start;
        int end;

        int left = 0;
        int right = N + 1;
        int mid;

        int minL = Math.max(y - 2, 0);
        // lower bound
        while (left < right) {
            mid = (left + right) / 2;

            if (dots[mid].y < minL)
                left = mid + 1;
            else
                right = mid;
        }
        start = right;

        // upper bound
        int maxR = y + 2;
        left = 0;
        right = N + 1;
        while (left < right) {
            mid = (left + right) / 2;

            if (dots[mid].y <= maxR)
                left = mid + 1;
            else
                right = mid;
        }
        end = right;

        return new int[]{start, end};
    }

    private static class Dot {
        int y, x, move, idx;

        public Dot(int y, int x, int move) {
            this.y = y;
            this.x = x;
            this.move = move;
        }

        public Dot(int y, int x, int move, int idx) {
            this.y = y;
            this.x = x;
            this.move = move;
            this.idx = idx;
        }
    }
}
