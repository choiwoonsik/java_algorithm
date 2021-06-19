package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class organic_baechu_2_1012 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] map;
    static int height, width;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder str = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int count;

        while (T-- > 0)
        {
            count = 0;
            map = new boolean[51][51];
            st = new StringTokenizer(br.readLine());
            width = Integer.parseInt(st.nextToken());
            height = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for (int i = 0; i < k; i++)
            {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = true;
            }
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (map[y][x]) {
                        count++;
                        DFS(y, x);
                    }
                }
            }
            str.append(count).append("\n");
        }
        System.out.println(str);
    }

    private static void DFS(int y, int x) {
        map[y][x] = false;
        for (int i = 0; i < 4; i++)
        {
            int nY = dy[i] + y;
            int nX = dx[i] + x;
            if (nY >= 0 && nY < height && nX >= 0 && nX < width) {
                if (map[nY][nX])
                    DFS(nY, nX);
            }
        }
    }
}
