import java.io.*;
import java.util.*;

public class iceMountain2_2573 {
    static int row;
    static int col;
    static int[][] map;
    static int[][] subMap;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int count;
    static boolean noIce;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        subMap = new int[row][col];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                int size = Integer.parseInt(st.nextToken());
                map[i][j] = size;
                subMap[i][j] = size;
            }
        }

        int year = 0;
        while(true) {
            year++;
            melt();
            if (check())
                break;
        }
        if (noIce)
            System.out.println(0);
        else
            System.out.println(year);
    }

    private static boolean check() {
        count = 0;
        visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    DFS(new Dot(i, j));
                    count++;
                    if (count > 1)
                        return true;
                }
            }
        }
        if (count == 0)
            noIce = true;
        return count != 1;
    }

    private static void DFS(Dot dot) {
        visited[dot.y][dot.x] = true;
        for (int d = 0; d < 4; d++) {
            int ny = dot.y + dy[d];
            int nx = dot.x + dx[d];
            if (ny >= 0 && ny < row && nx >= 0 && nx < col) {
                if (!visited[ny][nx] && map[ny][nx] > 0) {
                    DFS(new Dot(ny, nx));
                }
            }
        }
    }

    private static void melt() {
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                if (map[y][x] > 0) {
                    int count = 0;
                    for (int d = 0; d < 4; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if (map[ny][nx] == 0)
                            count++;
                    }
                    subMap[y][x] -= Math.min(count, map[y][x]);
                }
            }
        }
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                map[y][x] = subMap[y][x];
            }
        }
    }

    private static class Dot {
        int y;
        int x;
        Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
