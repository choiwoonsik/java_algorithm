import java.io.*;
import java.util.*;

public class alphabet2_1987 {
    static int row, col;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static char[][] map;
    static boolean[] alpha = new boolean[26];
    static int max = -100;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char[row][col];
        for (int i = 0; i < row; i++)
            map[i] = br.readLine().toCharArray();
        alpha[map[0][0] - 'A'] = true;
        DFS(0, 0, 1);
        System.out.println(max);
    }

    private static void DFS(int y, int x, int cnt) {
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny >= 0 && ny < row && nx >= 0 && nx < col) {
                if (!alpha[map[ny][nx] - 'A']) {
                    alpha[map[ny][nx] - 'A'] = true;
                    DFS(ny, nx, cnt + 1);
                    alpha[map[ny][nx] - 'A'] = false;
                }
            }
        }
        max = Math.max(max, cnt);
    }
}