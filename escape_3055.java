import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class escape_3055 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int W, H;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] board = new char[50][50];
    static int[][] raintime;
    static int[][] doccitime;
    static boolean[][] rainvisited = new boolean[50][50];
    static boolean[][] doccivisited = new boolean[50][50];
    static Dot D, S;
    static Queue<Dot> docciQ = new LinkedList<>();
    static Queue<Dot> rainQ = new LinkedList<>();
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        raintime = new int[H][W];
        doccitime = new int[H][W];

        for (int i = 0; i < H; i++)
        {
            String str = br.readLine();
            for (int j = 0; j < W; j++)
            {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'D')
                    D = new Dot(i, j);
                else if (board[i][j] == 'S')
                    S = new Dot(i, j);
                else if (board[i][j] == '*')
                    rainQ.add(new Dot(i, j));
            }
        }
        //물의 시작포인트를 리스트에 넣고, 다람쥐의 시작과 끝을 체크하고 bfs시작
        rain();
        BFS();
    }
    private static void rain()
    {
        while(!rainQ.isEmpty())
        {
            Dot rain = rainQ.poll();
            rainvisited[rain.x][rain.y] = true;
            for (int i = 0; i < 4; i++)
            {
                int x = rain.x + dx[i];
                int y = rain.y + dy[i];
                if (x >= 0 && y >= 0 && x < H && y < W) {
                    if (board[x][y] == '.' && !rainvisited[x][y]) {
                        rainvisited[x][y] = true;
                        raintime[x][y] = raintime[rain.x][rain.y] + 1;
                        rainQ.add(new Dot(x, y));
                    }
                }
            }
        }
    }
    private static void BFS()
    {
        docciQ.add(new Dot(S.x, S.y));
        while (!docciQ.isEmpty())
        {
            Dot now = docciQ.poll();
            if (now.x == D.x && now.y == D.y)
            {
                System.out.println(doccitime[now.x][now.y]);
                return;
            }
            doccivisited[now.x][now.y] = true;
            board[now.x][now.y] = '.';
            for (int i = 0; i < 4; i++)
            {
                int x = dx[i] + now.x;
                int y = dy[i] + now.y;
                if (x >=0 && y >= 0 && x < H && y < W) {
                    if ((board[x][y] == '.' || board[x][y] == 'D') && !doccivisited[x][y]) {
                        if (doccitime[now.x][now.y] + 1 < raintime[x][y] && rainvisited[x][y] || raintime[x][y] == 0) {
                            doccivisited[x][y] = true;
                            doccitime[x][y] += doccitime[now.x][now.y] + 1;
                            docciQ.add(new Dot(x, y));
                        }
                    }
                }
            }
        }
        System.out.println("KAKTUS");
        return;
    }
}