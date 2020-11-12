import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Robot_cleaner_14503 {
    // 0,1,2,3 = 북 서 남 동
    static int[] d_h = {-1,0,1,0};
    static int[] d_w = {0,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 보드판
        st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        //로봇 위치 및 방향
        st = new StringTokenizer(br.readLine());
        int nowH = Integer.parseInt(st.nextToken());
        int nowW = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        if (dir == 1)
            dir = 3;
        else if (dir == 3)
            dir = 1;
        //Dir_robot nowDir = new Dir_robot(d_h[dir], d_w[dir]);

        int[][] board = new int[H][W];
        boolean[][] visited = new boolean[H][W];

        for (int h = 0; h < H; h++) {
            st = new StringTokenizer(br.readLine());
            for (int w = 0; w < W; w++) {
                board[h][w] = Integer.parseInt(st.nextToken());
            }
        }


        int count = 0;
        boolean goBack;
        Queue<Dir_robot> queue = new LinkedList<>();
        queue.add(new Dir_robot(nowH, nowW));
        if (board[nowH][nowW] == 0)
            count++;
        visited[nowH][nowW] = true;

        while (!queue.isEmpty())
        {
            Dir_robot now_spot = queue.poll();
            Dir_robot check_spot;
            goBack = true;
            for (int i = 1; i <= 4; i++) {
                int rotate = (dir + i) % 4;
                check_spot = new Dir_robot(now_spot.h + d_h[rotate], now_spot.w + d_w[rotate]);
                if (!visited[check_spot.h][check_spot.w] && board[check_spot.h][check_spot.w] == 0) {
                    visited[check_spot.h][check_spot.w] = true;
                    nowH = check_spot.h;
                    nowW = check_spot.w;
                    queue.add(new Dir_robot(nowH, nowW));
                    dir = rotate;
                    count++;
                    goBack = false;
                    break;
                }
            }
            //후진
            int rotate = (dir + 2) % 4;
            //뒤에 공간 있음
            if (goBack && board[now_spot.h + d_h[rotate]][now_spot.w + d_w[rotate]] == 0) {
                nowH = now_spot.h + d_h[rotate];
                nowW = now_spot.w + d_w[rotate];
                queue.add(new Dir_robot(nowH, nowW));
            }
            //뒤에 공간 없음
            else if (goBack && board[now_spot.h + d_h[rotate]][now_spot.w + d_w[rotate]] == 1)
                break;
        }
        System.out.println(count);
    }
}

class Dir_robot
{
    int h;
    int w;
    public Dir_robot(int h, int w) {
        this.h = h;
        this.w = w;
    }
}
