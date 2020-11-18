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

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        find_road_row();
        find_road_col();
    }

    private static void find_road_row() {
        int nowHeight = 0;
        int blockCnt = 0;
        for (int y = 0; y < N; y++)
        {
            nowHeight = map[y][0];
            for (int x = 1; x < N; x++)
            {
                if (map[y][x] > nowHeight) break;
                else
                {
                    if (map[y][x] == nowHeight)

                    if (map[y][x] < nowHeight && Math.abs(map[y][x] - nowHeight) == 1)
                    {
                        // 높아진 경우
                        if (map[y][x] - nowHeight == -1)
                        {

                        }
                    }
                    else
                        break;
                }
            }
        }
    }

    private static void find_road_col() {
    }
}
