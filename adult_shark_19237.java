import java.io.*;
import java.util.*;

public class adult_shark_19237 {
    static int N, M, K;
    static int[][] map;
    static int[][][] smellMap;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static ArrayList<SK> skList = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수족관 크기, 상어 개수, K번 이동 후 냄새 사라짐
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        smellMap = new int[N][N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0)
                     skList.add(new SK(i, j, map[i][j], 0, null));
            }
        }

        // 번호순 정렬
        skList.sort(Comparator.comparingInt(o -> o.number));

        // 상어 방향 담기
        st = new StringTokenizer(br.readLine());
        for (int s = 0; s < M; s++)
            skList.get(s).dir = Integer.parseInt(st.nextToken()) - 1;

        // 상어 방향 우선순위 배열 담기
        for (int s = 0; s < M; s++) {
            skList.get(s).search = new int[4][4];
            for (int dir = 0; dir < 4; dir++) {
                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < 4; i++)
                    skList.get(s).search[dir][i] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        int time = 0;
        while (skList.size() > 1) {
            spread();
            time++;
            solve();
            if (time > 1000) {
                System.out.println(-1);
                return;
            }
            remove_smell();
        }
        System.out.println(time);
    }

    private static void remove_smell() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (smellMap[i][j][1] > 0) {
                    smellMap[i][j][1] -= 1;
                    if (smellMap[i][j][1] == 0)
                        smellMap[i][j][0] = 0;
                }
            }
        }
    }

    private static void solve() {
        ArrayList<Integer> rm = new ArrayList<>();
        for (SK s : skList) {
            int cy = s.y;
            int cx = s.x;
            int cd = s.dir;

            int nd = 0;
            boolean canGo = false;
            for (int dir = 0; dir < 4; dir ++) {
                int look = s.search[cd][dir];
                int ny = cy + dy[look];
                int nx = cx + dx[look];
                if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                    if (smellMap[ny][nx][0] == 0) { // 아무 냄새가 없는 칸
                        s.y = ny;
                        s.x = nx;
                        s.dir = look;
                        canGo = true;
                        break;
                    }
                }
            }
            if (!canGo) {
                for (int dir = 0; dir < 4; dir ++) {
                    int look = s.search[cd][dir];
                    int ny = cy + dy[look];
                    int nx = cx + dx[look];
                    if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                        if (smellMap[ny][nx][0] == s.number) { // 자신의 냄새 칸
                            s.y = ny;
                            s.x = nx;
                            s.dir = look;
                            canGo = true;
                            break;
                        }
                    }
                }
            }
            if (canGo) {
                map[cy][cx] = 0;
                if (map[s.y][s.x] == 0)
                    map[s.y][s.x] = s.number;
                else if (map[s.y][s.x] > s.number) {
                    rm.add(map[s.y][s.x]);
                    map[s.y][s.x] = s.number;
                } else if (map[s.y][s.x] < s.number)
                    rm.add(s.number);
            }
        }
        // 중복 삭제
        ArrayList<SK> remove = new ArrayList<>();
        for (Integer idx : rm) {
            for (SK sk : skList) {
                if (sk.number == idx) {
                    remove.add(sk);
                    break;
                }
            }
        }
        for (SK rmSK : remove)
            skList.remove(rmSK);
    }

    private static void spread() {
        for (SK s : skList) {
            int ny = s.y;
            int nx = s.x;
            smellMap[ny][nx][0] = s.number;
            smellMap[ny][nx][1] = K;
        }
    }

    private static class SK {
        int y;
        int x;
        int number;
        int dir;
        int[][] search = new int[4][4];
        public SK(int y, int x, int number, int dir, int[][] search) {
            this.y = y;
            this.x = x;
            this.number = number;
            this.dir = dir;
            this.search = search;
        }
    }
}
