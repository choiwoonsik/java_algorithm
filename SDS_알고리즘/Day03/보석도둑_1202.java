package SDS_알고리즘.Day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
3 2
1 65
5 23
2 99
10
2

 */
public class 보석도둑_1202 {
    static int N, K;
    static PriorityQueue<Jew> jeweryQ = new PriorityQueue<>(Comparator.comparing(J -> J.weigh));
    static PriorityQueue<Jew> jQ = new PriorityQueue<>(Comparator.comparingInt(J -> -J.price));
    static PriorityQueue<Integer> bagQ = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            jeweryQ.add(new Jew(w, p));
        }
        for (int i = 0; i < K; i++) {
            bagQ.add(Integer.parseInt(br.readLine()));
        }

        long total = 0;

        while (!bagQ.isEmpty()) {
            int bag = bagQ.poll();

            while (!jeweryQ.isEmpty()) {
                if (jeweryQ.peek().weigh <= bag) {
                    jQ.add(jeweryQ.poll());
                } else break;
            }
            if (!jQ.isEmpty())
                total += jQ.poll().price;
        }
        System.out.println(total);
    }

    private static class Jew {
        int weigh;
        int price;

        public Jew(int weigh, int price) {
            this.weigh = weigh;
            this.price = price;
        }
    }
}
