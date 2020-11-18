import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baby_shark2_16236 {
    static int[][] board;
    static int[][] moveBoard;
    static boolean[][] visited;
    static int N;
    static int Time;
    static int Size;
    static int eatCnt;
    static int[] d_y = {-1, 0, 0, 1};
    static int[] d_x = {0, -1, 1, 0};
    static shark_pos baby;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9)
                    baby = new shark_pos(i, j, 0);
            }
        }
        Size = 2;
        Time = 0;
        Eat();
        System.out.println(Time);
    }

    private static void Eat() {
        PriorityQueue<shark_pos> sharkQueue = new PriorityQueue<>();
        visited = new boolean[N][N];
        moveBoard = new int[N][N];
        sharkQueue.add(baby);
        shark_pos before = new shark_pos(baby.y, baby.x, 0);
        while (!sharkQueue.isEmpty())
        {
            shark_pos now = sharkQueue.poll();
            visited[now.y][now.x] = true;

            if (board[now.y][now.x] > 0 && board[now.y][now.x] < Size) {
                eatCnt++;
                board[before.y][before.x] = 0;
                board[now.y][now.x] = 9;
                before.y = now.y;
                before.x = now.x;
                baby.y = now.y;
                baby.x = now.x;
                Time += moveBoard[now.y][now.x];
                if (eatCnt == Size) {
                    Size++;
                    eatCnt = 0;
                }
                sharkQueue.clear();
                visited = new boolean[N][N];
                moveBoard = new int[N][N];
                visited[now.y][now.x] = true;
            }
            for (int i = 0; i < 4; i++)
            {
                int next_y = d_y[i] + now.y;
                int next_x = d_x[i] + now.x;
                if (next_y >= 0 && next_y < N && next_x >= 0 && next_x < N) {
                    if (!visited[next_y][next_x] && board[next_y][next_x] <= Size){
                        moveBoard[next_y][next_x] = moveBoard[now.y][now.x] + 1;
                        sharkQueue.add(new shark_pos(next_y, next_x, moveBoard[next_y][next_x]));
                        visited[next_y][next_x] = true;
                    }
                }
            }
        }
    }
}

class shark_pos implements Comparable<shark_pos>
{
    int y;
    int x;
    int dis;

    public shark_pos(int y, int x, int dis) {
        this.y = y;
        this.x = x;
        this.dis = dis;
    }

    @Override
    public int compareTo(shark_pos o) {
        // 가깝다면
        if (this.dis < o.dis)
            return -1;
        // 거리가 같다면
        else if (this.dis == o.dis) {
            // 높이가 높으면
            if (this.y < o.y)
                return -1;
            // 높이가 같다면
            else if (this.y == o.y) {
                if (this.x < o.x)
                    return -1;
            }
            // 높이가 낮으면
            else
                return 1;
        }
        // 거리가 멀다면
        return 1;
    }
}
