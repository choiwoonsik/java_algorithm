package Gold이상문제_정리;

/*
10 3
1 2 3 4 5 -1
9 7 10 -1
7 6 3 8 -1
1 10
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소환승경로_2021 {
    static int N, M;
    static boolean[] visitedLine;
    static boolean[] visitedStation;
    static ArrayList<Integer>[] stations;
    static ArrayList<Integer>[] lines;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visitedLine = new boolean[M + 1];
        visitedStation = new boolean[N + 1];
        stations = new ArrayList[N + 1];
        lines = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            stations[i] = new ArrayList<>();
            lines[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            String[] s = br.readLine().split(" ");
            for (String station : s) {
                int statN = Integer.parseInt(station);
                if (statN == -1) break;
                stations[statN].add(i);
                lines[i].add(statN);
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int answer = go(start, end);
        System.out.println(answer);
    }

    private static int go(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.transCount));
        visitedStation[start] = true;
        for (int line : stations[start]) {
            pq.add(new Node(line, start, 0));
            visitedLine[line] = true;
        }

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.curStation == end) {
                return now.transCount;
            }

            for (int nextStation : lines[now.curLine]) {

                if (!visitedStation[nextStation]) {
                    visitedStation[nextStation] = true;
                    pq.add(new Node(now.curLine, nextStation, now.transCount));

                    for (int nextLine : stations[nextStation]) {
                        if (!visitedLine[nextLine]) {
                            visitedLine[nextLine] = true;
                            pq.add(new Node(nextLine, nextStation, now.transCount + 1));
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static class Node {
        int curLine;
        int curStation;
        int transCount;

        public Node(int curLine, int curStation, int transCount) {
            this.curLine = curLine;
            this.curStation = curStation;
            this.transCount = transCount;
        }
    }
}
