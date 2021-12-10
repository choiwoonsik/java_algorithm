package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇시물레이션_2174 {
    static int R, C, N, M, end;
    static Robot[] robots = new Robot[101];
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = direction(st.nextToken());
            robots[i + 1] = new Robot(y, x, dir);
            map[y][x] = i + 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            String order = st.nextToken();
            int count = Integer.parseInt(st.nextToken());

            move(idx, order, count);
            if (end == -1) return;
        }
        System.out.println("OK");
    }

    private static void move(int idx, String order, int count) {
        switch (order) {
            case "L":
            {
                while (count-- > 0) {
                    robots[idx].dir = (robots[idx].dir + 1) % 4;
                }
                return;
            }
            case "R":
            {
                while (count-- > 0) {
                    robots[idx].dir = (robots[idx].dir - 1);
                    if (robots[idx].dir < 0) robots[idx].dir = 3;
                }
                return;
            }
            case "F":
            {
                while (count-- > 0) {
                    int tmp = go(idx);
                    if (tmp == -1) {
                        System.out.printf("Robot %d crashes into the wall", idx);
                        end = -1;
                        return;
                    } else if (tmp > 0) {
                        System.out.printf("Robot %d crashes into robot %d", idx, tmp);
                        end = -1;
                        return;
                    }
                }
            }
        }
    }

    private static int go(int idx) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        Robot robot = robots[idx];
        map[robot.y][robot.x] = 0;
        int ny = robot.y + dy[robot.dir];
        int nx = robot.x + dx[robot.dir];
        if (ny < 0 || nx < 0 || ny >= R || nx >= C)
            return -1;
        else if (map[ny][nx] > 0)
            return map[ny][nx];
        else {
            map[ny][nx] = idx;
            robots[idx].y = ny;
            robots[idx].x = nx;
            return 0;
        }
    }

    private static int direction(String nextToken) {
        switch (nextToken) {
            case "S":
                return 0;
            case "E":
                return 1;
            case "N":
                return 2;
            case "W":
                return 3;
        }
        return -1;
    }

    private static class Robot {
        int y;
        int x;
        int dir;

        public Robot(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }
}
