package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Min_conference_room_19598 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ArrayList<time> list = new ArrayList<>();
        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            time conference = new time(start, end);
            list.add(conference);
        }
        Iterator<time> iter = list.iterator();
        int count = 0;
        int k = 0;
        while (k < list.size())
        {
            time now = list.get(k);
            list.remove(now);
            while (iter.hasNext())
            {
                time next = iter.next();
                if (now.end <= next.start)
                {
                    list.remove(next);
                    count++;
                    break;
                }
                else {
                    count++;
                }
            }
            k++;
        }
        System.out.println(count);
    }
}

class time
{
    int start;
    int end;
    time (int start, int end)
    {
        this.start = start;
        this.end = end;
    }
}