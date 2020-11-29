import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class absolute_heap_11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int n;

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            if (Math.abs(o1) < Math.abs((o2)))
                return -1;
            else if (Math.abs(o1) == Math.abs(o2))
            {
                if (o1 < o2)
                    return -1;
                else
                    return 1;
            }
            else
                return 1;
        });

        while (N-- > 0)
        {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                if (pq.isEmpty())
                    sb.append("0\n");
                else
                    sb.append(pq.poll()).append("\n");
            }
            else
                pq.add(n);
        }
        System.out.println(sb);
    }
}
