package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 우체국_2141 {
    static int N;
    static PriorityQueue<Village> 우선순위큐 = new PriorityQueue<>(Comparator.comparingInt(v -> v.pos));
    private static class Village{
        int pos;
        int people;

        public Village(int pos, int people) {
            this.pos = pos;
            this.people = people;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        long peopleCount = 0;
        long totalCount = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            우선순위큐.add(new Village(pos, people));
            totalCount += people;
        }

        while (!우선순위큐.isEmpty()) {
            Village now = 우선순위큐.poll();

            peopleCount += now.people;

            if (peopleCount >= (totalCount + 1) / 2) {
                System.out.println(now.pos);
                break;
            }
        }
    }
}
