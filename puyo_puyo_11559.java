import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class puyo_puyo_11559 {
    static char[][] board = new char[12][6];
    static boolean[][] visited = new boolean[12][6];
    static Stack<Dot> stack = new Stack<>();
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static char[] RGBPY = {'R', 'G', 'B', 'P', 'Y'};
    static int Boom;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st;

        for (int y = 0; y < 12; y++) {
            st = br.readLine();
            for (int x = 0; x < 6; x++) {
                board[y][x] = st.charAt(x);
            }
        }

        while (true)
        {
            // 현재 보드에 대해서 4개 이상의 칼라로 된 뿌요가 있다면 그 좌표들을 스택에 담는다
            visited = new boolean[12][6];
            for (int y = 0; y < 12; y++) {
                for (int x = 0; x < 6; x++) {
                    if (board[y][x] != '.' && !visited[y][x])
                        BFS(y, x);
                }
            }
            if (stack.isEmpty()) {
                System.out.println(Boom);
                break;
            }
            // 4개이상의 뿌들이모여 있는 시작 좌표들에 대해서 터트린다
            else {
                Boom++;
                boom_color();
            }
            // 중력으로 모든 뿌들을 내린다
            go_down_bboo();
        }
    }

    private static void print_board() {
        for (int y = 0; y < 12; y++) {
            for (int x = 0; x < 6; x++) {
                System.out.print(board[y][x]);
            }
            System.out.println();
        }
        System.out.println("========");
    }

    private static void go_down_bboo() {
        boolean[] line = new boolean[6];
        Arrays.fill(line, false);
        int height;
        for (int y = 11; y >= 1; y--) {
            for (int x = 0; x < 6; x++) {
                height = y;
                if (board[height][x] == '.' && !line[x]) {
                    for (int h = height - 1; h >= 0; h--) {
                        if (board[h][x] != '.') {
                            board[height][x] = board[h][x];
                            board[h][x] = '.';
                            if (height >= 1)
                                height--;
                        }
                    }
                    line[x] = true;
                }
            }
        }
    }

    private static void boom_color() {
        visited = new boolean[12][6];
        while (!stack.isEmpty()) {
            Dot get_color = stack.pop();
            char color = board[get_color.y][get_color.x];
            Queue<Dot> que = new LinkedList<>();
            que.add(get_color);
            while (!que.isEmpty()) {
                Dot now = que.poll();
                board[now.y][now.x] = '.';
                for (int i = 0; i < 4; i++) {
                    int nY = now.y + dy[i];
                    int nX = now.x + dx[i];
                    if (nY >= 0 && nY < 12 && nX >= 0 && nX < 6) {
                        if (board[nY][nX] == color && !visited[nY][nX]) {
                            visited[nY][nX] = true;
                            board[nY][nX] = '.';
                            que.add(new Dot(nY, nX));
                        }
                    }
                }
            }
        }
    }

    private static void BFS(int y, int x) {
        Queue<Dot> que = new LinkedList<>();
        que.add(new Dot(y, x));
        visited[y][x] = true;
        int cnt = 0;
        for (int color = 0; color < 5; color++)
        {
            if (board[y][x] == RGBPY[color]) {
                while (!que.isEmpty()) {
                    Dot now = que.poll();
                    cnt++;
                    for (int i = 0; i < 4; i++) {
                        int nY = now.y + dy[i];
                        int nX = now.x + dx[i];
                        if (nY >= 0 && nY < 12 && nX >= 0 && nX < 6) {
                            if (board[nY][nX] == RGBPY[color] && !visited[nY][nX]) {
                                que.add(new Dot(nY, nX));
                                visited[nY][nX] = true;
                            }
                        }
                    }
                }
                if (cnt >= 4)
                    stack.add(new Dot(y, x));
            }
        }
    }
    private static class Dot{
        int y;
        int x;

        public Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
/*
......
......
......
......
......
....Y.
....Y.
....Y.
....RR
...YRR
..GGYY
..GGYY
 */
