package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 배열돌리기4_17406 {
    static int[][] map;
    static int[][] subMap;
    static int N, M, K;
    static Rota[] rotaArr;
    static List<Rota> list = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        subMap = new int[N + 1][M + 1];

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                subMap[r][c] = map[r][c];
            }
        }

        rotaArr = new Rota[K];
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int kk = Integer.parseInt(st.nextToken());
            rotaArr[k] = new Rota(r, c, kk);
        }

        // rotaArr에 대한 조합 만들기
        boolean[] visited = new boolean[K];
        Rota[] output = new Rota[K];
        rota_permutation(output, visited, 0, rotaArr.length, K);

        int roCount = 0;
        int Min = 987654321;
        for (Rota rota : list) {
            // 새로운 회전 조합
            // 현재 배열의 합을 Min 과 비교해서 넣기
            if (roCount == K) {
                roCount = 0;
                int min = SumOfMap();
                Min = Math.min(min, Min);
                for (int y = 1; y <= N; y++)
                    subMap[y] = map[y].clone();
            }
            // 회전하기
            int[][] copyMap = rotationMap(rota);

            // 복사하기
            copy(copyMap);

            roCount++;
        }

        System.out.println(Min);
    }

    private static int SumOfMap() {
        int Min = 987654321;
        for (int y = 1; y <= N; y++)
            Min = Math.min(Min, Arrays.stream(subMap[y]).sum());
        return Min;
    }

    private static void copy(int[][] copyMap) {
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= M; x++) {
                if (copyMap[y][x] > 0)
                    subMap[y][x] = copyMap[y][x];
            }
        }
    }

    private static int[][] rotationMap(Rota rota) {
        int startLeftY = rota.r - rota.s;
        int startLeftX = rota.c - rota.s;

        int endRightY = rota.r + rota.s;
        int endRightX = rota.c + rota.s;

        int[][] copyMap = new int[N + 1][M + 1];

        for (int s = 0; s < rota.s; s++)
        {
            // 위
            for (int x = startLeftX; x < endRightX; x++)
                copyMap[startLeftY][x + 1] = subMap[startLeftY][x];
            // 오른쪽
            for (int y = startLeftY; y < endRightY; y++)
                copyMap[y + 1][endRightX] = subMap[y][endRightX];
            // 아래
            for (int x = endRightX; x > startLeftX; x--)
                copyMap[endRightY][x - 1] = subMap[endRightY][x];
            // 왼쪽
            for (int y = endRightY; y > startLeftY; y--) {
                copyMap[y - 1][startLeftX] = subMap[y][startLeftX];
            }
            startLeftY += 1;
            startLeftX += 1;
            endRightY -= 1;
            endRightX -= 1;
        }
        return copyMap;
    }

    private static void rota_permutation(Rota[] output, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            list.addAll(Arrays.asList(output));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = rotaArr[i];
                rota_permutation(output, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }

    private static class Rota
    {
        int r;
        int c;
        int s;

        public Rota(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}
