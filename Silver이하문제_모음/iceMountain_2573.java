package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class iceMountain_2573 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int H, W, Y;
    static int[][] map;
    static int[][] copyMap;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static boolean[][] visited;
    static boolean notYet, over, ice;
    public static void main(String[] args) throws IOException
    {
        int n[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        H = n[0];
        W = n[1];
        map = new int[H][W];
        copyMap = new int[H][W];
        for (int i = 0; i < H; i++)
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        while (true)
        {
            //빙하에 대해 체크
            visited = new boolean[H][W];
            notYet = true;
            ice = false;
            check();
            //맵을 복사한다
            copyMap(map, copyMap);
            //1년마다 빙하가 줄음, Y증가
            passYear();
            //변경된 맵을 다시 맵에 준다
            copyMap(copyMap, map);
            if (over)
                break;
            if (!ice) {
                System.out.println(0);
                break;
            }
        }
    }
    private static void copyMap(int[][] origin, int[][] copy)
    {
        for (int i = 0; i < H; i++)
            System.arraycopy(origin[i], 0, copy[i], 0, W);
    }

    private static void passYear()
    {
        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                if (map[h][w] > 0) {
                    int count = 0;
                    for (int i = 0; i < 4; i++) {
                        int x = h + dx[i];
                        int y = w + dy[i];
                        if (map[x][y] == 0)
                            count++;
                    }
                    if (map[h][w] >= count)
                        copyMap[h][w] -= count;
                    else
                        copyMap[h][w] = 0;
                }
            }
        }
        Y++;
    }
    private static void check()
    {
        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++)
            {
                if (map[h][w] > 0)
                    ice = true;
                //빙산이고, 아직 방문하지 않았다면
                if (map[h][w] > 0 && !visited[h][w] && notYet){
                    //방문 가능한 모든 빙산을 방문하고 방문하지 않은 빙산이 나올경우 두개로 분리된 된것
                    DFS(new Dot(h, w));
                    notYet = false;
                }
                //이미 dfs가 돌았고 && 맵에 빙산이 있고 && 방문을 안했다면
                else if (!notYet && map[h][w] > 0 && !visited[h][w])
                {
                    System.out.println(Y);
                    over = true;
                    return;
                }
            }
        }
        //반복문을 전부 돌았는데 return이 안됐다면 방문을 했지만 빙산은 있었던것 == 쪼개지지 않음
        //System.out.println(0);
        //over = true;
        //return;
    }
    private static void DFS(Dot dot)
    {
        visited[dot.x][dot.y] = true;
        for (int i = 0; i < 4; i++)
        {
            int x = dot.x + dx[i];
            int y = dot.y + dy[i];
            if (x >= 0 && y >= 0 && x < H && y < W) {
                if (map[x][y] > 0 && !visited[x][y]) {
                    DFS(new Dot(x, y));
                }
            }
        }
        return;
    }
}
