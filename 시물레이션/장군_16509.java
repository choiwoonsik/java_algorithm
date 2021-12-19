package 시물레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
0 1
8 4
 */
public class 장군_16509 {
    private static boolean canGo;
    private static int[][] board;
    private static boolean[][][] visited;
    private static Queue<Dot> atkQ;

    private static class Dot {
        int y, x, move;

        Dot(int y, int x, int move) {
            this.y = y;
            this.x = x;
            this.move = move;
        }
    }

    private static boolean isOut(int ny, int nx) {
        return ny < 0 || nx < 0 || ny >= 10 || nx >= 9;
    }

    private static int killKing(Dot atk, Dot king) {
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, 1, -1};
        int[] leftUp = {-1, -1};
        int[] rightUp = {-1, 1};
        int[] leftDown = {1, -1};
        int[] rightDown = {1, 1};

        board = new int[10][9];
        visited = new boolean[10][9][4];
        atkQ = new LinkedList<>();
        board[king.y][king.x] = 1;
        atkQ.add(atk);

        while (!atkQ.isEmpty()) {
            Dot now = atkQ.poll();

            if (now.y == king.y && now.x == king.x) return now.move;

            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + now.y;
                int nx = dx[d] + now.x;

                if (isOut(ny, nx)) continue;
                if (board[ny][nx] == 1) continue;

                canGo = true;
                switch (d) {
                    case 0:
                    {
                        checkSlide(ny, nx, now.move, d, leftUp);
                        checkSlide(ny, nx, now.move, d, rightUp);
                        break;
                    }
                    case 1:
                    {
                        checkSlide(ny,nx, now.move, d, leftDown);
                        checkSlide(ny,nx, now.move, d, rightDown);
                        break;
                    }
                    case 2:
                    {
                        checkSlide(ny,nx, now.move, d, rightUp);
                        checkSlide(ny,nx, now.move, d, rightDown);
                        break;
                    }
                    case 3:
                    {
                        checkSlide(ny,nx, now.move, d, leftUp);
                        checkSlide(ny,nx, now.move, d, leftDown);
                    }
                }
            }
        }
        return  -1;
    }

    private static void checkSlide(int ny, int nx, int move, int d, int[] slide) {
        for (int m = 0; m < 2; m++) {
            ny += slide[0];
            nx += slide[1];
            if (isOut(ny, nx) || (m == 0 && board[ny][nx] == 1)) {
                canGo = false;
                break;
            }
        }
        if (canGo && !visited[ny][nx][d]) {
            visited[ny][nx][d] = true;
            atkQ.add(new Dot(ny, nx, move + 1));
        }
        canGo = true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        Dot atk = new Dot(y, x, 0);

        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        Dot king = new Dot(y, x, 0);

        System.out.println(killKing(atk, king));
    }
}
