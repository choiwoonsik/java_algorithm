package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class find_person_1697 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Queue<dot_info> queue = new LinkedList<>();
    static int MAX = 100001;
    static boolean visited[] = new boolean[MAX];
    public static void main(String[] args) throws IOException {
        int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int S = n[0];
        int E = n[1];
        BFS(S, E);
    }
    private static void BFS(int S, int E)
    {
        queue.add(new dot_info(S, 0));
        while(!queue.isEmpty()) {
            dot_info now = queue.poll();
            visited[now.x] = true;
            if (now.x == E) {
                System.out.print(now.count);
                break;
            }
            int[] go = {now.x + 1, now.x - 1, now.x * 2};
            for (int i = 0; i < go.length; i++) {
                if (go[i] >= 0 && go[i] < MAX && !visited[go[i]])
                    queue.add(new dot_info(go[i], now.count + 1));
            }
        }
    }
}
class dot_info
{
    int x;
    int count;
    dot_info (int x, int count)
    {
        this.x = x;
        this.count = count;
    }
}
