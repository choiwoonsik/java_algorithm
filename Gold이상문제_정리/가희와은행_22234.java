package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 가희와은행_22234 {
    static int N, T, W, M;
    static PriorityQueue<Customer> pq = new PriorityQueue<>(Comparator.comparingInt(C->C.start));
    private static class Customer
    {
        int id;
        int work;
        int start;

        public Customer(int id, int work, int start) {
            this.id = id;
            this.work = work;
            this.start = start;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        Deque<Customer> customers = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int work = Integer.parseInt(st.nextToken());
            customers.add(new Customer(id, work, 0));
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int work = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            pq.add(new Customer(id, work, start));
        }

        startWork(customers);
    }

    private static void startWork(Deque<Customer> customers) {
        int TIME = 0;
        StringBuilder answer = new StringBuilder();

        while (TIME < W) {
            Customer customer = customers.pollFirst();
            for (int i = 0; i < Math.min(T, customer.work); i++) {
                answer.append(customer.id).append("\n");
                if (++TIME == W) break;
            }

            //손님 넣기
            while (!pq.isEmpty() && pq.peek().start <= TIME) {
                customers.addLast(pq.poll());
            }

            // 뒤로 가는지 체크
            if (customer.work - T > 0) {
                customer.work -= T;
                customers.addLast(customer);
            }
        }

        System.out.print(answer);
    }
}
