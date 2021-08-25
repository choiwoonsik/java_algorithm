package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 시간관리하기_6068 {
    static int N;
    static PriorityQueue<job> pq = new PriorityQueue<>(Comparator.comparingInt(j -> -j.deadLine));

    private static class job{
        int needT;
        int deadLine;

        public job(int needT, int deadLine) {
            this.needT = needT;
            this.deadLine = deadLine;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int DEADLINE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int needTime = Integer.parseInt(st.nextToken());
            int deadLine = Integer.parseInt(st.nextToken());
            pq.add(new job(needTime, deadLine));
        }
        DEADLINE = pq.peek().deadLine;

        while (!pq.isEmpty() && DEADLINE >= 0) {
            job now = pq.poll();
            if (now.deadLine < DEADLINE) {
                DEADLINE = now.deadLine - now.needT;
            } else {
                DEADLINE -= now.needT;
            }
        }
        if (DEADLINE > 0) System.out.println(DEADLINE);
        else System.out.println(-1);
    }
}
