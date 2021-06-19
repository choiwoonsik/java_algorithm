package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N_Queen_2_9663 {
    static int[] board;
    static int N, count;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            board = new int[N + 1];
            board[0] = i;
            recursive(0);
        }
        System.out.println(count);
    }
    private static void recursive(int garo)
    {
        if (garo == N - 1)
            count++;
        else {
            for (int i = 0; i < N; i++) {
                board[garo + 1] = i;
                if (isValid(garo + 1))
                    recursive(garo + 1);
                else
                    board[garo + 1] = 0;
            }
        }
        board[garo] = 0;
    }
    private static boolean isValid(int garo)
    {
        for (int i = 0; i < garo; i++) {
            if (board[i] == board[garo] || board[garo] + (garo - i) == board[i] || board[garo] - (garo - i) == board[i])
                return false;
        }
        return true;
    }
}
