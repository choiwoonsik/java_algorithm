package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class rotto_6603 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static boolean[] visited;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        while (true) {
            nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            N = nums[0];
            if (N == 0)
                break;
            else {
                visited = new boolean[N + 1];
                DFS(1, 0);
                System.out.println();
            }
        }
        return ;
    }
    private static void DFS(int pos, int depth)
    {
        if (depth == 6)
        {
            for (int i = 1; i <= N; i++) {
                if (visited[i])
                    System.out.print(nums[i]+" ");
            }
            System.out.println();
            return;
        }
        for (int i = pos; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                DFS(i + 1, depth + 1);
                visited[i] = false;
            }
        }
    }
}