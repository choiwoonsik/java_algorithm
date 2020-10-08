import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class shortest_distance_1753 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, start, W, MIN;
    static int[][] board;
    static boolean[][] visited;
    static boolean find;
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N + 1][N + 1];
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int go = Integer.parseInt(st.nextToken());
            int stop = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (board[go][stop] == 0)
                board[go][stop] = weight;
            else if (board[go][stop] > weight)
                board[go][stop] = weight;
        }
        for (int i = 1; i <= N; i++)
        {
            W = 0;
            find = false;
            MIN = Integer.MAX_VALUE;
            visited = new boolean[N + 1][N + 1];
            if (start == i) {
                find = true;
                sb.append(0+"\n");
            }
            else {
                DFS(start, i);
                if (MIN != Integer.MAX_VALUE)
                    sb.append(MIN+"\n");
            }
            if(!find)
                sb.append("INF\n");
        }
        System.out.println(sb);
    }
    private static void DFS(int Go, int dest)
    {
        if (Go == dest)
        {
            find = true;
            if (MIN > W)
                MIN = W;
            return;
        }
        for (int i = 1; i <= N; i++)
        {
            if (start == Go)
                W = 0;
            if(board[Go][i] > 0 && !visited[Go][i])
            {
                visited[Go][i] = true;
                W += board[Go][i];
                DFS(i, dest);
                W -= board[Go][i];
            }
        }
    }
}
