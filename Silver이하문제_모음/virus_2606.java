package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class virus_2606 {
    static int[][] board;
    static boolean[] visited;
    static int N, M, count;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException
    {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new int[N+1][N+1];
        visited = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = 1;
            board[y][x] = 1;
        }
        BFS(1);
        System.out.println(count);
    }
    private static void BFS(int dot)
    {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(dot);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            visited[now] = true;
            for (int i = 1; i <= N; i++) {
                if (board[now][i] == 1 && !visited[i]) {
                    count++;
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
