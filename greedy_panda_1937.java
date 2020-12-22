import java.io.*;
import java.util.*;

public class greedy_panda_1937 {
    static int[][] map;
    static boolean[][] visited;
    static int[][] countDay;
    static int N, Day;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        countDay = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                DFS(new Dot(y, x));
            }
        }
        System.out.println(Day);
    }

    private static int DFS(Dot dot) {
        if (countDay[dot.y][dot.x] != 0)
            return countDay[dot.y][dot.x];
        countDay[dot.y][dot.x] = 1;
        for (int d = 0; d < 4; d++) {
            int ny = dot.y + dy[d];
            int nx = dot.x + dx[d];
            if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                if (map[ny][nx] > map[dot.y][dot.x]) {
                    int day = 1;
                    day += DFS(new Dot(ny, nx));
                    countDay[dot.y][dot.x] = Math.max(countDay[dot.y][dot.x], day);
                    if (countDay[dot.y][dot.x] > Day)
                        Day = countDay[dot.y][dot.x];
                }
            }
        }
        return countDay[dot.y][dot.x];
    }

    private static class Dot
    {
        int y;
        int x;
        Dot (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
