import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class line_up_2252 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException
    {
        int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = n[0];
        int M = n[1];

        ArrayList<pair> list = new ArrayList<>();
        LinkedList<Integer> map[] = new LinkedList[N+1];
        StringBuilder sb = new StringBuilder();
        int[] countArr = new int[N+1];

        for (int i = 0; i < map.length; i++)
            map[i] = new LinkedList<>();

        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            list.add(new pair(L, R));
        }

        Iterator<pair> iter = list.iterator();
        while (iter.hasNext())
        {
            pair p = iter.next();
            countArr[p.R]++;
            map[p.L].add(p.R);
        }

        for (int i = 1; i < countArr.length; i++)
            if (countArr[i] == 0)
                queue.add(i);

        while (!queue.isEmpty())
        {
            int order = queue.poll();
            int size = map[order].size();
            for (int i = 0; i < size; i++)
            {
                int after = map[order].poll();
                countArr[after] -= 1;
                if (countArr[after] == 0)
                    queue.add(after);
            }
            sb.append(order+" ");
        }
        System.out.println(sb);
    }
}

class pair
{
    int L;
    int R;
    pair (int L, int R){
        this.L = L;
        this.R = R;
    }
}
