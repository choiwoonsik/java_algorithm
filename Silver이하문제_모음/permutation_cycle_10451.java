package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class permutation_cycle_10451 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[][] map;
    static boolean[] visited;
    static int cnt;

    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++)
        {
            cnt = 0;
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            map = new boolean[M+1][M+1];
            visited = new boolean[M+1];
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++)
            {
                int y = Integer.parseInt(st.nextToken());
                map[j][y] = true;
            }
            for(int k = 1; k <= M; k++) {
                if (!visited[k])
                    cnt++;
                    dfs(k, M);
            }
            System.out.println(cnt);
        }
    }
    private static void dfs(int start, int M)
    {
        visited[start] =true;
        for (int i = 1; i <= M; i++) {
            if (map[start][i] == true && !visited[i])
                dfs(i, M);
        }
    }
}
