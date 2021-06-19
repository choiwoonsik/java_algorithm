package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bacons_sixLevel_rule_1389 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[][] map;
    static boolean[] visited;
    static int N, M, sum;
    public static void main(String[] args) throws IOException
    {
        ArrayList<Dot> list = new ArrayList<>();
        int n[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = n[0];
        M = n[1];
        map = new boolean[N+1][N+1];
        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = true;
            map[y][x] = true;
        }
        for (int i = 1 ; i <= N; i++)
        {
            sum = 0;
            visited = new boolean[N+1];
            BFS(i, 1);
            list.add(new Dot(sum, i));
        }
        list.sort(new Comparator<Dot>() {
            @Override
            public int compare(Dot o1, Dot o2) {
                if (o1.x < o2.x)
                    return -1;
                else if (o1.x == o2.x) {
                    if (o1.y < o2.y)
                        return -1;
                    else
                        return 1;
                }
                else
                    return 1;
            }
        });
        System.out.println(list.get(0).y);
    }
    private static void BFS(int me, int depth)
    {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(me);
        int cnt;
        int n = queue.size();
        while (!queue.isEmpty()) {
            boolean next = false;
            cnt = 0;
            for (int k = 0; k < n; k++) {
                int who = queue.poll();
                for (int i = 1; i <= N; i++) {
                    if (map[who][i] == true && !visited[i] && i != me) {
                        sum += depth;
                        visited[i] = true;
                        queue.add(i);
                        next = true;
                        cnt++;
                    }
                }
            }
            n = cnt;
            if (next) {
                depth++;
            }
        }
    }
}
