package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NandM_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int arr[];
    static boolean visited[];
    public static void main(String[] args) throws IOException
    {
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = tmp[0];
        int M = tmp[1];
        arr = new int[N + 1];
        visited = new boolean[N + 1];
        recursive(0,1, N, M);
    }
    private static void recursive(int depth, int start, int N, int M)
    {
        if (depth == M)
        {
            for (int i = 0; i < M; i++)
                System.out.print(arr[i] +" ");
            System.out.println();
            return;
        }
        for (int i = start; i <= N; i++)
        {
            if (!visited[i])
            {
                visited[i] = true;
                arr[depth] = start++;
                recursive(depth + 1, start, N, M);
                visited[i] = false;
            }
        }
    }
}
