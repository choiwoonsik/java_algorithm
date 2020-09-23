import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class part_sum_of_nums_1182 {
    static int N, count;
    static int target;
    static int[] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException
    {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        recursive(0, 0);
        System.out.println(count);
    }
    private static void recursive(int start, int sum)
    {
        sum += arr[start];
        if (start == N)
            return;
        if (sum == target)
            count++;
        recursive(start + 1, sum - arr[start]);
        recursive(start + 1, sum);
    }
}
