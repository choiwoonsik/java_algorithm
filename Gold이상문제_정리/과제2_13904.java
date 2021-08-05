package Gold이상문제_정리;

/*
7
4 60
4 40
1 20
2 50
3 30
4 10
6 5
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 과제2_13904 {
    static int N;
    static int LAST;
    static PriorityQueue<subject> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pq = new PriorityQueue<>((s1, s2) -> {
            if (s1.point == s2.point) return Integer.compare(s1.dday, s2.dday);
            else return Integer.compare(s2.point, s1.point);
        });
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int dday = Integer.parseInt(input[0]);
            int point = Integer.parseInt(input[1]);
            LAST = Math.max(dday, LAST);
            pq.add(new subject(dday, point));
        }
        doSubject();
    }

    private static void doSubject() {
        int[] days = new int[LAST + 1];
        Arrays.fill(days, 0);

        while (!pq.isEmpty()) {
            subject nowHw = pq.poll();

            for (int day = nowHw.dday; day >= 1; day--) {
                if (days[day] == 0) {
                    days[day] = nowHw.point;
                    break;
                }
            }
        }

        int sum = 0;
        for (int i = 1; i <= LAST; i++)
            sum += days[i];
        System.out.println(sum);
    }

    private static class subject {
        int dday, point;

        public subject(int dday, int point) {
            this.dday = dday;
            this.point = point;
        }
    }
}
