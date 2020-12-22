import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Cheese_2636 {
    static int[][] originMap;
    static boolean[][] outAirMap;
    static int[][] copyMap;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int r, c, cheeseCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        originMap = new int[r][c];
        copyMap = new int[r][c];
        outAirMap = new boolean[r][c];

        // 맵 채우기
        for (int y = 0; y < r; y++)
        {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < c; x++){
                int val = Integer.parseInt(st.nextToken());
                originMap[y][x] = val;
                copyMap[y][x] = val;
            }
        }

        int ret_cheeseCnt;
        int hour = 0;
        while (true) {
            // out Air all check
            airDFS();
            // cheeseCnt and melt cheese check
            ret_cheeseCnt = cheeseCnt;
            check_melt_cheese();
            // copy Map
            for (int i = 0; i < r; i++)
                originMap[i] = copyMap[i].clone();
            if (cheeseCnt == 0)
                break;
            hour++;
        }

        System.out.println(hour);
        System.out.println(ret_cheeseCnt);
    }

    private static void check_melt_cheese() {
        visited = new boolean[r][c];
        cheeseCnt = 0;
        for (int y = 0; y < r; y++) {
            for (int x = 0; x < c; x++) {
                if (originMap[y][x] == 1 && !visited[y][x])
                    cheeseDFS(y, x);
            }
        }
    }

    private static void cheeseDFS(int y, int x) {
        Queue<Dot> que = new LinkedList<>();
        que.add(new Dot(y, x));
        while (!que.isEmpty())
        {
            Dot now = que.poll();
            cheeseCnt++;
            visited[now.y][now.x] = true;
            for (int i = 0; i < 4; i++)
            {
                int nY = dy[i] + now.y;
                int nX = dx[i] + now.x;
                if (nY >= 0 && nY < r && nX >= 0 && nX < c) {
                    // 치즈인 경우 개수 세고 더이상 방문하지 않음
                    if (originMap[nY][nX] == 1 && !visited[nY][nX]) {
                        visited[nY][nX] = true;
                        que.add(new Dot(nY, nX));
                    }
                    // 주변에 공기가 있는 경우 && 바깥공기 인 경우 - 현재 치즈 녹인다
                    if (originMap[nY][nX] == 0 && outAirMap[nY][nX])
                        copyMap[now.y][now.x] = 0;
                }
            }
        }
    }

    private static void airDFS() {
        visited = new boolean[r][c];
        Queue<Dot> que = new LinkedList<>();
        que.add(new Dot(0, 0));
        while (!que.isEmpty())
        {
            Dot now = que.poll();
            visited[now.y][now.x] = true;
            outAirMap[now.y][now.x] = true;
            for (int i = 0; i < 4; i++)
            {
                int nY = now.y + dy[i];
                int nX = now.x + dx[i];
                if (nY >= 0 && nY < r && nX >= 0 && nX < c) {
                    if (!visited[nY][nX] && originMap[nY][nX] == 0) {
                        visited[nY][nX] = true;
                        que.add(new Dot(nY, nX));
                    }
                }
            }
        }
    }
    private static class Dot {
        int y;
        int x;
        public Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
