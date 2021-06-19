package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCTV_15683 {
    static int[] cctv_rotArr = {4, 2, 4, 4, 1};
    static int[][] map;
    static CCTV[] cctvArr = new CCTV[8];
    static int cctvCnt;
    static int answer;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] != 6 && map[y][x] > 0)
                    cctvArr[cctvCnt++] = new CCTV(y, x, map[y][x] - 1);
            }
        }
        answer = 100;
        DFS(0);
        System.out.println(answer);
    }

    private static void DFS(int cctv_idx) {
        if (cctv_idx >= cctvCnt){
            answer = check_unchecked();
            return;
        }
        int type = cctvArr[cctv_idx].type;
        int[][] backUpMap = new int[N][M];
        for (int dir = 0; dir < cctv_rotArr[type]; dir++) {
            // 맵 백업
            copy_map(backUpMap, map);
            // 각 cctv  별 로 맵 확인
            if (type == 0)
                check_map(dir, cctvArr[cctv_idx]);
            else if (type == 1)
            {
                check_map(dir, cctvArr[cctv_idx]);
                check_map(dir + 2, cctvArr[cctv_idx]);
            }
            else if (type == 2)
            {
                check_map(dir, cctvArr[cctv_idx]);
                check_map(dir + 1, cctvArr[cctv_idx]);
            }
            else if (type == 3)
            {
                check_map(dir, cctvArr[cctv_idx]);
                check_map(dir + 2, cctvArr[cctv_idx]);
                check_map(dir + 3, cctvArr[cctv_idx]);
            }
            else if (type == 4)
            {
                check_map(dir, cctvArr[cctv_idx]);
                check_map(dir + 1, cctvArr[cctv_idx]);
                check_map(dir + 2, cctvArr[cctv_idx]);
                check_map(dir + 3, cctvArr[cctv_idx]);
            }
            // 다음 cctv;
            DFS(cctv_idx + 1);
            // 맵 복구
            copy_map(map, backUpMap);
        }
    }

    private static int check_unchecked() {
        int unchecked = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0)
                    unchecked++;
            }
        }
        return Math.min(answer, unchecked);
    }

    private static void copy_map(int[][] dest, int[][] scrs) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dest[i][j] = scrs[i][j];
            }
        }
    }

    private static void check_map(int dir, CCTV cctv) {
        // cctv 타입별 맵 확인하기
        dir = dir % 4;

        // 동
        if (dir == 0)
        {
            for (int x = cctv.x; x < M; x++) {
                if (map[cctv.y][x] == 6) break;
                else
                    map[cctv.y][x] = -1;
            }
        }
        // 남
        else if (dir == 1)
        {
            for (int y = cctv.y; y < N; y++){
                if (map[y][cctv.x] == 6) break;
                else
                    map[y][cctv.x] = -1;
            }
        }
        // 서
        else if (dir == 2)
        {
            for (int x = cctv.x; x >= 0; x--) {
                if (map[cctv.y][x] == 6) break;
                else
                    map[cctv.y][x] = -1;
            }
        }
        // 북
        else if (dir == 3)
        {
            for (int y = cctv.y; y >= 0; y--){
                if (map[y][cctv.x] == 6) break;
                else
                    map[y][cctv.x] = -1;
            }
        }
    }
}
class CCTV
{
    int y;
    int x;
    int type;

    public CCTV(int y, int x, int type) {
        this.y = y;
        this.x = x;
        this.type = type;
    }
}