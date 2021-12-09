package Gold이상문제_정리;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불_5427 {
    static int N;
    static StringTokenizer st;
    static BufferedReader br;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            escapeRoom(n, m);
        }
        System.out.print(answer);
    }

    private static void escapeRoom(int r, int c) throws IOException {
        char[][] map = new char[r][c];
        Queue<Dot> fireQ = new LinkedList<>();
        Dot me = new Dot(0, 0, 0);

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '@') {
                    me = new Dot(i, j, 0);
                } else if (map[i][j] == '*') {
                    fireQ.add(new Dot(i, j));
                }
            }
        }

        int start;
        if ((start = start(map, me, fireQ)) == 0) {
            answer.append("IMPOSSIBLE").append("\n");
        } else {
            answer.append(start).append("\n");
        }
    }

    private static int start(char[][] map, Dot me, Queue<Dot> fireQ) {
        int r = map.length;
        int c = map[0].length;
        boolean[][] visited = new boolean[r][c];
        boolean[][] fireVisited = new boolean[r][c];
        int[] dy = new int[]{-1, 1, 0, 0};
        int[] dx = new int[]{0, 0, -1, 1};
        Queue<Dot> meQ = new LinkedList<>();
        meQ.add(me);
        visited[me.y][me.x] = true;

        while (!meQ.isEmpty()) {

            // 나 옮기기
            int tmp = meQ.size();
            while (tmp-- > 0) {
                Dot now = meQ.poll();

                if (fireVisited[now.y][now.x]) continue;

                for (int d = 0; d < 4; d++) {
                    int ny = dy[d] + now.y;
                    int nx = dx[d] + now.x;

                    if (ny < 0 || nx < 0 || ny >= r || nx >= c) {
                        return now.move + 1;
                    }
                    if (map[ny][nx] == '*') continue;
                    if (map[ny][nx] == '#') continue;
                    if (fireVisited[ny][nx]) continue;
                    if (visited[ny][nx]) continue;
                    visited[ny][nx] = true;
                    meQ.add(new Dot(ny, nx, now.move + 1));
                }
            }
            // 불 옮기기
            tmp = fireQ.size();
            while (tmp-- > 0) {
                Dot now = fireQ.poll();

                for (int d = 0; d < 4; d++) {
                    int ny = dy[d] + now.y;
                    int nx = dx[d] + now.x;

                    if (ny < 0 || nx < 0 || ny >= r || nx >= c) continue;
                    if (fireVisited[ny][nx]) continue;
                    if (map[ny][nx] == '#') continue;
                    fireVisited[ny][nx] = true;
                    fireQ.add(new Dot(ny, nx));
                }
            }
        }
        return 0;
    }

    private static class Dot {
        int y, x;
        int move;

        Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }

        Dot(int y, int x, int move) {
            this.y = y;
            this.x = x;
            this.move = move;
        }
    }
}
