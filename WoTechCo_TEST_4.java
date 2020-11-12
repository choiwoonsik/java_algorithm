import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class WoTechCo_TEST_4 {

    static boolean[][] visited;
    static int[][] countBoard;
    static int[] d_x = {0, 0, 1, -1};
    static int[] d_y = {-1, 1, 0, 0};
    static int finalNum;
    static int answer;

    public static void main(String[] args) {
        int n = 3;
        int[][] board = {{3, 5, 6}, {9, 2, 7}, {4, 1, 8}};
        //int[][] board = {{2,3}, {4,1}};
        solution(board.length, board);
    }
    private static int solution(int n, int[][] board) {
        finalNum = n*n;

        BFS(board, n);

        System.out.println(answer);
        return answer;
    }

    private static void BFS(int[][] board, int n) {
        int start_Num = 1;
        int startX = 0;
        int startY = 0;

        while (start_Num <= finalNum) {
            visited = new boolean[n][n];
            countBoard = new int[n][n];

            Queue<Dot_pos> que = new LinkedList<>();
            que.add(new Dot_pos(startX, startY));

            while (!que.isEmpty()) {
                Dot_pos now = que.poll();
                visited[now.x][now.y] = true;

                if (board[now.x][now.y] == start_Num) {
                    answer += countBoard[now.x][now.y] + 1;
                    start_Num++;
                    startX = now.x;
                    startY = now.y;
                    break;
                }else {
                    for (int i = 0; i < 4; i++) {
                        int dx = now.x + d_x[i];
                        int dy = now.y + d_y[i];
                        if (dx >= 0 && dy >= 0 && dx < n && dy < n) {
                            if (!visited[dx][dy]) {
                                countBoard[dx][dy] += (countBoard[now.x][now.y] + 1);
                                que.add(new Dot_pos(dx, dy));
                                visited[dx][dy] = true;
                            }
                        }
                        //x가 -1
                        if (dx == -1 && dy >= 0 && dy < n) {
                            dx = n - 1;
                            if (!visited[dx][dy]) {
                                countBoard[dx][dy] += (countBoard[now.x][now.y] + 1);
                                que.add(new Dot_pos(dx, dy));
                                visited[dx][dy] = true;
                            }
                        }
                        //x가 n
                        if (dx == n && dy >= 0 && dy < n) {
                            dx = 0;
                            if (!visited[dx][dy]) {
                                countBoard[dx][dy] += (countBoard[now.x][now.y] + 1);
                                que.add(new Dot_pos(dx, dy));
                                visited[dx][dy] = true;
                            }
                        }
                        //y가 -1
                        if (dy == -1 && dx >= 0 && dx < n) {
                            dy = n - 1;
                            if (!visited[dx][dy]) {
                                countBoard[dx][dy] += (countBoard[now.x][now.y] + 1);
                                que.add(new Dot_pos(dx, dy));
                                visited[dx][dy] = true;
                            }
                        }
                        //y가 n
                        if (dy == n && dx >= 0 && dx < n) {
                            dy = 0;
                            if (!visited[dx][dy]) {
                                countBoard[dx][dy] += (countBoard[now.x][now.y] + 1);
                                que.add(new Dot_pos(dx, dy));
                                visited[dx][dy] = true;
                            }
                        }
                    }
                }
            }
        }
    }
}

class Dot_pos
{
    int x;
    int y;
    Dot_pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
