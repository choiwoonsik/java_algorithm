import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class printer_queue_1966 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException
    {
        Queue<Docs> queue = new LinkedList<>();
        int N = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            // 총 몇개의 문서 count, 궁금한 문서 target
            int count = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            // 모든 문서를 읽어서 큐에다가 순서와 중요도를 넣는다
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < count; j++) {
                int priority = Integer.parseInt(st.nextToken());
                queue.add(new Docs(j, priority));
            }
            print(queue, target);
        }
    }
    private static void print(Queue<Docs> queue, int target_pos)
    {
        int result = 1;
        while (!queue.isEmpty())
        {
            Docs now = queue.poll();
            boolean find_more_important = false;
            Iterator<Docs> iter = queue.iterator();
            while (iter.hasNext())
            {
                if (now.priority < iter.next().priority)
                {
                    find_more_important = true;
                    break;
                }
            }
            //맨앞에 있던 문서를 맨 뒤로 보낸다
            if (find_more_important)
                queue.add(now);
            else if (now.pos == target_pos)
                System.out.println(result);
            else
                result++;
        }
    }
}

class Docs
{
    int pos;
    int priority;
    Docs (int pos, int priority)
    {
        this.pos = pos;
        this.priority = priority;
    }
}