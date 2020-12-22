import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SortPos_11650 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        List<Dot_111650> list = new ArrayList<>();
        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Dot_111650(x, y));
        }
        Collections.sort(list);
        list.forEach(System.out::println);
    }
}
class Dot_111650 implements Comparable<Dot_111650>
{
    int x;
    int y;
    Dot_111650 (int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Dot_111650 o) {
        if (this.x < o.x)
            return -1;
        else {
            if (this.x == o.x && this.y < o.y)
                return -1;
            return 1;
        }
    }

    @Override
    public String toString() {
        return this.x +" "+this.y;
    }
}