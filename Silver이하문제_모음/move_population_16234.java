package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class move_population_16234 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int sum, sum_count, nextCnt;
    static int N, L, R;
    static boolean possible;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[][] map;
    static int[][] copyMap;
    static boolean[][] visited;
    static boolean[][] opened;
    static Queue<Dot> queue = new LinkedList<>();
    static ArrayList<Dot> list = new ArrayList<>();
    static ArrayList<Dot_this> onlyThis = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        copyMap = new int[N][N];
        visited = new boolean[N][N];
        opened = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                copyMap[i][j] = tmp;
            }
        }
        int count = 0;
        while(true) {
            possible = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        DFS(new Dot(i, j));
                    }
                }
            }
            if (!possible)
                break;
            count++;
            moveAll();
        }
        System.out.println(count);
    }
    private static void DFS(Dot country) {
        queue.add(country);
        sum = map[country.x][country.y];
        sum_count = 1;
        visited[country.x][country.y] = true;
        while (!queue.isEmpty()) {
            Dot now = queue.poll();
            for (int d = 0; d < 4; d++) {
                int x = now.x + dx[d];
                int y = now.y + dy[d];
                if (x >= 0 && y >= 0 && x < N && y < N) {
                    if (Math.abs(map[now.x][now.y] - map[x][y]) <= R
                            && Math.abs(map[now.x][now.y] - map[x][y]) >= L) {
                        if (!visited[x][y]) {
                            possible = true;
                            visited[x][y] = true;
                            sum += map[x][y];
                            sum_count++;
                            queue.add(new Dot(x, y));
                            list.add(new Dot(x, y));
                            if (!list.contains(now))
                                list.add(now);
                        }
                    }
                }
            }
        }
        move();
    }

    private static void move()
    {
        if (sum_count > 0) {
            sum /= sum_count;
            while (!list.isEmpty()) {
                Dot diff = list.get(0);
                onlyThis.add(new Dot_this(diff.x, diff.y, sum));
                list.remove(0);
            }
        }
    }

    private static void moveAll()
    {
        while (!onlyThis.isEmpty())
        {
            Dot_this diff = onlyThis.get(0);
            onlyThis.remove(0);
            map[diff.x][diff.y] = diff.value;
        }
    }
}

class Dot_this
{
    int x;
    int y;
    int value;

    Dot_this (int x, int y, int value)
    {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}