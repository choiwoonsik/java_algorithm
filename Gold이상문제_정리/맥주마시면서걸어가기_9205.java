package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 맥주마시면서걸어가기_9205 {
    static int T, storeCnt;
    static SPOT home, festival;
    static boolean[] visited;
    static ArrayList<SPOT> spots = new ArrayList<>();
    static PriorityQueue<SPOT> pq;
    static StringBuilder answer = new StringBuilder();

    private static class SPOT {
        int idx, x, y;

        public SPOT(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        while (T-- > 0)
        {
            spots.clear();

            storeCnt = Integer.parseInt(br.readLine());
            for (int i = 0; i < storeCnt + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                spots.add(new SPOT(i, x, y));
                if (i == 0) home = new SPOT(i, x, y);
                if (i == storeCnt + 1) festival = new SPOT(i, x, y);
            }

            spots.sort(Comparator.comparingInt(spot -> hereTolDist(spot, festival)));
            visited = new boolean[storeCnt + 2];
            go();
        }
        System.out.print(answer);
    }

    private static void go() {
        int move = 1000;
        pq = new PriorityQueue<>(Comparator.comparingInt(spot -> hereTolDist(spot, festival)));
        pq.add(home);
        visited[home.idx] = true;

        while (!pq.isEmpty()) {
            SPOT now = pq.poll();

            if (hereTolDist(now, festival) <= move) {
                answer.append("happy").append("\n");
                return;
            }

            for (SPOT nextSpot : spots) {
                if (!visited[nextSpot.idx]) {
                    if (hereTolDist(now, nextSpot) <= move) {
                        visited[nextSpot.idx] = true;
                        pq.add(nextSpot);
                    }
                }
            }
        }
        answer.append("sad").append("\n");
    }

    private static int hereTolDist(SPOT home, SPOT target) {
        return Math.abs(home.x - target.x) + Math.abs(home.y - target.y);
    }
}
