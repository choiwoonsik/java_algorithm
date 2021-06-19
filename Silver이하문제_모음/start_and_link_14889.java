package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class start_and_link_14889 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] board;
    static boolean[] visited;
    static int N;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        board = new int[N][N];
        for (int i = 0; i < N; i++)
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        recursive(0, 0);
        System.out.println(min);
    }
    private static void recursive(int start, int depth)
    {
        if (depth == N / 2)
        {
            min = Math.min(min, get_Min());
            return;
        }
        for (int i = start; i < N; i++)
        {
            if (!visited[i])
            {
                visited[i] = true;
                recursive(i + 1, depth + 1);
                visited[i] = false;
            }
        }
    }
    private static int get_Min()
    {
        int startT = 0;
        int linkT = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visited[i] && visited[j]) {
                    startT += board[i][j];
                    startT += board[j][i];
                }
                if (!visited[i] && !visited[j]) {
                    linkT += board[i][j];
                    linkT += board[j][i];
                }
            }
        }
        return Math.abs(startT - linkT);
    }

}
