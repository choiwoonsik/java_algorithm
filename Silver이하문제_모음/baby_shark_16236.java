package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baby_shark_16236 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] map;
    static boolean[][] visited;
    static List<Dot_count1> fishQ = new ArrayList<>();
    static Dot shark;
    static int N;
    static int baby, eaten;
    static boolean possible;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        int fishN = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Dot(i, j);
                    map[i][j] = 0;
                }
                else if (map[i][j] > 0)
                    fishN++;
            }
        }
        baby = 2;
        if (fishN == 0)
            System.out.println(0);
        else
            System.out.println(go());
    }
    //먼저 상어 몸무게 보다 작은 물고기를 bfs를 통해 찾는다
    //찾은 물고기들의 좌표를 비교하여 가깝고 같다면 위에서 왼쪽부터 먹는다
    private static int go()
    {
        int count = 0;
        while(true) {
            find();
            if(!possible)
                break;
            //찾은 첫번째 물고기로 한번만 이동
            Dot_count1 fish = fishQ.get(0);
            fishQ.remove(0);
            map[fish.x][fish.y] = 0;
            shark.x = fish.x;
            shark.y = fish.y;
            count += fish.count;
            eaten++;
            if (eaten == baby) {
                baby++;
                eaten = 0;
            }
        }
        return count;
    }
    private static void find()
    {
        Queue<Dot_count> queue = new LinkedList<>();
        possible = false;
        fishQ = new ArrayList<>();
        visited = new boolean[N][N];
        queue.add(new Dot_count(shark.x, shark.y, 0));

        while (!queue.isEmpty()) {
            Dot_count now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = now.x + dx[i];
                int y = now.y + dy[i];
                int move = now.count + 1;
                if (x >= 0 && y >= 0 && x < N && y < N) {
                    if (map[x][y] <= baby && !visited[x][y]) {
                        //먹이 발견
                        if (map[x][y] < baby && map[x][y] != 0) {
                            possible = true;
                            fishQ.add(new Dot_count1(x, y, move));
                        }
                        visited[x][y] = true;
                        queue.add(new Dot_count(x, y, move));
                    }
                }
            }
        }
        Collections.sort(fishQ);
    }
}

class Dot_count1 implements Comparable<Dot_count1>
{
    int x;
    int y;
    int count;
    Dot_count1 (int x, int y, int count)
    {
        this.x = x;
        this.y = y;
        this.count = count;
    }

    @Override
    public int compareTo(Dot_count1 o) {
        //거리가 가깝다면 먼저
        if (this.count < o.count)
            return -1;
        //거리가 같다면
        else if (this.count == o.count){
            //위에있는거
            if (this.x < o.x)
                return -1;
            else if (this.x == o.x) {
                //그중에서도 왼쪽
                if (this.y < o.y)
                    return -1;
            } else
                return 1;
        }
        else
            return 1;
        return 0;
    }
}

