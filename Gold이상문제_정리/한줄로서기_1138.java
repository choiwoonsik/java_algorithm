import java.util.*;
import java.io.*;

public class 한줄로서기_1138 {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ArrayList<Integer> list = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        list.add(N);
        for (int i = N - 1; i > 0; i--) {
            int pos = arr[i];
            if (pos > list.size())
                list.add(list.size() - 1, i);
            else
                list.add(pos, i);
        }
        list.forEach(s -> System.out.print(s+" "));
    }
}
