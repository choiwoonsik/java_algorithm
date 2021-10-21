package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
6
011000
011011
000011
000011
110010
111000
 */
public class nhn01_2020 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        solve();
        System.out.println(answer);
    }

    private static void solve() {
        int count = 0;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    count++;
                    visited[i][j] = true;
                    list.add(BFS(new Dot(i, j)));
                }
            }
        }
        Collections.sort(list);
        answer.append(count).append("\n");
        for (int size : list) {
            answer.append(size).append(" ");
        }
    }

    private static int BFS(Dot dot) {
        int size = 0;
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        Queue<Dot> queue = new LinkedList<>();
        queue.add(dot);

        while (!queue.isEmpty()) {
            Dot now = queue.poll();
            size++;

            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                if (map[ny][nx] == 0) continue;
                if (visited[ny][nx]) continue;
                visited[ny][nx] = true;
                queue.add(new Dot(ny, nx));
            }
        }
        return size;
    }

    private static class Dot {
        int y, x;

        public Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
