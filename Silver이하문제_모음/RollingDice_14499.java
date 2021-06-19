package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RollingDice_14499 {
    static int[][] board;
    static int orderCnt;
    static Dot_Dice start;
    static int[] Dice = new int[7];
    static int[] CopyDice = new int[7];
    static int[] orders;
    static StringBuilder ret = new StringBuilder();

    static void copyBoardDice()
    {
        if(board[start.x][start.y] == 0)
            board[start.x][start.y] = Dice[4];
        else
        {
            Dice[4] = board[start.x][start.y];
            board[start.x][start.y] = 0;
        }
    }
    static void startGame()
    {
        // 처음 시작
        //copyBoardDice();
        for (int game = 0; game < orderCnt; game++)
        {
            // 동쪽
            if (orders[game] == 1)
            {
                start.y += 1;
                // 범위 내
                if (start.y < board[0].length)
                {
                    // 주사위 굴리기
                    CopyDice[1] = Dice[1];
                    CopyDice[2] = Dice[5];
                    CopyDice[3] = Dice[3];
                    CopyDice[4] = Dice[6];
                    CopyDice[5] = Dice[4];
                    CopyDice[6] = Dice[2];
                    Dice = CopyDice.clone();
                    copyBoardDice();
                    ret.append(Dice[2]).append("\n");
                }
                else
                    start.y -= 1;
            }
            // 서쪽
            else if (orders[game] == 2)
            {
                start.y -= 1;
                if (start.y >= 0)
                {
                    CopyDice[1] = Dice[1];
                    CopyDice[2] = Dice[6];
                    CopyDice[3] = Dice[3];
                    CopyDice[4] = Dice[5];
                    CopyDice[5] = Dice[2];
                    CopyDice[6] = Dice[4];
                    Dice = CopyDice.clone();
                    copyBoardDice();
                    ret.append(Dice[2]).append("\n");
                }
                else
                    start.y += 1;
            }
            // 남쪽
            else if (orders[game] == 4)
            {
                start.x += 1;
                if (start.x < board.length)
                {
                    CopyDice[1] = Dice[2];
                    CopyDice[2] = Dice[3];
                    CopyDice[3] = Dice[4];
                    CopyDice[4] = Dice[1];
                    CopyDice[5] = Dice[5];
                    CopyDice[6] = Dice[6];
                    Dice = CopyDice.clone();
                    copyBoardDice();
                    ret.append(Dice[2]).append("\n");
                }
                else
                    start.x -= 1;
            }
            // 북쪽
            else if (orders[game] == 3)
            {
                start.x -= 1;
                if (start.x >= 0)
                {
                    CopyDice[1] = Dice[4];
                    CopyDice[2] = Dice[1];
                    CopyDice[3] = Dice[2];
                    CopyDice[4] = Dice[3];
                    CopyDice[5] = Dice[5];
                    CopyDice[6] = Dice[6];
                    Dice = CopyDice.clone();
                    copyBoardDice();
                    ret.append(Dice[2]).append("\n");
                }
                else
                    start.x += 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        start = new Dot_Dice(x, y);
        orderCnt = Integer.parseInt(st.nextToken());
        // 보드 채우기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        // 명령어 채우기
        orders = new int[orderCnt];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < orderCnt; i++)
            orders[i] = Integer.parseInt(st.nextToken());

        startGame();
        System.out.println(ret);
    }
}

class Dot_Dice
{
    int x;
    int y;
    public Dot_Dice(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
