package Silver이하문제_모음;

import java.io.*;
import java.util.*;
public class youth_shark_19236 {
    static int ret;
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] board = new int[4][4];
        Fish[] fish = new Fish[16];

        for (int y = 0; y < 4; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < 4; x++) {
                int bunho = Integer.parseInt(st.nextToken()) - 1;
                int dir = Integer.parseInt(st.nextToken()) - 1;
                fish[bunho] = new Fish(y, x, dir);
                board[y][x] = bunho;
            }
        }

        ret = 0;
        solve(board, fish, 0, 0, 0);
        System.out.println(ret);
    }

    private static void solve(int[][] board, Fish[] fish, int shark_y, int shark_x, int sum) {
        Fish[] candi_fish = new Fish[16];
        int[][] candi_board = new int[4][4];
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                candi_board[y][x] = board[y][x];
            }
        }
        for (int i = 0; i < 16; i++) {
            int y = fish[i].y;
            int x = fish[i].x;
            int dir = fish[i].dir;
            candi_fish[i] = new Fish(y, x, dir);
        }
        // eat
        int fish_number = candi_board[shark_y][shark_x];
        int shark_dir = candi_fish[fish_number].dir;
        candi_fish[fish_number].y = -1;
        candi_fish[fish_number].x = -1;
        candi_fish[fish_number].dir = -1;
        candi_board[shark_y][shark_x] = -1;

        sum += (fish_number + 1);
        if (ret < sum)
            ret = sum;

        // fish move
        for (int f = 0; f < 16; f++) {
            if (candi_fish[f].y == -1)
                continue;
            int cy = candi_fish[f].y;
            int cx = candi_fish[f].x;
            int cd = candi_fish[f].dir;

            int ny = cy + dy[cd];
            int nx = cx + dx[cd];
            int nd = cd;
            while (ny < 0 || ny >= 4 || nx < 0 || nx >= 4 || (ny == shark_y && nx == shark_x)) {
                nd = (nd + 1) % 8;
                ny = cy + dy[nd];
                nx = cx + dx[nd];
            }
            if (candi_board[ny][nx] != -1) {
                //물고기가 있는경우 - 교환
                int target = candi_board[ny][nx];
                candi_fish[target].y = cy;
                candi_fish[target].x = cx;
                candi_fish[f].y = ny;
                candi_fish[f].x = nx;
                candi_fish[f].dir = nd;

                candi_board[ny][nx] = f;
                candi_board[cy][cx] = target;
            }
            else {
                // 물고기가 없는경우
                candi_fish[f].y = ny;
                candi_fish[f].x = nx;
                candi_fish[f].dir = nd;

                candi_board[ny][nx] = f;
                candi_board[cy][cx] = -1;
            }
        }
        // shark move
        for (int step = 1; step < 4; step++) {
            int ny = shark_y + dy[shark_dir] * step;
            int nx = shark_x + dx[shark_dir] * step;
            if (ny >= 0 && ny < 4 && nx >= 0 && nx < 4) {
                if (candi_board[ny][nx] != -1 ) {
                        solve(candi_board, candi_fish, ny, nx, sum);
                }
            }
            else
                break;
        }
    }

    private static class Fish
    {
        int y;
        int x;
        int dir;

        public Fish(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }
}
