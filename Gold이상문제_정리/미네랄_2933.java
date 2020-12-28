import java.util.*;
import java.io.*;

public class 미네랄_2933 {
    static char[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] visited;
    static int[] stickArr;
    static int row, col, N;
    static boolean isDown;
    static boolean[][] down_mineral;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char[row][col];

        for (int i = 0; i < row; i++) {
            String str = br.readLine();
            for (int j = 0; j < col; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        N = Integer.parseInt(br.readLine());
        stickArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Get_cluster();
    }

    private static void Get_cluster() {
        boolean isLeft = true;
        for (int turn = 0; turn < N; turn++)
        {
            visited = new boolean[row][col];
            down_mineral = new boolean[row][col];
            isDown = false;

            //좌우 막대던지기
            int stick = row - stickArr[turn];
            if (isLeft) {
                for (int x = 0; x < col; x++) {
                    if (map[stick][x] == 'x') {
                        map[stick][x] = '.';
                        break;
                    }
                }
            }
            else {
                for (int x = col - 1; x >= 0; x--) {
                    if (map[stick][x] == 'x') {
                        map[stick][x] = '.';
                        break;
                    }
                }
            }
            isLeft = !isLeft;

            // 미네랄을 발견하면 체크 후 DFS를 통해 바닥과 붙어있는지 확인 -> 떨어지거나 안떨어지거나
            for (int y = 0; y < row; y++) {
                for (int x = 0; x < col; x++) {
                    if(map[y][x] == 'x' && !visited[y][x]) {
                        isDown = true;
                        DFS(y, x);
                        if (isDown) {
                            visited = new boolean[row][col];
                            down_cluster(y, x);
                            break;
                        }
                    }
                }
                if (isDown)
                    break;
            }

            // 미네랄 낙하
            if (isDown)
            {
                int count = 1000;
                int diff = 1000;
                for (int x = 0; x < col; x++) {
                    for (int y = row - 1; y >= 0; y--) {
                        if (down_mineral[y][x]) {
                            int nh = y;
                            while (++nh < row) {
                                if (map[nh][x] == '.')
                                    diff = nh - y;
                                else
                                    break;
                            }
                            break;
                        }
                    }
                    count = Math.min(diff, count);
                }

                for (int y = row - 1; y >= 0; y--) {
                    for (int x = 0; x < col; x++) {
                        if (down_mineral[y][x]) {
                            map[y + count][x] = 'x';
                            map[y][x] = '.';
                        }
                    }
                }
            }
        }

        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                System.out.print(map[y][x]);
            }
            System.out.println();
        }
    }

    private static void down_cluster(int y, int x) {
        visited[y][x] = true;
        down_mineral[y][x] = true;

        for (int d = 0; d < 4; d++)
        {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny >= 0 && ny < row && nx >= 0 && nx < col) {
                if (map[ny][nx] == 'x' && !visited[ny][nx])
                    down_cluster(ny, nx);
            }
        }
    }

    private static void DFS(int y, int x) {
        visited[y][x] = true;

        for (int d = 0; d < 4; d++)
        {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny >= 0 && ny < row && nx >= 0 && nx < col) {
                if (map[ny][nx] == 'x' && !visited[ny][nx]) {
                    if (ny == row - 1)
                        isDown = false;
                    DFS(ny, nx);
                }
            }
        }
    }
}
