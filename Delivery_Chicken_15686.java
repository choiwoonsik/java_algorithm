import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Delivery_Chicken_15686 {
    static Dot_chicken[]home = new Dot_chicken[100];
    static Dot_chicken[]chicken = new Dot_chicken[100];
    static int homeCnt;
    static int chickenCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int place = Integer.parseInt(st.nextToken());
                if (place == 1)
                    home[homeCnt++] = new Dot_chicken(i, j);
                else if (place == 2)
                    chicken[chickenCnt++] = new Dot_chicken(i, j);
            }
        }
        System.out.println(solve(M));
    }

    private static int solve(int m) {
        // 부분집합의 개수 만큼
        int ret = Integer.MAX_VALUE;
        for (int subset = 0; subset < 1 << chickenCnt; subset++) {
            // 비트 수가 원하는 치킨집의 개수라면
            if (countBit(subset) == m) {
                int sum = 0;
                for (int house = 0; house < homeCnt; house++) {
                    int Min = Integer.MAX_VALUE;
                    // 각 집에 대해서 모든 치킨집들의 거리 파악
                    for (int chick = 0; chick < chickenCnt; chick++) {
                        if ((subset & 1 << chick) != 0) {
                            Min = Math.min(Min, Math.abs(home[house].x - chicken[chick].x)
                                                + Math.abs(home[house].y - chicken[chick].y));
                        }
                    }
                    sum += Min;
                }
                // 각 치킨집 조합들의 거리들의 합에 대한 비교
                ret = Math.min(sum, ret);
            }
        }
        return ret;
    }

    private static int countBit(int n) {
        int cnt = 0;
        while (n > 0)
        {
            if ((n & 1) == 1) cnt++;
            n = n >> 1;
        }
        return  cnt;
    }
}
class Dot_chicken
{
    int x;
    int y;
    public Dot_chicken(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
