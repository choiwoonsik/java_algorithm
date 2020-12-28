package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 원판돌리기_17822 {
    static int[][] circle;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static boolean[][] visited;
    static int N, M, T;
    static boolean find;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        circle = new int[N + 1][M];

        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                circle[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            visited = new boolean[N + 1][M];

            // 방향 회전
            for (int roX = x; roX <= N; roX += x)
            {
                int[] tmp = new int[M];
                if (d == 0) {
                    for (int num = 0; num < M; num++)
                        tmp[(num + k) % M] = circle[roX][num];
                }
                else {
                    for (int num = 0; num < M; num++)
                        tmp[(num + (M - k)) % M] = circle[roX][num];
                }
                circle[roX] = tmp.clone();
            }

            // 원판에 수가 존재한다면 인접한거 찾기
            // 인접한게 있으면 다 지우고 , 없으면 -> 평균보다 큰수에서는 1빼고 작은수에서는 1 더한다
            find = false;
            for (int roX = 1; roX <= N; roX++) {
                for (int num = 0; num < M; num++) {
                    if (circle[roX][num] > 0 && !visited[roX][num]) {
                        dfs(roX, num, circle[roX][num]);
                    }
                }
            }

            if (!find) {
                double sum = 0;
                double count = 0;
                for (int y = 1; y <= N; y++) {
                    for (int w = 0; w < M; w++) {
                        if (circle[y][w] > 0) {
                            sum += circle[y][w];
                            count += 1;
                        }
                    }
                }
                if (count > 0) {
                    double avg = sum / count;
                    for (int y = 1; y <= N; y++) {
                        for (int w = 0; w < M; w++) {
                            if (circle[y][w] < avg && circle[y][w] != 0)
                                circle[y][w] += 1;
                            else if (circle[y][w] > avg)
                                circle[y][w] -= 1;
                        }
                    }
                }
            }
        }
        int sum = 0;
        for (int y = 1; y <= N; y++) {
            for (int w = 0; w < M; w++) {
                if (circle[y][w] > 0) {
                    sum += circle[y][w];
                }
            }
        }
        System.out.println(sum);
    }

    private static void dfs(int roX, int num, int origin) {
        visited[roX][num] = true;

        for (int d = 0; d < 4; d++) {
            int ny = roX + dy[d];
            int nx = num + dx[d];
            if (nx == -1)
                nx = M - 1;
            else if (nx == M)
                nx = 0;
            if (ny >= 1 && ny <= N && nx >= 0 && nx < M) {
                if (!visited[ny][nx] && origin == circle[ny][nx]) {
                    circle[ny][nx] = 0;
                    circle[roX][num] = 0;
                    find = true;
                    dfs(ny, nx, origin);
                }
            }
        }
    }
}
