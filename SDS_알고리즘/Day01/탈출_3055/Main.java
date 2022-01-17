package SDS_알고리즘.Day01.탈출_3055;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static Queue<Dot> waterQ = new LinkedList<>();
    static Queue<Dot> dochiQ = new LinkedList<>();
    static boolean[][] visited;
    static String answer = "KAKTUS";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'S') {
                    dochiQ.add(new Dot(i, j, 0));
                    visited[i][j] = true;
                }
                else if (map[i][j] == '*') waterQ.add(new Dot(i, j));
            }
        }

        start();
    }

    private static void start() {
        while(!dochiQ.isEmpty()) {
            waterFlood();
            if (escape()) break;
        }
        System.out.println(answer);
    }

    private static boolean escape() {
        int t = dochiQ.size();
        while (t-- > 0 && !dochiQ.isEmpty()) {
            Dot cur = dochiQ.poll();

            if (map[cur.y][cur.x] == 'D') {
                answer = cur.t + "";
                return true;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
                if (visited[ny][nx]) continue;
                if (map[ny][nx] == 'X') continue; // stone
                if (map[ny][nx] == '*') continue; // water
                visited[ny][nx] = true;
                dochiQ.add(new Dot(ny, nx, cur.t + 1));
            }
        }
        return false;
    }

    private static void waterFlood() {
        int t = waterQ.size();
        while (t-- > 0 && !waterQ.isEmpty()) {
            Dot cur = waterQ.poll();

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
                if (map[ny][nx] == '*') continue; // water
                if (map[ny][nx] == 'D') continue; // destination
                if (map[ny][nx] == 'X') continue; // stone
                if (map[ny][nx] == 'S' || map[ny][nx] == '.') map[ny][nx] = '*';
                waterQ.add(new Dot(ny, nx));
            }
        }
    }

    private static class Dot {
        int y, x, t;

        public Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Dot(int y, int x, int t) {
            this.y = y;
            this.x = x;
            this.t = t;
        }
    }
}
