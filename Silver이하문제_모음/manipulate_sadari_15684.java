package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class manipulate_sadari_15684 {
    static int N;
    static int M;
    static int H;
    static int ret;
    static boolean[][] sadari;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        sadari = new boolean[H + 1][N + 1];
        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int side = Integer.parseInt(st.nextToken());
            sadari[height][side] = true;
        }
        ret = 4;
        DFS(0, 1, 1);
        if (ret > 3) {
            System.out.println(-1);
            return;
        }
        System.out.println(ret);
    }

    private static void DFS(int count, int height, int dari) {
        if (count >= ret)
            return ;
        if (checkDari()) {
            ret = count;
            return ;
        }
        if (count == 3)
            return;
        for (int i = height; i <= H; i++)
        {
            for (int j = dari; j < N; j++)
            {
                if (!sadari[i][j] && !sadari[i][j - 1] && !sadari[i][j + 1])
                {
                    sadari[i][j] = true;
                    DFS(count + 1, i, j);
                    sadari[i][j] = false;
                }
            }
            dari = 1;
        }
    }

    private static boolean checkDari() {
        for (int i = 1; i <= N; i++) {
            int pos = i;
            for (int y = 0; y <= H; y++) {
                if (sadari[y][pos])
                    pos += 1;
                else if (sadari[y][pos - 1])
                    pos -= 1;
            }
            if (pos != i) {
                return false;
            }
        }
        return true;
    }
}
