import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.PriorityQueue;

public class card_sort_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int card = Integer.parseInt(br.readLine());
            pq.add(card);
        }

        int sum = 0;
        int all = 0;

        if (pq.size() > 0)
            sum = pq.poll();
        int count=1;
        while (!pq.isEmpty())
        {
            count++;
            sum += pq.poll();
            if (count == 2) {
                all += sum;
                pq.add(sum);
                sum = 0;
                count = 0;
            }
        }
        System.out.println(all);
    }
}
