package 그래프탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 맥주마시면서걸어가기_9205 {
    static int T;
    static boolean[] visited;
    static ArrayList<SPOT> spots = new ArrayList<>();
    static PriorityQueue<SPOT> pq;
    static StringBuilder answer = new StringBuilder();

    private static class SPOT {
        int x, y;

        public SPOT(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            spots.clear();

            int storeCnt = Integer.parseInt(br.readLine());
            for (int i = 0; i < storeCnt + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                spots.add(new SPOT(x, y));
            }

            visited = new boolean[storeCnt + 2];
            go();
        }
        System.out.println(answer);
    }

    private static void go() {
        int move = 1000;
        pq = new PriorityQueue<>(Comparator.comparingInt(spot -> hereTolDist(spot, spots.get(spots.size() - 1))));
        pq.add(spots.get(0));
        visited[0] = true;

        while (!pq.isEmpty()) {
            SPOT now = pq.poll();

            if (hereTolDist(now, spots.get(spots.size() - 1)) <= move) {
                answer.append("happy").append("\n");
                return;
            }

            for (int i = 1; i < spots.size(); i++) {
                if (!visited[i]) {
                    if (hereTolDist(now, spots.get(i)) <= move) {
                        visited[i] = true;
                        pq.add(spots.get(i));
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

