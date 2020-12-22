import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class make_Road_14890 {
    static int[][] map;
    static int N;
    static int L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int rowCnt = find_road_row();
        int colCnt = find_road_col();
        int sum = rowCnt+colCnt;
        System.out.println(sum);
    }

    private static int find_road_row() {
        int nowHeight;
        int blockCnt;
        boolean possible_road;
        int all_row = 0;
        for (int y = 0; y < N; y++)
        {
            blockCnt = 1;
            nowHeight = map[y][0];
            possible_road = false;
            // 가로 체크
            for (int x = 1; x < N; x++)
            {
                if (map[y][x] == nowHeight) {
                    possible_road = true;
                    blockCnt++;
                }
                // 차이가 1일때
                else if (Math.abs(map[y][x] - nowHeight) == 1) {
                    // 높아진 경우
                    if (map[y][x] - nowHeight == 1) {
                        if (blockCnt < L) {
                            possible_road = false;
                            break;
                        }
                        else {
                            blockCnt = 1;
                            possible_road = true;
                        }
                    }
                    // 낮아진 경우
                    else {
                        blockCnt = 0;
                        possible_road = false;
                        for (int now = x; now < N; now++) {
                            if (map[y][now] == nowHeight - 1) {
                                blockCnt++;
                                if (blockCnt == L) {
                                    possible_road = true;
                                    blockCnt = -(L-1);
                                    break;
                                }
                            }
                            else {
                                possible_road = false;
                                blockCnt = 1;
                                break;
                            }
                        }
                        if (!possible_road)
                            break;
                    }
                    nowHeight = map[y][x];
                }
                else {
                    possible_road = false;
                    break;
                }
            }
            if (possible_road)
                all_row++;
        }
        return all_row;
    }

    private static int find_road_col() {
        int nowHeight;
        int blockCnt;
        boolean possible_road;
        int all_col = 0;
        for (int x = 0; x < N; x++)
        {
            blockCnt = 1;
            nowHeight = map[0][x];
            possible_road = false;
            // 가로 체크
            for (int y = 1; y < N; y++)
            {
                if (map[y][x] == nowHeight) {
                    possible_road = true;
                    blockCnt++;
                }
                // 차이가 1일때
                else if (Math.abs(map[y][x] - nowHeight) == 1) {
                    // 높아진 경우
                    if (map[y][x] - nowHeight == 1) {
                        if (blockCnt < L) {
                            possible_road = false;
                            break;
                        }
                        else {
                            blockCnt = 1;
                            possible_road = true;
                        }
                    }
                    // 낮아진 경우
                    else {
                        blockCnt = 0;
                        possible_road = false;
                        for (int now = y; now < N; now++) {
                            if (map[now][x] == nowHeight - 1) {
                                blockCnt++;
                                if (blockCnt == L) {
                                    possible_road = true;
                                    blockCnt = -(L-1);
                                    break;
                                }
                            }
                            else {
                                possible_road = false;
                                blockCnt = 1;
                                break;
                            }
                        }
                        if (!possible_road)
                            break;
                    }
                    nowHeight = map[y][x];
                }
                else {
                    possible_road = false;
                    break;
                }
            }
            if (possible_road)
                all_col++;
        }
        return all_col;
    }
}
