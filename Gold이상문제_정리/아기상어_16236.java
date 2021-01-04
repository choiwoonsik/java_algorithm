package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 아기상어_16236 {
    static int[][] map;
    static int[][] countMap;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        countMap = new int[N][N];
        visited = new boolean[N][N];
        Shark baby = new Shark(0, 0, 0);

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 9)
                    baby = new Shark(y, x, 0);
            }
        }

        PriorityQueue<Shark> que = new PriorityQueue<>();
        que.add(baby);

        int babySize = 2;
        int babyEat = 0;
        int move = 0;
        while (!que.isEmpty())
        {
            Shark now = que.poll();
            visited[now.y][now.x] = true;

            if (map[now.y][now.x] < babySize && map[now.y][now.x] >= 1) {
                babyEat++;
                map[baby.y][baby.x] = 0;
                map[now.y][now.x] = 9;
                baby.y = now.y;
                baby.x = now.x;
                move += countMap[now.y][now.x];
                if (babyEat == babySize) {
                    babyEat = 0;
                    babySize++;
                }
                visited = new boolean[N][N];
                countMap = new int[N][N];
                visited[now.y][now.x] = true;
                que.clear();
            }
            for (int d = 0; d < 4; d++)
            {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                    if (map[ny][nx] <= babySize && !visited[ny][nx]) {
                        countMap[ny][nx] = countMap[now.y][now.x] + 1;
                        que.add(new Shark(ny, nx, countMap[ny][nx]));
                        visited[ny][nx] = true;
                    }
                }
            }
        }
        System.out.println(move);
    }

    private static class Shark implements Comparable<Shark>
    {
        int y;
        int x;
        int dis;

        public Shark(int y, int x, int dis) {
            this.y = y;
            this.x = x;
            this.dis = dis;
        }

        @Override
        public int compareTo(Shark o) {
            if (this.dis < o.dis)
                return -1;
                // 거리가 같다면
            else if (this.dis == o.dis)
            {
                if (this.y < o.y)
                    return -1;
                else if (this.y == o.y)
                {
                    if (this.x < o.x)
                        return -1;
                }
                else
                    return 1;
            }
            // 거리가 멀다면
            return 1;
        }
    }
}
