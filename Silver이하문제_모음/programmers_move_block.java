package Silver이하문제_모음;

import java.util.*;

public class programmers_move_block {
    static int[][] dir_plus = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][][] visited;
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}};
        System.out.println(solution(board));
    }
    public static int solution(int[][] board) {
        int answer = 0;

        int N = board.length;
        visited = new boolean[N][N][2];

        robot init = new robot(0, 0,0, 1, 0, 0);
        Queue<robot> que = new LinkedList<>();
        que.offer(init);

        while (!que.isEmpty())
        {
            robot robo = que.poll();

            if (robo.y1 < 0 || robo.x1 < 0 || robo.y2 < 0 || robo.x2 < 0
                || robo.y1 >= N || robo.x1 >= N || robo.y2 >= N || robo.x2 >= N)
                continue;
            if (board[robo.y1][robo.x1] == 1 || board[robo.y2][robo.x2] == 1)
                continue;
            if (visited[robo.y1][robo.x1][robo.garo_sero]
            && visited[robo.y2][robo.x2][robo.garo_sero]) {
                continue;
            }
            if ((robo.y1 == N - 1 && robo.x1 == N - 1) || (robo.y2 == N - 1 && robo.x2 == N - 1)) {
                answer = robo.time;
                break;
            }

            visited[robo.y1][robo.x1][robo.garo_sero] = true;
            visited[robo.y2][robo.x2][robo.garo_sero] = true;

            // 가로세로 유지한채 그대로 좌우상하 옮기기
            for (int dir = 0; dir < 4; dir++)
            {
                int nY1 = robo.y1 + dir_plus[dir][0];
                int nX1 = robo.x1 + dir_plus[dir][1];
                int nY2 = robo.y2 + dir_plus[dir][0];
                int nX2 = robo.x2 + dir_plus[dir][1];
                que.offer(new robot(nY1, nX1, nY2, nX2, robo.garo_sero, robo.time + 1));
            }

            // 회전하기
            if (robo.garo_sero == 0) {
                // 내려가기
                if (robo.y1 + 1 < N && board[robo.y1 + 1][robo.x1] == 0 && board[robo.y2 + 1][robo.x2] == 0) {
                    que.offer(new robot(robo.y1, robo.x1, robo.y1 + 1, robo.x1, 1, robo.time + 1));
                    que.offer(new robot(robo.y2, robo.x2, robo.y2 + 1, robo.x2, 1, robo.time + 1));
                }
                // 올라가기
                if (robo.y1 - 1 >= 0 && board[robo.y1 - 1][robo.x1] == 0 && board[robo.y2 - 1][robo.x2] == 0) {
                    que.offer(new robot(robo.y1, robo.x1, robo.y1 - 1, robo.x1, 1, robo.time + 1));
                    que.offer(new robot(robo.y2, robo.x2, robo.y2 - 1, robo.x2, 1, robo.time + 1));
                }
            } else if (robo.garo_sero == 1) {
                // 오른쪽
                if (robo.x1 + 1 < N && board[robo.y1][robo.x1 + 1] == 0 && board[robo.y2][robo.x2 + 1] == 0) {
                    que.offer(new robot(robo.y1, robo.x1, robo.y1, robo.x1 + 1, 0, robo.time + 1));
                    que.offer(new robot(robo.y2, robo.x2, robo.y2, robo.x2 + 1, 0, robo.time + 1));
                }
                // 왼쪽
                if (robo.x1 - 1 >= 0 && board[robo.y1][robo.x1 - 1] == 0 && board[robo.y2][robo.x2 - 1] == 0) {
                    que.offer(new robot(robo.y1, robo.x1, robo.y1, robo.x1 - 1, 0, robo.time + 1));
                    que.offer(new robot(robo.y2, robo.x2, robo.y2, robo.x2 - 1, 0, robo.time + 1));
                }
            }
        }

        return answer;
    }
    private static class robot
    {
        int y1;
        int x1;
        int y2;
        int x2;
        int garo_sero;
        int time;

        public robot(int y1, int x1, int y2, int x2, int garo_sero, int time) {
            this.y1 = y1;
            this.x1 = x1;
            this.y2 = y2;
            this.x2 = x2;
            this.garo_sero = garo_sero;
            this.time = time;
        }
    }
}
