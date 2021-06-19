package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class calc_familyTree_2644 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, T1, T2, M, Count;
    static int[][] map;
    static boolean[][] visited;
    static boolean find;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];
        st = new StringTokenizer(br.readLine());
        T1 = Integer.parseInt(st.nextToken());
        T2 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
            map[y][x] = 1;
        }
        DFS(T1);
        if (!find)
            System.out.println(-1);
    }
    private static void DFS(int go)
    {
        if (go == T2)
        {
            find = true;
            System.out.println(Count);
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (i != go && map[go][i] == 1)
                if (!visited[go][i]) {
                    Count++;
                    visited[go][i] = true;
                    visited[i][go] = true;
                    DFS(i);
                    Count--;
                }
        }
    }
}
