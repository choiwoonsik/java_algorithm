package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 뱀_3190 {
    static final int right = 0, up = 1, left = 2, down = 3;
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};
    static final int A = 1;
    static final int BODY = 2;
    static int N;
    static int[][] map;
    static int appleCount;
    static int cmdCount;
    static int T = 0;
    static Snake snake;
    static Queue<CMD> cmds = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        appleCount = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < appleCount; i++) {
            String[] pos = br.readLine().split(" ");
            int y = Integer.parseInt(pos[0]) - 1;
            int x = Integer.parseInt(pos[1]) - 1;
            map[y][x] = A;
        }

        cmdCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < cmdCount; i++) {
            String[] cmd = br.readLine().split(" ");
            int time = Integer.parseInt(cmd[0]);
            String command = cmd[1];
            cmds.add(new CMD(time, command));
        }

        solve();
        System.out.println(T);
    }

    private static void solve() {
        LinkedList<Dot> body = new LinkedList<>();
        body.add(new Dot(0, 0));
        snake = new Snake(right, new Dot(0, 0), body);
        map[0][0] = BODY;

        while (true) {
            T++;
            if (!move()) return;
            // turn
            if (!cmds.isEmpty() && T == cmds.peek().time) {
                turn(cmds.poll().command);
            }
        }
    }

    private static void turn(String command) {
        if (command.equals("L")) {
            snake.dir = ++snake.dir % 4;
        } else {
            if (snake.dir == right) snake.dir = down;
            else snake.dir--;
        }
    }

    private static boolean move() {
        Dot now = snake.head;
        int dir = snake.dir;
        int ny = now.y + dy[dir];
        int nx = now.x + dx[dir];

        // 벽 || 몸통
        if (ny < 0 || nx < 0 || ny >= N || nx >= N) return false;
        if (map[ny][nx] == BODY) return false;

        // apple
        if (map[ny][nx] == A) {
            map[ny][nx] = BODY;
            snake.body.addFirst(new Dot(ny, nx));
        }// no apple
        else {
            map[ny][nx] = BODY;
            snake.body.addFirst(new Dot(ny, nx));
            if (snake.body.size() > 0) {
                Dot tail = snake.body.pollLast();
                map[tail.y][tail.x] = 0;
            }
        }
        snake.head.y = ny;
        snake.head.x = nx;
        return true;
    }

    private static class CMD {
        int time;
        String command;

        public CMD(int time, String command) {
            this.time = time;
            this.command = command;
        }
    }

    private static class Snake {
        int dir;
        Dot head;
        Deque<Dot> body;

        public Snake(int dir, Dot head, Deque<Dot> body) {
            this.dir = dir;
            this.head = head;
            this.body = body;
        }
    }

    private static class Dot {
        int y, x;

        public Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
