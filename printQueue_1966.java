import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class printQueue_1966 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++)
        {
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 > o2 ? -1 : 1);
            Queue<printOrder> queue = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++){
                int price = Integer.parseInt(st.nextToken());
                queue.add(new printOrder(price, i));
                pq.add(price);
            }

            int count = 0;
            while (true)
            {
                printOrder now = queue.poll();

                if (now.price == pq.peek()){
                    pq.poll();
                    count++;
                    if (target == now.pos) {
                        System.out.println(count);
                        break;
                    }
                }
                else {
                    queue.add(new printOrder(now.price, now.pos));
                }
            }
        }

    }
}

class printOrder
{
    int price;
    int pos;
    printOrder (int price, int pos) {
        this.price = price;
        this.pos = pos;
    }
}
