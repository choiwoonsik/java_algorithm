import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class goodBye_Microdust_17144 {
    static int R;
    static int C;
    static int[][] map;
    static int[][] copyedMap;
    static int[] d_r = {-1, 1, 0, 0};
    static int[] d_c = {0, 0, -1, 1};
    static int T;
    static Pos_air[] Air = new Pos_air[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        boolean isFirst = true;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                if (map[i][j] == -1 && isFirst) {
                    Air[0] = new Pos_air(i, j);
                    isFirst = false;
                }
                else if (map[i][j] == -1 && !isFirst)
                    Air[1] = new Pos_air(i, j);
            }
        }

        while (T-- > 0)
        {
            spread_dust();
            blow_air();
        }
        calc();

    }

    private static void calc() {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0)
                    sum += map[i][j];
            }
        }
        System.out.println(sum);
    }

    private static void blow_air() {
        boolean isFirst = true;
        int Air_r;
        int Air_c;
        for (Pos_air pos_air : Air) {
            Air_r = pos_air.r;
            Air_c = pos_air.c;
            if (isFirst) {
                isFirst = false;
                // 청정기 왼쪽 위 내려옴
                for (int r = Air_r - 1; r >= 0; r--)
                    map[r + 1][0] = map[r][0];
                // 맨 위 왼쪽으로 이동
                for (int c = 1; c < C; c++)
                    map[0][c - 1] = map[0][c];
                // 맨 오른쪽 위로 이동
                for (int r = 1; r <= Air_r; r++)
                    map[r - 1][C - 1] = map[r][C - 1];
            }
            else {
                // 청정기 왼쪽 아래 올라옴
                for (int r = Air_r + 1; r < R; r++)
                    map[r - 1][0] = map[r][0];
                // 맨 아래 왼쪽으로 이동
                for (int c = 1; c < C; c++)
                    map[R - 1][c - 1] = map[R - 1][c];
                // 맨 오른쪽 아래로 이동
                for (int r = R - 1; r > Air_r; r--)
                    map[r][C - 1] = map[r - 1][C - 1];
            }
            // 청정기 오른쪽 밀어냄
            for (int c = C - 1; c > Air_c; c--)
                map[Air_r][c] = map[Air_r][c - 1];
            map[Air_r][Air_c + 1] = 0;
            map[Air_r][0] = -1;
        }
    }

    private static void spread_dust() {
        // 원본에서 줄이고 // 사본에서는 늘리고 // 합치기?
        copyedMap = new int[R][C];
        int spread;
        int count_dir;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 먼지가 존재한다면 뿌리기
                if (map[i][j] > 0) {
                    count_dir = 0;
                    spread = map[i][j] / 5;
                    // 4방위 확인
                    for (int dir = 0; dir < 4; dir++) {
                        int dr = d_r[dir] + i;
                        int dc = d_c[dir] + j;
                        if (dr >= 0 && dr < R && dc >= 0 && dc < C) {
                            if (map[dr][dc] >= 0) {
                                count_dir++;
                                copyedMap[dr][dc] += spread;
                            }
                        }
                    }
                    // 뿌린만큼 빼주기
                    map[i][j] -= spread * count_dir;
                }
            }
        }
        sum_Maps();
    }

    private static void sum_Maps() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += copyedMap[i][j];
            }
        }
    }
}

class Pos_air
{
    int r;
    int c;
    public Pos_air(int r, int c) {
        this.r = r;
        this.c = c;
    }
}