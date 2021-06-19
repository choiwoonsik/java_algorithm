package Silver이하문제_모음;
//빨간 구슬이 구멍에 빠지면 성공
//파란 구슬이 구멍에 빠지면 실패, 동시에 같이 빠져도 실패
//빨간구슬과 파란구슬은 함께 움직인다
//왼쪽 오른쪽 위쪽 아래쪽 (-1, 1, 1, -1)으로 움직이기 가능
//
//10번 이하로 빨간 구슬만을 구멍으로 빼낼수 있는지 구하는 프로그램

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class escape_marble_13459 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Queue<Dot_count> queue = new LinkedList<>();
    static int[] pos_x = {-1, 1, 0, 0};
    static int[] pos_y = {0, 0, -1, 1};
    static int H;
    static int W;
    static Dot dest;
    static int turn;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException
    {
        int []arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        H = arr[0];
        W = arr[1];
        char[][] board = new char[H][W];
        visited = new boolean[H][W];

        for (int i = 0; i < H; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                if (board[i][j] == 'R') {
                    queue.add(new Dot_count(i, j, 0));
                    visited[i][j] = true;
                }
                if (board[i][j] == 'O')
                    dest = new Dot(i, j);
            }
        }
        BFS(board);
    }
    private static void BFS(char[][] board)
    {
        while (!queue.isEmpty())
        {
            Dot_count dot = queue.poll();
            for (int i = 0; i < 4; i++)
            {
                int x = dot.x + pos_x[i];
                int y = dot.y + pos_y[i];
                if (x > 0 && y > 0 && x <= W && y < H) {
                    if (board[x][y] == '.') {
                        System.out.print("BFS > move : 높이 :" + x +", 가로 :" + y +"\n");
                        visited[x][y] = true;
                        turn++;
                        DFS(board, new Dot_count(x, y, dot.count), i);
                    }
                    else if (x == dest.x && y == dest.y) {
                        System.out.println(1);
                        return ;
                    }
                }
            }
        }
    }
    private static void DFS(char[][] board, Dot_count dot, int dir)
    {
        int x = dot.x + pos_x[dir];
        int y = dot.y + pos_y[dir];

        if (board[x][y] == 'O') {
            if (dot.count > 10) {
                System.out.println(0);
                return ;
            }
            else {
                System.out.println(1);
                System.out.println("turn count : "+dot.count);
            }
            return ;
        }
        else if (board[x][y] == '.' && !visited[x][y]) {
            visited[x][y] = true;
            System.out.print("      DFS > move : 높이 :" + x +", 가로 :" + y +"\n");
            DFS(board, new Dot_count(x, y, dot.count), dir);
        }
        else if (board[x][y] == '#') {
            System.out.println("\nturn dircetcion\n");
            dot.count++;
            queue.add(dot);
        }
    }
}
