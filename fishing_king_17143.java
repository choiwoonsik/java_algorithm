import java.io.*;
import java.util.*;

public class fishing_king_17143 {
    static ArrayList<Shark> sharks = new ArrayList<>();
    static Shark[][] board;
    static int row;
    static int col;
    static int sharkN;
    static int allW;
    static int[] dy = {0, -1, 1, 0, 0};
    static int[] dx = {0, 0, 0, 1, -1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st .nextToken());
        col = Integer.parseInt(st .nextToken());
        sharkN = Integer.parseInt(st .nextToken());

        for (int s = 0; s < sharkN; s++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int sp = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            sharks.add(new Shark(y, x, sp, dir, size));
        }

        boolean isRemove;
        PriorityQueue<Shark> nearSk = new PriorityQueue<>(Comparator.comparingInt(o -> o.y));
        for (int king = 0; king <= col; king++)
        {
            if (king == col)
                break;
            isRemove = false;
            nearSk.clear();
            // 낚시
            for (Shark shark : sharks) {
                if (shark.x == king) {
                    isRemove = true;
                    nearSk.add(shark);
                }
            }
            if (isRemove && !nearSk.isEmpty()) {
                Shark get = nearSk.poll();
                allW += get.size;
                sharks.remove(get);
            }
            // 이동
            move_sharks();
            eat_sharks();
        }
        System.out.println(allW);
    }

    private static void eat_sharks() {
        board = new Shark[row][col];
        ArrayList<Shark> remove = new ArrayList<>();

        for (Shark eat : sharks)
        {
            if (board[eat.y][eat.x] == null)
                board[eat.y][eat.x] = eat;
            else if (board[eat.y][eat.x].size > eat.size)
                remove.add(eat);
            else {
                remove.add(board[eat.y][eat.x]);
                board[eat.y][eat.x] = eat;
            }
        }
        for (Shark die : remove)
            sharks.remove(die);
    }

    private static void move_sharks() {
        for (Shark nowSk : sharks)
        {
            int y = nowSk.y;
            int x = nowSk.x;
            for (int move = 0; move < nowSk.speed; move++) {
                if ((x == col - 1 && nowSk.dir == 3) || (y == row - 1 && nowSk.dir == 2)) {
                    if (nowSk.dir == 3)
                        nowSk.dir = 4;
                    else
                        nowSk.dir = 1;
                }
                else if ((x == 0 && nowSk.dir == 4) || (y == 0 && nowSk.dir == 1)) {
                    if (nowSk.dir == 4)
                        nowSk.dir = 3;
                    else
                        nowSk.dir = 2;
                }
                y += dy[nowSk.dir];
                x += dx[nowSk.dir];
            }
            nowSk.y = y;
            nowSk.x = x;
        }
    }

    private static class Shark
    {
        int y;
        int x;
        int speed;
        int dir;
        int size;

        public Shark(int y, int x, int speed, int dir, int size) {
            this.y = y;
            this.x = x;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }
}
//4 4 4
//1 1 1 2 10
//2 1 0 1 1
//3 1 1 2 10
//4 1 0 1 1