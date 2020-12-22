import java.io.*;
import java.util.*;

public class cheese_2638 {
    static int[][] map;
    static int[][] subMap;
    static boolean[][] isOutAir;
    static boolean[][] visited;
    static int N, M;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean noMore;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        subMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int val = Integer.parseInt(st.nextToken());
                map[i][j] = val;
                subMap[i][j] = val;
            }
        }

        int hour = 0;
        while(true)
        {
            isOutAir = new boolean[N][M];
            visited = new boolean[N][M];
            noMore = true;
            // 외부공기 확인
            check_outAir(0, 0);
            // 외부공기랑 닿은 치즈녹기
            melt_cheese();
            map = subMap;
            if (noMore)
                break;
            hour++;
        }
        System.out.println(hour);
    }

    private static void melt_cheese() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 1) {
                    noMore = false;
                    int count = 0;
                    for (int d = 0; d < 4; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                            if (isOutAir[ny][nx])
                                count++;
                        }
                    }
                    if (count >= 2)
                        subMap[y][x] = 0;
                }
            }
        }
    }

    private static void check_outAir(int y, int x) {
        if (map[y][x] == 0 && !isOutAir[y][x]) {
            isOutAir[y][x] = true;
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny >= 0 && ny < N && nx >= 0 && nx < M)
                    check_outAir(ny, nx);
            }
        }
    }
}
