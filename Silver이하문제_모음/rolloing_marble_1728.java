package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class rolloing_marble_1728 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        N = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
        board = new int[N + 1][N];
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < N + 1; i++)
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++)
        {
            int start = board[0][i];
            for (int j = 0; j < N; j++) {
                int next = board[1][j];
                int vector = next - start;
                if (isValid(next, vector))
                {
                    map.put(start, vector);
                    list.add(start);
                    break;
                }
            }
        }
        Collections.sort(list);
        for (int k : list) {
            if (map.containsKey(k))
                System.out.println(k +" " +map.get(k));
        }
    }
    private static boolean isValid(int start, int vector)
    {
        int n_start = start;
        for (int i = 2; i < N + 1; i++)
        {
            for (int j = 0; j < N; j++){
                if (vector == board[i][j] - n_start) {
                    n_start = board[i][j];
                    break;
                }
                if (j == N - 1)
                    return false;
            }
        }
        return true;
    }
}
