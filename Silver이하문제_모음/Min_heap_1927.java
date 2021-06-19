package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Min_heap_1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++)
        {
            int input = Integer.parseInt(br.readLine());

            if (input == 0) {
                if (pq.size() == 0)
                    System.out.println(0);
                else
                    System.out.println(pq.poll());
            }
            else
                pq.add(input);
        }
    }
}
