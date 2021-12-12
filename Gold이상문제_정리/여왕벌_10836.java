package Gold이상문제_정리;

/*
4 2
2 3 2
0 6 1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 여왕벌_10836 {
    static int N;
    static int D;
    static int[][] map;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], 1);
        }

        for (int i = 0; i < D; i++) {
            st = new StringTokenizer(br.readLine());
            Dot start = new Dot(N - 1, 0);
            int zero = Integer.parseInt(st.nextToken());
            Dot first = init(start, 0, zero);
            int one = Integer.parseInt(st.nextToken());
            Dot second = init(first, 1, one);
            int two = Integer.parseInt(st.nextToken());
            init(second, 2, two);
        }
        print();
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j == 0) {
                    answer.append(map[i][j]).append(" ");
                }  else answer.append(map[0][j]).append(" ");
            }
            answer.append("\n");
        }
        System.out.print(answer);
    }

    private static Dot init(Dot start, int type, int count) {
        // 왼쪽 열
        if (start.y >= 0 && count <= start.y) {
            while (count-- > 0) {
                map[start.y--][0] += type;
            }
            return new Dot(start.y, start.x);
        }
        // 왼쪽 열 + 오른쪽 행
        if (start.y >= 0) {
            while (start.y > 0){
                map[start.y--][0] += type;
                count--;
            }
            for (int i = 0; i < count; i++) {
                map[0][start.x++] += type;
            }
            return new Dot(start.y, start.x);
        }
        // 오른쪽 행
        while (count-- > 0) {
            map[0][start.x++] += type;
        }
        return new Dot(start.y, start.x);
    }

    private static class Dot {
        int y, x;

        public Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
