package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 컵라면_1781 {
    static int N, LastDay;
    static PriorityQueue<Cup> pq = new PriorityQueue<>(Comparator.comparingInt(c -> -c.count));
    static int[] days;

    private static class Cup {
        int deadLine;
        int count;

        public Cup(int deadLine, int count) {
            this.deadLine = deadLine;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int dead = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            pq.add(new Cup(dead, count));
            LastDay = Math.max(LastDay, dead);
        }

        days = new int[LastDay + 1];
        for (int i = 0; i < LastDay + 1; i++) days[i] = i;

        int answer = solve();
        System.out.println(answer);
    }

    private static int solve() {
        int totalCup = 0;
        int today;
        while (!pq.isEmpty()) {
            Cup now = pq.poll();
            today = now.deadLine;

            if (today >= N) {
                totalCup += now.count;
                continue;
            }

            if (find(today) == today) {
                union(find(today - 1), today);
                totalCup += now.count;
            } else if (find(today) != 0) {
                today = find(today);
                union(find(today - 1), today);
                totalCup += now.count;
            }
        }
        return totalCup;
    }

    private static int find(int now) {
        if (days[now] == now) return now;
        return days[now] = find(days[now]);
    }

    private static void union(int left, int right) {
        left = find(left);
        right = find(right);

        days[days[right]] = days[left];
    }
}
