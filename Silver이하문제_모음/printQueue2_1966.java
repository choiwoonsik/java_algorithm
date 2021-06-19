package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class printQueue2_1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++)
        {
            Queue<printObject> queue = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int priority = Integer.parseInt(st.nextToken());
                queue.add(new printObject(priority, i));
            }

            int printCount = 0;

            while (!queue.isEmpty()) {
                printObject now = queue.poll();

                Iterator<printObject> iter = queue.iterator();
                boolean findMoreBig = false;
                while (iter.hasNext()) {
                    if (iter.next().priority > now.priority) {
                        findMoreBig = true;
                        break;
                    }
                }
                if (findMoreBig) {
                    queue.add(new printObject(now.priority, now.pos));
                }
                else {
                    printCount++;
                    if (now.pos == target) {
                        System.out.println(printCount);
                        break;
                    }
                }

            }
        }
    }
}

class printObject
{
    int priority;
    int pos;
    printObject (int priority, int pos) {
        this.priority = priority;
        this.pos = pos;
    }
}
