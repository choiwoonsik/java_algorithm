package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 수족관1_8982 {
    static Water[] pools = new Water[40002];
    static ArrayList<Dot[]> holeList = new ArrayList<>();
    static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        br.readLine();
        int x;
        int y;

        int max = 0;
        for (int i = 0; i < (n - 2) / 2; i++) {
            Dot left;
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            left = new Dot(y, x);

            Dot right;
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            right = new Dot(y, x);

            for (int col = left.x; col < right.x; col++) {
                pools[col] = new Water(left.y, left.y);
            }
            max = Math.max(max, y);
        }
        String[] dot = br.readLine().split(" ");
        x = Integer.parseInt(dot[0]);
        y = Integer.parseInt(dot[1]);
        Dot end = new Dot(y, x);

        N = max;
        M = end.x;

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            int x1 = Integer.parseInt(line[0]);
            int y1 = Integer.parseInt(line[1]);
            int x2 = Integer.parseInt(line[2]);
            int y2 = Integer.parseInt(line[3]);
            holeList.add(new Dot[]{new Dot(y1, x1), new Dot(y2, x2)});
        }

        drainHole();
        int answer = 0;
        for (int i = 0; i < M; i++) {
            answer += pools[i].w;
        }
        System.out.println(answer);
    }

    private static void drainHole() {
        for (Dot[] dots : holeList) {
            Dot left = dots[0];
            Dot right = dots[1];

            //왼쪽
            for (int x = left.x; x >= 0; x--) {
                left.y = Math.min(pools[x].height, left.y);
                pools[x].w = Math.min(pools[x].w, pools[x].height - left.y);
            }
            // 오른쪽
            for (int x = left.x+1; x < M; x++) {
                right.y = Math.min(pools[x].height, right.y);
                pools[x].w = Math.min(pools[x].w, pools[x].height- right.y);
            }
        }
    }

    private static class Dot {
        int y, x;

        public Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static class Water {
        int w;
        int height;

        public Water(int w, int height) {
            this.w = w;
            this.height = height;
        }
    }
}