import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SnakeApple_3190 {
    static int N;
    static int[][] board;
    static boolean[][] snakeBody;
    static int moveCnt;
    static snake_move[] moveArr;
    static int appleCnt;
    static Dot_apple[] appleArr;

    static snake_pos turn_DL(snake_pos moveDir, char DL)
    {
        if (DL == 'D') {
            if (moveDir.x == 0 && moveDir.y == 1) {
                moveDir.x = 1;
                moveDir.y = 0;
            }
            else if (moveDir.x == 1 && moveDir.y == 0)
            {
                moveDir.x = 0;
                moveDir.y = -1;
            }
            else if (moveDir.x == 0 && moveDir.y == -1)
            {
                moveDir.x = -1;
                moveDir.y = 0;
            }
            else if (moveDir.x == -1 && moveDir.y == 0)
            {
                moveDir.x = 0;
                moveDir.y = 1;
            }
        }
        else if (DL == 'L')
        {
            if (moveDir.x == 0 && moveDir.y == 1) {
                moveDir.x = -1;
                moveDir.y = 0;
            }
            else if (moveDir.x == 1 && moveDir.y == 0)
            {
                moveDir.x = 0;
                moveDir.y = 1;
            }
            else if (moveDir.x == 0 && moveDir.y == -1)
            {
                moveDir.x = 1;
                moveDir.y = 0;
            }
            else if (moveDir.x == -1 && moveDir.y == 0)
            {
                moveDir.x = 0;
                moveDir.y = -1;
            }
        }
        return moveDir;
    }

    static void start_snake()
    {
        int time = 0;
        int checkTime = 0;
        snake_pos start = new snake_pos(1, 1);
        snake_pos moveDir = new snake_pos(0, 1);
        Queue<snake_pos> bodyStack = new LinkedList<>();
        int bodyLen = 1;

        while (true)
        {
            time++;
            // 현재위치
            snakeBody[start.x][start.y] = true;
            bodyStack.add(new snake_pos(start.x, start.y));
            // 이동
            snake_pos after = new snake_pos(0, 0);
            after.x = start.x + moveDir.x;
            after.y = start.y + moveDir.y;
            // 끝 확인 && 몸 확인
            if (!(after.x >= 1 && after.x <= N && after.y >= 1 && after.y <= N)
                || (snakeBody[after.x][after.y]))
            {
                System.out.println(time);
                return;
            }
            // 방향 전환
            if (checkTime < moveCnt && time == moveArr[checkTime].time) {
                snake_pos dir = turn_DL(moveDir, moveArr[checkTime].dir);
                moveDir.x = dir.x;
                moveDir.y = dir.y;
                checkTime++;
            }
            // 사과 있는 경우
            if (board[after.x][after.y] == 4) {
                bodyLen++;
                board[after.x][after.y] = 0;
            }
            // 현재 위치 체크 && 지나간 몸통 지우기
            snakeBody[after.x][after.y] = true;
            if (!bodyStack.isEmpty()) {
                if (bodyStack.size() >= bodyLen) {
                    snake_pos before_pos = bodyStack.poll();
                    snakeBody[before_pos.x][before_pos.y] = false;
                }
            }
            start.x = after.x;
            start.y = after.y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N+1][N+1];
        snakeBody = new boolean[N+1][N+1];
        appleCnt = Integer.parseInt(br.readLine());
        appleArr = new Dot_apple[appleCnt];
        for (int i = 0; i < appleCnt; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = 4;
        }
        moveCnt = Integer.parseInt(br.readLine());
        moveArr = new snake_move[moveCnt];
        for (int i = 0; i < moveCnt; i++)
        {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char dir =  st.nextToken().toCharArray()[0];
            moveArr[i] = new snake_move(t, dir);
        }

        start_snake();
    }
}
class snake_move
{
    int time;
    char dir;

    public snake_move(int time, char dir) {
        this.time = time;
        this.dir = dir;
    }
}

class Dot_apple
{
    int x;
    int y;

    public Dot_apple(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class snake_pos
{
    int x;
    int y;

    public snake_pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}