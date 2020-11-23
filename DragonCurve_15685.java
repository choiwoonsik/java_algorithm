import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DragonCurve_15685 {
    static int N;
    static boolean[][] map = new boolean[101][101];
    static int[] dirArr = {0, 1, 2, 3};
    static Queue<Integer> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int dragon = 0; dragon < N; dragon++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int gen = Integer.parseInt(st.nextToken());
            DragonPos D_pos = new DragonPos(x, y);
            make_DragonRoad(D_pos, dir, gen);
        }
        count_box();
    }

    private static void make_DragonRoad(DragonPos dPos, int dir, int gen) {
        queue = new LinkedList<>();
        queue.add(dir);
        DFS(0, gen);
        road_build(dPos);
    }

    private static void count_box() {
        int count = 0;
        for (int y = 0; y < 100; y++)
        {
            for (int x = 0; x < 100; x++)
            {
                if (map[y][x] && map[y][x + 1] && map[y+1][x] && map[y+1][x+1])
                    count++;
            }
        }
        System.out.println(count);
    }

    private static void road_build(DragonPos dPos) {
        map[dPos.y][dPos.x] = true;
        for (int dir : queue)
        {
            // 오른쪽
            if (dir == 0)
                dPos.x += 1;
            // 위
            else if (dir == 1)
                dPos.y -= 1;
            // 왼쪽
            else if (dir == 2)
                dPos.x -= 1;
            // 아래쪽
            else if (dir == 3)
                dPos.y += 1;
            map[dPos.y][dPos.x] = true;
        }
    }

    private static void DFS(int generation, int max_gen) {
        if (generation >= max_gen)
            return;

        Deque<Integer> reverseQueue = new LinkedList<>();
        for (Integer integer : queue) {
            int nextDir = dirArr[(integer + 1) % 4];
            reverseQueue.add(nextDir);
        }
        while (!reverseQueue.isEmpty())
            queue.add(reverseQueue.pollLast());
        DFS(generation + 1, max_gen);
    }
}
class DragonPos
{
    int x;
    int y;

    public DragonPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
