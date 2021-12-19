package sds_algorithm_class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main3 {
    static int[] map;
    static int D;
    static int minRest;
    static int lastCamp;
    static int moveT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ret = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int L = Integer.parseInt(br.readLine());
            D = 0;
            map = new int[L];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < L; j++) {
                map[j] = Integer.parseInt(st.nextToken());
                if (map[j] == 3) {
                    D = j;
                }
            }
            ret.append("#").append(i+1).append(" ").append(trip()).append("\n");
        }
        System.out.print(ret);
    }

    private static int trip() {
        int start = 0;
        int cost = 0;

        while (true) {
            moveT = 0;
            goLastCamp(start);
            cost += moveT;
            start = lastCamp;
            if (lastCamp == D) break;
            if (moveT == -1) return moveT;
        }
        return cost;
    }

    private static void goLastCamp(int start) {
        minRest = 9999999;
        lastCamp = 0;

        ArrayList<Node> list = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(N -> N.restCount));
        pq.add(new Node(start, 0, 0, 0, false));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.pos > map.length - 1) {
                continue;
            }

            if (now.move > 6 || now.move + now.beforeMove + now.restCount > 15) {
                continue;
            }

            if (map[now.pos] == 3 && now.sleep) {
                list.add(now);
                continue;
            }

            int next = now.pos + 1;
            int nextMove = now.move + 1;

            if (next >= map.length) break;
            if (map[next] == 1) {
                if (nextMove >= 6) continue;
                pq.add(new Node(next, now.restCount, nextMove, now.beforeMove, false));
            }
            if (map[next] == 2) {
                // 쉰다.
                pq.add(new Node(next, now.restCount + 1, 0, now.beforeMove + now.move + 1, false));
                // 안 쉰다.
                if (nextMove >= 6) continue;
                pq.add(new Node(next, now.restCount, nextMove, now.beforeMove, false));
            }
            if (map[next] == 3) {
                pq.add(new Node(next, now.restCount, nextMove, now.beforeMove, true));
                pq.add(new Node(next, now.restCount, nextMove, now.beforeMove, false));
            }
        }

        if (!list.isEmpty()) {
            list.sort(Comparator.comparingInt(N -> N.restCount));
            list.sort(Comparator.comparingInt(N -> -N.pos));
            Node node = list.get(0);
            moveT = node.move + node.beforeMove + node.restCount;
            lastCamp = node.pos;
        } else {
            moveT = -1;
        }
    }

    private static class Node {
        int pos;
        int restCount;
        int move;
        int beforeMove;
        boolean sleep;

        public Node(int pos, int restCount, int move, int beforeMove, boolean sleep) {
            this.pos = pos;
            this.restCount = restCount;
            this.move = move;
            this.beforeMove = beforeMove;
            this.sleep = sleep;
        }
    }
}
